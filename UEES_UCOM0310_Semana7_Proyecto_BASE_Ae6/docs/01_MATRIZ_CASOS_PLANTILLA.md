# Matriz de Casos de Prueba - Semana 7

## 1. Regla de Cancelación (`puedeCancelar`)
Regla: Se permite cancelar con 2 o más horas de anticipación.

| ID | Escenario | Entrada | Esperado | Tipo | Justificación |
|---|---|---|---|---|---|
| CP-01 | Anticipación habitual | 5 | true | Normal | Comprueba la regla general en flujo normal. |
| CP-02 | Límite permitido | 2 | true | Límite | Detecta uso incorrecto de operador estricto (`>`). |
| CP-03 | Debajo del límite | 1 | false | Límite | Protege la frontera inferior del umbral. |
| CP-04 | Sin anticipación | 0 | false | Extremo | Asegura que la cancelación inmediata sea rechazada. |

## 2. Regla de Descuentos (`calcularTotal`)
Regla: VIP 15%, ESTUDIANTE 10%, NORMAL 0%. Total debe ser no negativo.

| ID | Escenario | Entrada | Esperado | Tipo | Justificación |
|---|---|---|---|---|---|
| CP-05 | Cliente NORMAL | NORMAL, 100 | 100.0 | Normal | Flujo estándar sin aplicación de descuento. |
| CP-06 | Cliente VIP | VIP, 100 | 85.0 | Alternativo | Valida cálculo del 15% de descuento. |
| CP-07 | Cliente ESTUDIANTE | ESTUDIANTE, 100 | 90.0 | Alternativo | Valida cálculo del 10% de descuento. |
| CP-08 | Total en cero | VIP, 0 | 0.0 | Límite | Comprueba que el cálculo no genere errores en límite cero. |
| CP-09 | Total negativo | NORMAL, -1 | IllegalArgumentException | Inválido | Garantiza que se lance excepción ante montos inválidos. |