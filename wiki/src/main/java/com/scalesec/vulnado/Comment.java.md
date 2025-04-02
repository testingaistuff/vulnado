# Dokumentation: `Comment.java`

## Übersicht
Die Klasse `Comment` repräsentiert Kommentare, die von Benutzern erstellt werden können. Sie bietet Funktionen zum Erstellen, Abrufen, Löschen und Speichern von Kommentaren in einer Datenbank. Diese Klasse verwendet eine PostgreSQL-Datenbank für die Speicherung und Verwaltung der Daten.

---

## Eigenschaften der Klasse

| **Eigenschaft** | **Typ**         | **Beschreibung**                                                                 |
|------------------|-----------------|----------------------------------------------------------------------------------|
| `id`            | `String`       | Eindeutige ID des Kommentars.                                                   |
| `username`      | `String`       | Benutzername des Erstellers des Kommentars.                                     |
| `body`          | `String`       | Inhalt des Kommentars.                                                          |
| `created_on`    | `Timestamp`    | Zeitstempel, wann der Kommentar erstellt wurde.                                 |

---

## Konstruktor

### `Comment(String id, String username, String body, Timestamp created_on)`
- Erstellt eine neue Instanz der Klasse `Comment`.
- **Parameter**:
  - `id`: Eindeutige ID des Kommentars.
  - `username`: Benutzername des Erstellers.
  - `body`: Inhalt des Kommentars.
  - `created_on`: Zeitstempel der Erstellung.

---

## Methoden

### `create(String username, String body)`
- Erstellt einen neuen Kommentar und speichert ihn in der Datenbank.
- **Parameter**:
  - `username`: Benutzername des Erstellers.
  - `body`: Inhalt des Kommentars.
- **Rückgabewert**:
  - Gibt die erstellte `Comment`-Instanz zurück.
- **Fehlerbehandlung**:
  - Wirft eine `BadRequest`-Exception, wenn der Kommentar nicht gespeichert werden kann.
  - Wirft eine `ServerError`-Exception bei anderen Fehlern.

---

### `fetch_all()`
- Ruft alle Kommentare aus der Datenbank ab.
- **Rückgabewert**:
  - Eine Liste von `Comment`-Objekten.
- **Fehlerbehandlung**:
  - Gibt eine leere Liste zurück, wenn ein Fehler auftritt.

---

### `delete(String id)`
- Löscht einen Kommentar anhand seiner ID aus der Datenbank.
- **Parameter**:
  - `id`: Die ID des zu löschenden Kommentars.
- **Rückgabewert**:
  - `true`, wenn der Kommentar erfolgreich gelöscht wurde.
  - `false`, wenn ein Fehler auftritt.

---

### `commit()`
- Speichert die aktuelle Instanz des Kommentars in der Datenbank.
- **Rückgabewert**:
  - `true`, wenn der Kommentar erfolgreich gespeichert wurde.
  - `false`, wenn ein Fehler auftritt.
- **Fehlerbehandlung**:
  - Wirft eine `SQLException`, wenn ein Fehler beim Speichern auftritt.

---

## Insights

1. **UUID für Eindeutigkeit**: Die ID jedes Kommentars wird mithilfe von `UUID.randomUUID()` generiert, um sicherzustellen, dass sie eindeutig ist.
2. **Datenbankintegration**: Die Klasse verwendet SQL-Abfragen, um mit einer PostgreSQL-Datenbank zu interagieren. Dies umfasst das Einfügen, Abrufen und Löschen von Kommentaren.
3. **Fehlerbehandlung**: Die Klasse enthält grundlegende Fehlerbehandlungsmechanismen, um sicherzustellen, dass Probleme bei der Datenbankinteraktion nicht zu Programmabstürzen führen.
4. **Zeitstempel**: Jeder Kommentar wird mit einem Zeitstempel versehen, der den Zeitpunkt der Erstellung angibt.
5. **Flexibilität**: Die Klasse bietet Methoden, um Kommentare dynamisch zu erstellen, abzurufen und zu löschen, was sie für verschiedene Anwendungen nützlich macht.

---

## Hinweise für Kinder
- Diese Klasse hilft dabei, Kommentare zu speichern, die von Benutzern geschrieben werden.
- Sie sorgt dafür, dass jeder Kommentar eine eigene ID hat, damit er nicht verloren geht.
- Wenn du einen Kommentar löschst, wird er aus der Datenbank entfernt.
- Die Klasse kümmert sich darum, dass alles sicher gespeichert wird, auch wenn etwas schiefgeht.
