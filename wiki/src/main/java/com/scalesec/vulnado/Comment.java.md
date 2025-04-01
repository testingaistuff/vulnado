# Dokumentation: `Comment.java`

## Übersicht
Die Klasse `Comment` repräsentiert Kommentare, die von Benutzern erstellt werden können. Sie bietet Funktionen zum Erstellen, Abrufen, Löschen und Speichern von Kommentaren in einer Datenbank. Diese Klasse verwendet JDBC für die Interaktion mit einer PostgreSQL-Datenbank.

---

## Eigenschaften der Klasse

| **Eigenschaft** | **Typ**         | **Beschreibung**                                                                 |
|------------------|-----------------|----------------------------------------------------------------------------------|
| `id`            | `String`       | Eindeutige ID des Kommentars.                                                   |
| `username`      | `String`       | Benutzername des Autors des Kommentars.                                         |
| `body`          | `String`       | Inhalt des Kommentars.                                                          |
| `created_on`    | `Timestamp`    | Zeitstempel, wann der Kommentar erstellt wurde.                                 |

---

## Konstruktoren

### `Comment(String id, String username, String body, Timestamp created_on)`
- Erstellt eine neue Instanz der Klasse `Comment`.
- **Parameter**:
  - `id`: Eindeutige ID des Kommentars.
  - `username`: Benutzername des Autors.
  - `body`: Inhalt des Kommentars.
  - `created_on`: Zeitstempel der Erstellung.

---

## Methoden

### `static Comment create(String username, String body)`
- Erstellt einen neuen Kommentar und speichert ihn in der Datenbank.
- **Parameter**:
  - `username`: Benutzername des Autors.
  - `body`: Inhalt des Kommentars.
- **Rückgabewert**: Ein `Comment`-Objekt, wenn der Kommentar erfolgreich gespeichert wurde.
- **Fehlerbehandlung**:
  - Wirft `BadRequest`, wenn der Kommentar nicht gespeichert werden kann.
  - Wirft `ServerError`, wenn ein unerwarteter Fehler auftritt.

---

### `static List<Comment> fetch_all()`
- Ruft alle Kommentare aus der Datenbank ab.
- **Rückgabewert**: Eine Liste von `Comment`-Objekten.
- **Fehlerbehandlung**:
  - Gibt eine leere Liste zurück, wenn ein Fehler auftritt.

---

### `static Boolean delete(String id)`
- Löscht einen Kommentar anhand seiner ID.
- **Parameter**:
  - `id`: Die ID des zu löschenden Kommentars.
- **Rückgabewert**: `true`, wenn der Kommentar erfolgreich gelöscht wurde, andernfalls `false`.

---

### `private Boolean commit()`
- Speichert den aktuellen Kommentar in der Datenbank.
- **Rückgabewert**: `true`, wenn der Kommentar erfolgreich gespeichert wurde, andernfalls `false`.
- **Fehlerbehandlung**:
  - Wirft eine `SQLException`, wenn ein Fehler beim Speichern auftritt.

---

## Einblicke

1. **UUID für eindeutige IDs**: Die Klasse verwendet `UUID.randomUUID()` zur Generierung einer eindeutigen ID für jeden Kommentar. Dies stellt sicher, dass keine zwei Kommentare dieselbe ID haben.

2. **Zeitstempel**: Der Zeitstempel (`created_on`) wird automatisch beim Erstellen eines Kommentars generiert, um den Zeitpunkt der Erstellung zu speichern.

3. **Datenbankinteraktion**:
   - Die Klasse verwendet JDBC für die Kommunikation mit einer PostgreSQL-Datenbank.
   - SQL-Befehle wie `INSERT`, `SELECT` und `DELETE` werden verwendet, um Kommentare zu speichern, abzurufen und zu löschen.

4. **Fehlerbehandlung**:
   - Die Klasse behandelt Fehler durch Ausnahmen (`Exception`) und gibt hilfreiche Fehlermeldungen aus.
   - Es gibt benutzerdefinierte Fehlerklassen wie `BadRequest` und `ServerError`, um spezifische Probleme zu kennzeichnen.

5. **Flexibilität**:
   - Die Methode `fetch_all` ermöglicht das Abrufen aller Kommentare, während `delete` gezielt einzelne Kommentare entfernt.
   - Die Klasse ist so gestaltet, dass sie leicht erweitert werden kann, z. B. durch Hinzufügen weiterer Funktionen wie das Aktualisieren von Kommentaren.

---

## Abhängigkeiten

| **Abhängigkeit**         | **Beschreibung**                                                                 |
|---------------------------|----------------------------------------------------------------------------------|
| `java.sql.*`             | Wird für die Datenbankinteraktion verwendet.                                     |
| `java.util.*`            | Wird für die Generierung von UUIDs und die Arbeit mit Zeitstempeln verwendet.    |
| `Postgres.connection()`  | Eine Methode, die eine Verbindung zur PostgreSQL-Datenbank herstellt.            |
| `BadRequest`             | Benutzerdefinierte Ausnahme für fehlerhafte Anfragen.                            |
| `ServerError`            | Benutzerdefinierte Ausnahme für Serverfehler.                                    |

---

## Hinweise für Kinder

- Diese Klasse hilft dabei, Kommentare zu speichern, abzurufen und zu löschen, ähnlich wie bei einem Blog oder einer Webseite.
- Jeder Kommentar hat eine eindeutige ID, damit er nicht verloren geht oder verwechselt wird.
- Die Datenbank ist wie ein großer Speicher, in dem alle Kommentare sicher aufbewahrt werden.
