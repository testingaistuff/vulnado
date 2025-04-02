# Dokumentation: Postgres.java

## Übersicht
Die Datei `Postgres.java` enthält eine Java-Klasse, die für die Interaktion mit einer PostgreSQL-Datenbank entwickelt wurde. Sie bietet Funktionen zur Einrichtung der Datenbank, zum Einfügen von Benutzern und Kommentaren sowie zur Berechnung von MD5-Hashwerten. Diese Klasse verwendet JDBC (Java Database Connectivity), um die Verbindung zur Datenbank herzustellen und SQL-Befehle auszuführen.

---

## Funktionen und Methoden

### `connection()`
- **Beschreibung**: Stellt eine Verbindung zur PostgreSQL-Datenbank her.
- **Details**:
  - Verwendet Umgebungsvariablen (`PGHOST`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`), um die Verbindungsdetails zu konfigurieren.
  - Lädt den PostgreSQL-Treiber und stellt die Verbindung her.
  - Gibt ein `Connection`-Objekt zurück.
  - Behandelt Fehler durch Ausgabe einer Fehlermeldung und Beenden des Programms.

---

### `setup()`
- **Beschreibung**: Richtet die Datenbank ein, indem sie Tabellen erstellt, bestehende Daten löscht und Beispiel-Daten einfügt.
- **Details**:
  - Erstellt zwei Tabellen:
    - `users`: Speichert Benutzerinformationen.
    - `comments`: Speichert Kommentare.
  - Löscht alle vorhandenen Daten in den Tabellen.
  - Fügt Beispiel-Benutzer und Kommentare ein:
    - Benutzer: `admin`, `alice`, `bob`, `eve`, `rick`.
    - Kommentare: `rick` und `alice`.
  - Verwendet die Methoden `insertUser()` und `insertComment()` für das Einfügen von Daten.

---

### `md5(String input)`
- **Beschreibung**: Berechnet den MD5-Hashwert eines gegebenen Strings.
- **Details**:
  - Verwendet die Klasse `MessageDigest`, um den MD5-Hash zu berechnen.
  - Konvertiert den Hash in eine hexadezimale Darstellung.
  - Füllt den Hash mit führenden Nullen auf, um eine Länge von 32 Zeichen zu erreichen.
- **Anwendung**: Wird verwendet, um Passwörter vor der Speicherung in der Datenbank zu verschlüsseln.

---

### `insertUser(String username, String password)`
- **Beschreibung**: Fügt einen neuen Benutzer in die `users`-Tabelle ein.
- **Details**:
  - Generiert eine eindeutige Benutzer-ID (`UUID`).
  - Verschlüsselt das Passwort mit der `md5()`-Methode.
  - Speichert den Benutzernamen, das verschlüsselte Passwort und den Zeitstempel der Erstellung in der Datenbank.

---

### `insertComment(String username, String body)`
- **Beschreibung**: Fügt einen neuen Kommentar in die `comments`-Tabelle ein.
- **Details**:
  - Generiert eine eindeutige Kommentar-ID (`UUID`).
  - Speichert den Benutzernamen, den Kommentartext und den Zeitstempel der Erstellung in der Datenbank.

---

## Datenbankstruktur

### Tabelle: `users`
| Spaltenname   | Datentyp         | Beschreibung                          |
|---------------|------------------|---------------------------------------|
| `user_id`     | `VARCHAR(36)`    | Eindeutige ID des Benutzers.          |
| `username`    | `VARCHAR(50)`    | Benutzername, muss eindeutig sein.    |
| `password`    | `VARCHAR(50)`    | Verschlüsseltes Passwort des Benutzers. |
| `created_on`  | `TIMESTAMP`      | Zeitstempel der Erstellung.           |
| `last_login`  | `TIMESTAMP`      | Zeitstempel der letzten Anmeldung.    |

### Tabelle: `comments`
| Spaltenname   | Datentyp         | Beschreibung                          |
|---------------|------------------|---------------------------------------|
| `id`          | `VARCHAR(36)`    | Eindeutige ID des Kommentars.         |
| `username`    | `VARCHAR(36)`    | Benutzername des Kommentators.        |
| `body`        | `VARCHAR(500)`   | Inhalt des Kommentars.                |
| `created_on`  | `TIMESTAMP`      | Zeitstempel der Erstellung.           |

---

## Beispiel-Daten
### Benutzer
| Benutzername | Passwort              |
|--------------|-----------------------|
| admin        | !!SuperSecretAdmin!!  |
| alice        | AlicePassword!        |
| bob          | BobPassword!          |
| eve          | $EVELknev^l           |
| rick         | !GetSchwifty!         |

### Kommentare
| Benutzername | Kommentar            |
|--------------|----------------------|
| rick         | cool dog m8          |
| alice        | OMG so cute!         |

---

## Einblicke
- **Sicherheitsaspekt**: Passwörter werden vor der Speicherung mit MD5 verschlüsselt. MD5 ist jedoch nicht mehr als sicherer Algorithmus für Passwörter geeignet, da er anfällig für Angriffe ist. Es wird empfohlen, sicherere Algorithmen wie bcrypt oder Argon2 zu verwenden.
- **Datenbankbereinigung**: Die Methode `setup()` löscht alle vorhandenen Daten in den Tabellen, bevor neue Daten eingefügt werden. Dies ist nützlich für Entwicklungs- und Testzwecke, sollte jedoch in einer Produktionsumgebung mit Vorsicht verwendet werden.
- **UUIDs**: Eindeutige IDs werden für Benutzer und Kommentare verwendet, um Kollisionen zu vermeiden.
- **Fehlerbehandlung**: Fehler werden ausgegeben, aber die Anwendung beendet sich bei schwerwiegenden Problemen. Eine robustere Fehlerbehandlung könnte die Benutzerfreundlichkeit verbessern.
