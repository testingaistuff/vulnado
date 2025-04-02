# Dokumentation: CommentsController

## Datei-Name
`CommentsController.java`

## Übersicht
Die `CommentsController`-Klasse ist ein REST-Controller, der HTTP-Anfragen für Kommentare verarbeitet. Sie ermöglicht das Abrufen, Erstellen und Löschen von Kommentaren. Die Klasse verwendet Spring Boot und bietet Funktionen wie Authentifizierung und Fehlerbehandlung.

---

## Hauptfunktionen

### 1. **Kommentare abrufen**
- **Endpunkt:** `/comments`
- **HTTP-Methode:** `GET`
- **Beschreibung:** Gibt eine Liste aller Kommentare zurück.
- **Header:** 
  - `x-auth-token`: Authentifizierungstoken.
- **Rückgabewert:** Eine Liste von Kommentaren.
- **Sicherheitsmechanismus:** 
  - Die Methode überprüft die Authentifizierung des Benutzers mit einem geheimen Schlüssel (`secret`).

---

### 2. **Kommentar erstellen**
- **Endpunkt:** `/comments`
- **HTTP-Methode:** `POST`
- **Beschreibung:** Erstellt einen neuen Kommentar.
- **Header:** 
  - `x-auth-token`: Authentifizierungstoken.
- **Body:** 
  - `username`: Der Name des Benutzers, der den Kommentar erstellt.
  - `body`: Der Inhalt des Kommentars.
- **Rückgabewert:** Das erstellte Kommentarobjekt.

---

### 3. **Kommentar löschen**
- **Endpunkt:** `/comments/{id}`
- **HTTP-Methode:** `DELETE`
- **Beschreibung:** Löscht einen Kommentar basierend auf seiner ID.
- **Header:** 
  - `x-auth-token`: Authentifizierungstoken.
- **Pfadvariable:** 
  - `id`: Die ID des zu löschenden Kommentars.
- **Rückgabewert:** `true`, wenn der Kommentar erfolgreich gelöscht wurde, andernfalls `false`.

---

## Datenstrukturen

### 1. **CommentRequest**
- **Beschreibung:** Eine Klasse, die die Datenstruktur für die Erstellung eines Kommentars definiert.
- **Attribute:**
  | Attribut   | Typ       | Beschreibung                     |
  |------------|-----------|----------------------------------|
  | `username` | `String`  | Der Name des Benutzers.          |
  | `body`     | `String`  | Der Inhalt des Kommentars.       |

---

### 2. **Fehlerbehandlung**
Die Klasse definiert zwei benutzerdefinierte Fehlerklassen:
- **BadRequest:** Wird ausgelöst, wenn eine ungültige Anfrage gestellt wird.
- **ServerError:** Wird ausgelöst, wenn ein interner Serverfehler auftritt.

| Fehlerklasse   | HTTP-Statuscode | Beschreibung                          |
|----------------|-----------------|---------------------------------------|
| `BadRequest`   | `400`           | Ungültige Anfrage.                   |
| `ServerError`  | `500`           | Interner Serverfehler.               |

---

## Insights
- **Sicherheitsaspekt:** Die Authentifizierung erfolgt über ein Token (`x-auth-token`), das mit einem geheimen Schlüssel (`secret`) validiert wird.
- **Flexibilität:** Die Verwendung von `@CrossOrigin(origins = "*")` ermöglicht Anfragen von allen Ursprüngen, was für die Integration mit Frontend-Anwendungen hilfreich ist.
- **Fehlerbehandlung:** Die benutzerdefinierten Fehlerklassen sorgen für eine klare Kommunikation von Problemen an den Benutzer.
- **Modularität:** Die Klasse ist gut strukturiert und trennt die Logik für Kommentare und Fehlerbehandlung.
