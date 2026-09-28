# UEES | Diseño de Software | Semana 3

## Actividad 1 – ¿Necesitamos realmente un patrón?

Proyecto base para el mini taller de 30 minutos sobre **Factory Method**.

El proyecto representa deliberadamente una solución inicial que **funciona**,
pero concentra la decisión de creación de notificadores en una clase con
`if/else`.

El objetivo del taller es analizar si existe suficiente variabilidad y
acoplamiento como para justificar una refactorización mediante **Factory Method**.

---

## Requisitos

- Java 25
- Maven 3.9 o superior
- Git
- Opcional: PlantUML o diagrams.net

Verificar:

```bash
java -version
javac -version
mvn -version
git --version
```

---

## Compilar

```bash
mvn clean compile
```

## Ejecutar pruebas

```bash
mvn clean test
```

## Ejecutar la aplicación

```bash
mvn exec:java
```

---

## Estructura

```text
src/main/java/edu/uees/disenosoftware/
├── app/
│   └── Main.java
└── notificacion/
    ├── Notificador.java
    ├── NotificadorEmail.java
    ├── NotificadorPush.java
    ├── NotificadorSMS.java
    ├── CreadorNotificadorSimple.java
    └── ServicioNotificacion.java

src/test/java/...
docs/modelo-inicial.puml
```

---

## Situación inicial

`CreadorNotificadorSimple` contiene:

```java
if ("EMAIL".equalsIgnoreCase(tipo)) {
    return new NotificadorEmail();
}

if ("PUSH".equalsIgnoreCase(tipo)) {
    return new NotificadorPush();
}

if ("SMS".equalsIgnoreCase(tipo)) {
    return new NotificadorSMS();
}
```

Esto permite discutir:

1. ¿Qué cambia?
2. ¿Qué permanece estable?
3. ¿Dónde existe acoplamiento con clases concretas?
4. ¿Qué ocurrirá al agregar `TEAMS` o `WHATSAPP`?
5. ¿Factory Method reduce realmente un costo de cambio en este caso?
6. ¿Qué complejidad nueva introducirá?

---

## Reto del taller

Refactorizar el diseño hacia Factory Method creando, como mínimo:

```text
NotificadorCreator
├── EmailCreator
├── PushCreator
└── SmsCreator
```

Mantener:

```text
Notificador
├── NotificadorEmail
├── NotificadorPush
└── NotificadorSMS
```

Después agregar una nueva variante:

```text
NotificadorTeams
TeamsCreator
```

y responder:

> ¿Qué clases existentes tuvieron que modificarse?

---

## Commits sugeridos

```bash
git init
git add .
git commit -m "chore: crear proyecto base del taller"

git add .
git commit -m "refactor: aplicar factory method a notificadores"

git add .
git commit -m "feat: agregar notificador teams"

git add .
git commit -m "docs: actualizar UML y conclusiones"
```

---

## Evidencia sugerida

- Proyecto que compile.
- `mvn clean test` exitoso.
- UML inicial y UML final.
- Historial de commits.
- Respuesta breve:
  - qué problema existía;
  - qué mejoró;
  - qué costo añadió Factory Method;
  - si el patrón quedó justificado o no.
