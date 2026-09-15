import imaplib
import email
from email.header import decode_header
import re
import socket
import unicodedata
IMAP_SERVER = "mail.tce.gob.ec"   # Reemplaza con tu host IMAP
IMAP_PORT = 993                      # 993 (SSL) o 143 (STARTTLS/Plano)
USERNAME = "secretaria.general@tce.gob.ec"
PASSWORD = "Sg.TcE.2025$"
socket.setdefaulttimeout(30)

def clean_header(val):
    if not val: return ""
    decoded = decode_header(val)
    parts = []
    for text, enc in decoded:
        if isinstance(text, bytes):
            try: parts.append(text.decode(enc or 'utf-8', errors='ignore'))
            except: parts.append(text.decode('latin-1', errors='ignore'))
        else: parts.append(str(text))
    return "".join(parts).strip()

mail = imaplib.IMAP4_SSL(IMAP_SERVER, IMAP_PORT)
mail.login(USERNAME, PASSWORD)
mail.select('"INBOX"', readonly=True)

# Buscar correos del 24 de agosto
status, data = mail.search(None, '(ON "24-Aug-2026")')
msg_ids = data[0].split() if status == 'OK' and data[0] else []

print(f"\n--- DIAGNÓSTICO DE CORREOS DEL 24 DE AGOSTO ---")
for num in msg_ids:
    status, msg_data = mail.fetch(num, '(BODY.PEEK[])')
    msg = email.message_from_bytes(msg_data[0][1])
    subj = clean_header(msg.get("Subject", ""))
    date_str = msg.get("Date", "")
    print(f"\nAsunto: {subj}")
    print(f"Fecha cabecera: {date_str}")
    print(f"Message-ID: {msg.get('Message-ID', '')}")
    print(f"Remitente: {clean_header(msg.get('From', ''))}")

mail.logout()
