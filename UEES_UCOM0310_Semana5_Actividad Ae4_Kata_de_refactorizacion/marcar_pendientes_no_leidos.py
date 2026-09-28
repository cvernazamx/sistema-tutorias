import imaplib
import email
from email.header import decode_header
import re
from datetime import datetime
import socket
import unicodedata

# ==========================================
# 1. CONFIGURACIÓN DE ACCESO
# ==========================================
IMAP_SERVER = "mail.tce.gob.ec"   # Reemplaza con tu host IMAP
IMAP_PORT = 993                      # 993 (SSL) o 143 (STARTTLS/Plano)
USERNAME = "secretaria.general@tce.gob.ec"
PASSWORD = "Sg.TcE.2025$"

# Nombres de carpetas
INBOX_FOLDER = "Bandeja de entrada"
INBOX_FOLDER = "INBOX"  # Si no existe, probaremos con "INBOX"
SENT_FOLDER = "Enviados"  # Si no existe, probaremos con "Enviados"
SENT_FOLDER = "Sent"  # Si no existe, probaremos con "Sent"

FECHA_DESDE_STR = "10-Aug-2026"

socket.setdefaulttimeout(35)

# ==========================================
# 2. FUNCIONES AUXILIARES
# ==========================================
def remove_accents(input_str):
    if not input_str:
        return ""
    nfkd_form = unicodedata.normalize('NFKD', input_str)
    return "".join([c for c in nfkd_form if not unicodedata.combining(c)])

def clean_header(header_val):
    if not header_val:
        return ""
    decoded = decode_header(header_val)
    parts = []
    for text, enc in decoded:
        if isinstance(text, bytes):
            try:
                parts.append(text.decode(enc or 'utf-8', errors='ignore'))
            except Exception:
                parts.append(text.decode('latin-1', errors='ignore'))
        else:
            parts.append(str(text))
    return "".join(parts).strip()

def extract_email_address(raw_str):
    match = re.search(r'[\w\.-]+@[\w\.-]+', raw_str)
    return match.group(0).lower() if match else ""

def get_body_peek(msg):
    body = ""
    if msg.is_multipart():
        for part in msg.walk():
            ctype = part.get_content_type()
            cdispo = str(part.get('Content-Disposition'))
            if ctype in ['text/plain', 'text/html'] and 'attachment' not in cdispo:
                try:
                    payload = part.get_payload(decode=True)
                    if payload:
                        decoded = payload.decode('utf-8', errors='ignore')
                        decoded = re.sub(r'<[^>]+>', ' ', decoded)
                        body += decoded + " "
                except Exception:
                    pass
    else:
        try:
            payload = msg.get_payload(decode=True)
            if payload:
                body = payload.decode('utf-8', errors='ignore')
        except Exception:
            pass
    return body.strip()

def matches_target_criteria(subject, body):
    text = remove_accents(f"{subject} {body}").lower()
    cert_patterns = [
        r'certificaci[oó]n',
        r'certificaciones',
        r'solicitud\s+(?:de\s+)?certificaci[oó]n',
        r'solicito\s+certificaci[oó]n'
    ]
    junta_patterns = [
        r'junta[s]?\s+provincial(?:es)?',
        r'junta[s]?\s+provincial(?:es)?\s+electoral(?:es)?',
        r'\bjpe\b',
        r'\bjpes\b',
        r'delegaci[oó]n\s+provincial\s+electoral',
        r'junta\s+electoral'
    ]
    return any(re.search(pat, text) for pat in cert_patterns + junta_patterns)

def extract_oficio(text):
    patterns = [
        r'([A-Z0-9]{2,10}(?:-[A-Z0-9]{2,10}){1,5}(?:-[0-9]{4})?(?:-[A-Z0-9]+)?)',
        r'(?:oficio|memorando|memo|ofc|nro\.?|n°|resoluci[oó]n|solicitud|acuerdo)\s*[:#-]?\s*([A-Za-z0-9_\-\/]{3,35})',
        r'(?:certificaci[oó]n|tramite|tr[aá]mite|causa)\s*(?:nro\.?|n°|#)?\s*([0-9A-Za-z\-\/]+)'
    ]
    for pattern in patterns:
        matches = re.finditer(pattern, text, re.IGNORECASE)
        for m in matches:
            val = m.group(1).strip()
            if len(val) >= 4 and val.lower() not in ["certificacion", "certificaciones", "solicitud", "oficio", "junta", "provincial"]:
                return val
    return "No identificado"

def normalize_subject(subj):
    s = remove_accents(subj).lower()
    s = re.sub(r'^(re|fwd|rv|enc|fwd:):\s*', '', s, flags=re.IGNORECASE)
    return re.sub(r'\s+', ' ', s).strip()

# ==========================================
# 3. EJECUCIÓN
# ==========================================
print(f"[{datetime.now().strftime('%H:%M:%S')}] Conectando a {IMAP_SERVER}...")

try:
    mail = imaplib.IMAP4_SSL(IMAP_SERVER, IMAP_PORT)
    mail.login(USERNAME, PASSWORD)
    print(f"[{datetime.now().strftime('%H:%M:%S')}] [✓] Autenticación correcta.")
except Exception as e:
    print(f"[X] Error: {e}")
    exit(1)

# Detectar carpeta de enviados
status, folders = mail.list()
available_folders = [re.search(r'\"([^\"]+)\"$', f.decode('utf-8', errors='ignore')).group(1) 
                     for f in folders if re.search(r'\"([^\"]+)\"$', f.decode('utf-8', errors='ignore'))]

if SENT_FOLDER not in available_folders:
    for candidate in ["Enviados", "Elementos enviados", "Sent Messages", "Sent Items", "Sent"]:
        found = [fol for fol in available_folders if candidate.lower() in fol.lower()]
        if found:
            SENT_FOLDER = found[0]
            break

# 1. Leer Enviados (sin alterar flags con BODY.PEEK[])
mail.select(f'"{SENT_FOLDER}"', readonly=True)
status, sent_data = mail.search(None, f'(SINCE "{FECHA_DESDE_STR}")')
sent_ids = sent_data[0].split() if status == 'OK' and sent_data[0] else []

sent_items = []
for num in sent_ids:
    status, msg_data = mail.fetch(num, '(BODY.PEEK[])')
    if status != 'OK':
        continue
    msg = email.message_from_bytes(msg_data[0][1])
    subject = clean_header(msg.get("Subject", ""))
    date_tuple = email.utils.parsedate_tz(msg.get("Date"))
    msg_date = datetime.fromtimestamp(email.utils.mktime_tz(date_tuple)) if date_tuple else datetime.now()
    recipient = clean_header(msg.get("To", ""))
    body = get_body_peek(msg)

    sent_items.append({
        "message_id": msg.get("Message-ID", "").strip(),
        "in_reply_to": msg.get("In-Reply-To", "").strip(),
        "references": msg.get("References", "").strip(),
        "date": msg_date,
        "norm_subject": normalize_subject(subject),
        "recipient_email": extract_email_address(recipient),
        "oficio": extract_oficio(f"{subject}\n{body}"),
        "body": body,
        "subject": subject
    })

# 2. Leer Entrada (en modo lectura/escritura para poder modificar flags)
mail.select(f'"{INBOX_FOLDER}"', readonly=False)
status, inbox_data = mail.search(None, f'(SINCE "{FECHA_DESDE_STR}")')
inbox_ids = inbox_data[0].split() if status == 'OK' and inbox_data[0] else []

print(f"[{datetime.now().strftime('%H:%M:%S')}] Evaluando {len(inbox_ids)} correos de entrada...")

pendientes_count = 0

for num in inbox_ids:
    status, msg_data = mail.fetch(num, '(BODY.PEEK[])')
    if status != 'OK':
        continue
    
    msg = email.message_from_bytes(msg_data[0][1])
    subject = clean_header(msg.get("Subject", ""))
    body = get_body_peek(msg)

    # Filtrar solo los que corresponden al trámite
    if not matches_target_criteria(subject, body):
        continue

    date_tuple = email.utils.parsedate_tz(msg.get("Date"))
    msg_date = datetime.fromtimestamp(email.utils.mktime_tz(date_tuple)) if date_tuple else datetime.now()
    sender = clean_header(msg.get("From", ""))
    sender_email = extract_email_address(sender)
    msg_id = msg.get("Message-ID", "").strip()
    norm_subject = normalize_subject(subject)
    oficio = extract_oficio(f"{subject}\n{body}")

    # Verificar si fue atendido
    match_found = False
    
    # Cruce por ID
    for s in sent_items:
        if msg_id and (msg_id in s["in_reply_to"] or msg_id in s["references"]):
            match_found = True
            break
            
    # Cruce por Asunto
    if not match_found and norm_subject:
        for s in sent_items:
            if s["norm_subject"] and (norm_subject in s["norm_subject"] or s["norm_subject"] in norm_subject):
                if s["date"] >= msg_date:
                    match_found = True
                    break

    # Cruce por Oficio
    if not match_found and oficio != "No identificado":
        for s in sent_items:
            if oficio.lower() in s["body"].lower() or oficio.lower() in s["subject"].lower():
                if s["date"] >= msg_date:
                    match_found = True
                    break

    # Cruce por Remitente/Destinatario
    if not match_found and sender_email:
        for s in sent_items:
            if sender_email == s["recipient_email"] and s["date"] >= msg_date:
                if (s["date"] - msg_date).days <= 10:
                    match_found = True
                    break

    # Si es PENDIENTE -> Remover la bandera \Seen (Marcar como NO LEÍDO)
    if not match_found:
        mail.store(num, '-FLAGS', '\\Seen')
        pendientes_count += 1
        print(f" [NO LEÍDO] Marcado: {subject[:65]}")

mail.logout()
print(f"\n[✓] Listo. Se marcaron {pendientes_count} correos pendientes como NO LEÍDOS en Zimbra.")