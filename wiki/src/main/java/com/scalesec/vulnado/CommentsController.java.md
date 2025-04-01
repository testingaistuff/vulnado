# Dokumentation: CommentsController

## Übersicht

Die Datei `CommentsController.java` enthält eine Java-Klasse, die als REST-Controller für die Verwaltung von Kommentaren dient. Sie verwendet das Spring Framework, um HTTP-Anfragen zu verarbeiten. Der Controller bietet Funktionen zum Abrufen, Erstellen und Löschen von Kommentaren. Außerdem gibt es Hilfsklassen für die Verarbeitung von Anfragen und die Behandlung von Fehlern.

---

## Funktionen

### 1. **Abrufen von Kommentaren**
   - **Pfad:** `/comments`
   - **HTTP-Methode:** `GET`
   - **Beschreibung:** Gibt eine Liste aller Kommentare zurück.
   - **Header:** 
     - `x-auth-token` (erforderlich): Authentifizierungstoken.
   - **Rückgabewert:** Eine Liste von Kommentaren im JSON-Format.
   - **Besonderheiten:** 
     - Die Methode überprüft die Authentifizierung des Benutzers mithilfe eines geheimen Schlüssels (`secret`).

---

### 2. **Erstellen eines Kommentars**
   - **Pfad:** `/comments`
   - **HTTP-Methode:** `POST`
   - **Beschreibung:** Erstellt einen neuen Kommentar.
   - **Header:** 
     - `x-auth-token` (erforderlich): Authentifizierungstoken.
   - **Body:** 
     - Ein JSON-Objekt mit den folgenden Feldern:
       | Feldname   | Typ     | Beschreibung                  |
       |------------|---------|------------------------------|
       | `username` | String  | Der Name des Benutzers.      |
       | `body`     | String  | Der Inhalt des Kommentars.   |
   - **Rückgabewert:** Der erstellte Kommentar im JSON-Format.

---

### 3. **Löschen eines Kommentars**
   - **Pfad:** `/comments/{id}`
   - **HTTP-Methode:** `DELETE`
   - **Beschreibung:** Löscht einen Kommentar anhand seiner ID.
   - **Header:** 
     - `x-auth-token` (erforderlich): Authentifizierungstoken.
   - **Pfadvariable:** 
     - `id` (erforderlich): Die ID des zu löschenden Kommentars.
   - **Rückgabewert:** Ein `Boolean`, das angibt, ob der Kommentar erfolgreich gelöscht wurde.

---

## Datenstrukturen

### 1. **CommentRequest**
   - **Beschreibung:** Eine Hilfsklasse, die die Datenstruktur für die Erstellung eines Kommentars definiert.
   - **Felder:**
     | Feldname   | Typ     | Beschreibung                  |
     |------------|---------|------------------------------|
     | `username` | String  | Der Name des Benutzers.      |
     | `body`     | String  | Der Inhalt des Kommentars.   |

---

### 2. **BadRequest**
   - **Beschreibung:** Eine benutzerdefinierte Ausnahme, die bei fehlerhaften Anfragen ausgelöst wird.
   - **HTTP-Status:** `400 Bad Request`

---

### 3. **ServerError**
   - **Beschreibung:** Eine benutzerdefinierte Ausnahme, die bei internen Serverfehlern ausgelöst wird.
   - **HTTP-Status:** `500 Internal Server Error`

---

## Insights

- **Sicherheitsaspekt:** Die Authentifizierung erfolgt über einen geheimen Schlüssel (`secret`) und ein Authentifizierungstoken (`x-auth-token`). Dies stellt sicher, dass nur autorisierte Benutzer auf die Funktionen zugreifen können.
- **CORS-Unterstützung:** Alle Endpunkte sind für Cross-Origin-Anfragen freigegeben (`@CrossOrigin(origins = "*")`).
- **Fehlerbehandlung:** Es gibt benutzerdefinierte Ausnahmen für häufige Fehlerfälle, wie z. B. fehlerhafte Anfragen und Serverfehler.
- **Flexibilität:** Die Verwendung von JSON für Anfragen und Antworten macht die API flexibel und leicht integrierbar.
- **Modularität:** Die Trennung von Datenstrukturen (z. B. `CommentRequest`) und Logik (z. B. `CommentsController`) sorgt für eine klare Struktur und bessere Wartbarkeit.
