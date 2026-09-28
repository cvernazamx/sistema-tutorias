# Análisis de cobertura

## Resultado observado
- Cobertura de líneas:
- Cobertura de ramas:
- Clase o método analizado:

## Huecos relevantes
1.
2.

## Decisiones
- ¿Qué prueba nueva se añadió?
- ¿Qué riesgo protege?
- ¿Por qué no basta con el porcentaje?

# Análisis de Cobertura JaCoCo - Laboratorio 2

### 1. ¿Qué método tiene menor cobertura?
Tras incorporar la totalidad de los casos de prueba (valores límite, excepciones y las 3 pruebas con dobles), tanto `puedeCancelar()`, `calcularTotal()` como `confirmar()` alcanzan el 100% de cobertura en líneas e instrucciones.

### 2. ¿Qué comportamiento falta?
Falta proteger la resiliencia del servicio ante una caída catastrófica de dependencias externas; por ejemplo, si `DisponibilidadClient` arroja un error de red o timeout no controlado.

### 3. ¿Qué prueba nueva aportaría valor?
Una prueba donde el método `disponibilidad.estaDisponible(...)` lance una excepción en tiempo de ejecución (`RuntimeException`), para comprobar que el flujo capture o propague el fallo sin alterar el estado de la reserva ni realizar llamadas a persistencia o notificación.

### 4. ¿Existe código cubierto pero mal probado?
Sí. El hecho de que una línea aparezca en verde en JaCoCo solo indica que se ejecutó, no que su resultado sea el esperado. Si en el Caso 1 no hubiéramos incluido `assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado())`, el método `reserva.confirmar()` estaría 100% cubierto pero su efecto real no estaría validado.
```[cite: 5, 6]

3. Guarda con **`Cmd + S`**.

---

### 5. Registra los commits en Git desde la terminal de VS Code
Ejecuta estos 3 comandos en orden[cite: 5]:

```bash
git add src/test/java/edu/uees/testing/service/ReservaServiceTest.java
git commit -m "test: agregar escenarios con stub y mock para confirmar reserva"
```[cite: 5]

```bash
git add docs/02_ANALISIS_COBERTURA_PLANTILLA.md
git commit -m "docs: analizar cobertura jacoco y registrar huecos de prueba"
```[cite: 5]

```bash
git log --oneline --decorate -n 3
```[cite: 5]

Con este último comando verás tus commits limpios y organizados en la rama `test/lab2-dobles-cobertura`[cite: 5, 6]. 

¿Te salió el `BUILD SUCCESS` al correr `mvn clean test` en la terminal de VS Code[cite: 5]?