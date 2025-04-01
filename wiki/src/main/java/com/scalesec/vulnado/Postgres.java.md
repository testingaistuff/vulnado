# Dokumentation: Postgres.java

## Übersicht
Die Klasse `Postgres` ist eine Java-Anwendung, die mit einer PostgreSQL-Datenbank interagiert. Sie bietet Funktionen zur Einrichtung der Datenbank, zum Einfügen von Benutzern und Kommentaren sowie zur Berechnung von MD5-Hashwerten. Diese Klasse verwendet Umgebungsvariablen, um die Verbindung zur Datenbank herzustellen.

---

## Funktionen

### `connection()`
Diese Methode stellt eine Verbindung zur PostgreSQL-Datenbank her. Sie verwendet Umgebungsvariablen, um die Verbindungsdetails wie Host, Datenbankname, Benutzername und Passwort zu erhalten.

#### Eingaben:
- Keine direkten Eingaben. Die Methode greift auf folgende Umgebungsvariablen zu:
  - `PGHOST`: Hostname der Datenbank.
  - `PGDATABASE`: Name der Datenbank.
  - `PGUSER`: Benutzername.
  - `PGPASSWORD`: Passwort.

#### Ausgaben:
- Gibt ein `Connection`-Objekt zurück, das die Verbindung zur Datenbank repräsentiert.

---

### `setup()`
Diese Methode richtet die Datenbank ein, indem sie Tabellen erstellt, bestehende Daten löscht und Beispiel-Daten einfügt.

#### Schritte:
1. **Tabellen erstellen**:
   - `users`: Speichert Benutzerinformationen.
   - `comments`: Speichert Kommentare.
   
2. **Daten löschen**:
   - Löscht alle vorhandenen Daten aus den Tabellen `users` und `comments`.

3. **Beispieldaten einfügen**:
   - Fügt vordefinierte Benutzer und Kommentare ein.

#### Beispiel-Daten:
| Benutzername | Passwort              |
|--------------|-----------------------|
| admin        | !!SuperSecretAdmin!! |
| alice        | AlicePassword!       |
| bob          | BobPassword!         |
| eve          | $EVELknev^l          |
| rick         | !GetSchwifty!        |

| Benutzername | Kommentar         |
|--------------|-------------------|
| rick         | cool dog m8       |
| alice        | OMG so cute!      |

---

### `md5(String input)`
Diese Methode berechnet den MD5-Hashwert eines gegebenen Strings.

#### Eingaben:
- `input`: Der String, dessen MD5-Hashwert berechnet werden soll.

#### Ausgaben:
- Gibt den MD5-Hashwert des Eingabe-Strings als Hexadezimal-String zurück.

#### Besonderheiten:
- Falls der Hashwert kürzer als 32 Zeichen ist, wird er mit führenden Nullen aufgefüllt.

---

### `insertUser(String username, String password)`
Diese Methode fügt einen neuen Benutzer in die `users`-Tabelle ein.

#### Eingaben:
- `username`: Der Benutzername des neuen Benutzers.
- `password`: Das Passwort des neuen Benutzers (wird als MD5-Hash gespeichert).

#### Schritte:
1. Generiert eine eindeutige Benutzer-ID (`UUID`).
2. Berechnet den MD5-Hash des Passworts.
3. Fügt die Daten in die Tabelle ein.

---

### `insertComment(String username, String body)`
Diese Methode fügt einen neuen Kommentar in die `comments`-Tabelle ein.

#### Eingaben:
- `username`: Der Benutzername des Kommentators.
- `body`: Der Text des Kommentars.

#### Schritte:
1. Generiert eine eindeutige Kommentar-ID (`UUID`).
2. Fügt die Daten in die Tabelle ein.

---

## Datenstrukturen

### Tabellenstruktur: `users`
| Spaltenname   | Datentyp         | Beschreibung                          |
|---------------|------------------|---------------------------------------|
| user_id       | VARCHAR (36)     | Eindeutige ID des Benutzers.          |
| username      | VARCHAR (50)     | Benutzername (muss eindeutig sein).   |
| password      | VARCHAR (50)     | MD5-Hash des Passworts.               |
| created_on    | TIMESTAMP        | Zeitpunkt der Erstellung.             |
| last_login    | TIMESTAMP        | Zeitpunkt der letzten Anmeldung.      |

### Tabellenstruktur: `comments`
| Spaltenname   | Datentyp         | Beschreibung                          |
|---------------|------------------|---------------------------------------|
| id            | VARCHAR (36)     | Eindeutige ID des Kommentars.         |
| username      | VARCHAR (36)     | Benutzername des Kommentators.        |
| body          | VARCHAR (500)    | Inhalt des Kommentars.                |
| created_on    | TIMESTAMP        | Zeitpunkt der Erstellung.             |

---

## Einblicke

1. **Sicherheitsaspekte**:
   - Passwörter werden als MD5-Hash gespeichert. MD5 ist jedoch nicht mehr als sicherer Algorithmus für Passwörter geeignet, da er anfällig für Kollisionen und Brute-Force-Angriffe ist. Es wird empfohlen, sicherere Hashing-Algorithmen wie bcrypt oder Argon2 zu verwenden.

2. **Datenbankverbindung**:
   - Die Verbindung zur Datenbank basiert auf Umgebungsvariablen. Dies ist eine gute Praxis, da sensible Informationen nicht direkt im Code gespeichert werden.

3. **Fehlerbehandlung**:
   - Die Fehlerbehandlung ist rudimentär und beendet die Anwendung bei einem Fehler. Eine robustere Fehlerbehandlung könnte die Benutzerfreundlichkeit verbessern.

4. **Beispieldaten**:
   - Die vordefinierten Benutzernamen und Passwörter sind nicht sicher und sollten nur für Testzwecke verwendet werden.

5. **Tabellenstruktur**:
   - Die Tabellenstruktur ist einfach und klar, jedoch fehlt eine Referenz zwischen `users` und `comments`, um die Beziehung zwischen Benutzern und ihren Kommentaren zu sichern.
