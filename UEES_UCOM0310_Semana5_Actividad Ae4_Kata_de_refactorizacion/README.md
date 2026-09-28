cat << 'EOF' > "UEES_UCOM0310_Semana5_Actividad Ae4_Kata_de_refactorizacion/README.md"
# Actividad 2 (Ae4) – Kata de Refactorización: Antes y Después
**Materia:** Diseño de Software (UCOM0310)
**Institución:** Universidad de Especialidades Espíritu Santo (UEES)
**Semana:** Semana 5 · PEL 4 – 2026
**Estudiante:** Christian Fernando Vernaza Vernaza

---

## 1. Descripción del Proyecto
Este módulo realiza la auditoría y conciliación documental de expedientes electrónicos y trámites procesales gestionados vía correo electrónico institucional (IMAP/SSL). Su función principal es cruzar solicitudes recibidas con respuestas formales emitidas, evaluando criterios de vinculación técnica (oficios de entrada, oficios de salida TCE, adjuntos PDF e hilos de correo RFC822) y generando un dashboard ejecutivo automatizado en formato Excel (`auditoria_certificaciones.xlsx`).

---

## 2. Diagnóstico de Code Smells (Antes)
Durante la inspección de la línea base se diagnosticaron los siguientes problemas de diseño:
1. **Hardcoded Secrets & Magic Values:** Credenciales de correo y parámetros de red incrustados directamente en el código fuente.
2. **Monolithic Script / Global Scope Execution:** Ausencia de un orquestador o función de entrada (`main()`); todo el flujo se ejecutaba proceduralmente en el ámbito global.
3. **Long Method & Deep Nesting (Arrow Anti-pattern):** Anidamientos excesivos (`for`-`if`-`for`) para la conciliación de oficios e hilos de respuesta.
4. **Feature Envy & Violación de SRP:** Manipulación visual directa de celdas y estilos OpenPyXL mezclada con la lógica de red y de negocio.

---

## 3. Refactorizaciones Aplicadas
El código fue transformado de manera incremental sin alterar su comportamiento funcional:
* **Extract Variable / Environmental Configuration:** Migración de credenciales hacia variables de entorno (`os.getenv`) con valores por defecto seguros.
* **Extract Method (SRP):** Desacoplamiento de la capa de visualización en la función especializada `generar_reporte_excel()`.
* **Simplificación de Flujo y Retornos Tempranos:** Descomposición de la heurística de matching en `indexar_respuestas_por_oficio()`, `buscar_respuesta_para_solicitud()` y `evaluar_estado_tramite()`.
* **Encapsulación del Orquestador:** Creación del punto de entrada modular `main()` y el guardián de ejecución `if __name__ == "__main__":`.

---

## 4. Requisitos y Dependencias