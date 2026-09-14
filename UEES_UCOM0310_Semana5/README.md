# Actividad 1 - Semana 5: De Code Smells a Código Mantenible
**Asignatura:** Diseño de Software (UCOM0310)  
**Institución:** Universidad Espíritu Santo (UEES)  
**Semana:** 5 · PEL 4 – 2026  

---

## 1. Diagnóstico de Code Smells
| Fragmento | Smell | Problema identificado | Técnica aplicada |
| :--- | :--- | :--- | :--- |
| `proc()` | Nombre poco expresivo | No comunica la intención de la operación. | Rename (`confirmarReserva`) |
| `r` | Nombre ambiguo | Obliga a deducir el tipo y contexto. | Rename (`reserva`) |
| `h` | Nombre ambiguo | No indica significado ni unidad temporal. | Rename (`horasAnticipacion`) |
| `if` anidados | Complejidad de flujo (Arrow pattern) | Cuatro niveles que ocultan el flujo principal. | Simplificación con Guard Clauses |
| `2` | Magic Number | Oculta la regla de negocio del dominio. | Constante expresiva (`HORAS_MINIMAS_CONFIRMACION`) |
| Mensajes + Lógica | Responsabilidades mezcladas | Mezcla validaciones con la lógica de confirmación. | Extract Method (`esReservaProcesable`) |

---

## 2. Comparación Antes y Después
| Dimensión | Antes | Después |
| :--- | :--- | :--- |
| **Nombres** | `proc`, `r`, `h` | `confirmarReserva`, `reserva`, `horasAnticipacion` |
| **Flujo** | 4 niveles de `if` anidados | Guard Clauses y validación desacoplada |
| **Regla** | `2` (Magic Number) | `HORAS_MINIMAS_CONFIRMACION` (Constante) |
| **Responsabilidad** | Validación dentro del flujo | `esReservaProcesable` extraído |
| **Caso A (5 h, válida)** | Procesa y confirma | Procesa y confirma |
| **Caso B (5 h, cancelada)** | No procesa | No procesa |
| **Caso C (Reserva null)** | No procesa | No procesa |
| **Caso D (1 h, válida)** | No procesa | No procesa |

---

## 3. Reflexión de Calidad (100–150 palabras)
La refactorización aplicada mejoró significativamente los atributos internos de calidad sin alterar el comportamiento observable externo del software. Internamente se redujo la complejidad ciclomática al sustituir la estructura en pirámide de cuatro condicionales anidados por cláusulas de guarda y la extracción del método `esReservaProcesable`. Se incrementó la legibilidad y mantenibilidad mediante nombres expresivos (`confirmarReserva`, `horasAnticipacion`) y se explicitó la regla de negocio mediante la constante `HORAS_MINIMAS_CONFIRMACION`, eliminando la ambigüedad del número mágico `2`. Externamente, el comportamiento del sistema permaneció estrictamente invariable: los casos de prueba A, B, C y D produjeron exactamente las mismas salidas y transiciones de estado de la reserva antes y después de cada cambio, validando que el proceso preservó la funcionalidad intacta.

---

## 4. Pregunta de Cierre
**¿Qué evidencia permite afirmar que la versión final tiene mejor estructura si el resultado funcional permanece exactamente igual?**  
Se evidencia principalmente por:
1. **Reducción de complejidad ciclomática y cognitiva:** Se eliminó la indentación excesiva, haciendo que el flujo de ejecución sea lineal y fácil de leer.
2. **Alta cohesión y modularidad:** La verificación de precondiciones reside ahora en su propio método (`esReservaProcesable`), lo que permite probar la lógica de negocio de manera aislada.
3. **Semántica explícita del dominio:** La constante expresiva y los nombres descriptivos eliminan la necesidad de comentarios explicativos.
4. **Trazabilidad de cambios:** El historial de commits en Git documenta transformaciones pequeñas y seguras, conservando la verificación funcional en cada paso.
