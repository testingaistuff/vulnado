# Dokumentation: User.java

## Übersicht
Die Klasse `User` repräsentiert einen Benutzer mit grundlegenden Informationen wie ID, Benutzername und Passwort. Sie bietet Funktionen zur Token-Generierung, Authentifizierung und Datenbankabfrage, um Benutzerinformationen zu erhalten.

---

## Eigenschaften der Klasse

| **Eigenschaft** | **Typ**   | **Beschreibung** |
|------------------|-----------|------------------|
| `id`            | `String`  | Die eindeutige ID des Benutzers. |
| `username`      | `String`  | Der Benutzername des Benutzers. |
| `hashedPassword`| `String`  | Das gehashte Passwort des Benutzers. |

---

## Konstruktor

### `User(String id, String username, String hashedPassword)`
- **Beschreibung**: Erstellt eine neue Instanz der `User`-Klasse mit den angegebenen Werten.
- **Parameter**:
  - `id`: Die ID des Benutzers.
  - `username`: Der Benutzername des Benutzers.
  - `hashedPassword`: Das gehashte Passwort des Benutzers.

---

## Methoden

### `String token(String secret)`
- **Beschreibung**: Generiert ein JSON Web Token (JWT) für den Benutzer basierend auf einem geheimen Schlüssel.
- **Parameter**:
  - `secret`: Der geheime Schlüssel, der für die Token-Signatur verwendet wird.
- **Rückgabewert**: Ein signiertes JWT als `String`.

---

### `static void assertAuth(String secret, String token)`
- **Beschreibung**: Überprüft die Authentizität eines Tokens basierend auf einem geheimen Schlüssel.
- **Parameter**:
  - `secret`: Der geheime Schlüssel, der für die Token-Validierung verwendet wird.
  - `token`: Das zu überprüfende Token.
- **Fehlerbehandlung**: Wirft eine `Unauthorized`-Ausnahme, wenn die Authentifizierung fehlschlägt.

---

### `static User fetch(String un)`
- **Beschreibung**: Ruft die Benutzerinformationen aus der Datenbank ab, basierend auf dem angegebenen Benutzernamen.
- **Parameter**:
  - `un`: Der Benutzername des Benutzers, der abgefragt werden soll.
- **Rückgabewert**: Eine Instanz der `User`-Klasse mit den abgerufenen Informationen oder `null`, wenn kein Benutzer gefunden wurde.
- **Datenbankinteraktion**:
  - Führt eine SQL-Abfrage aus, um die Benutzerinformationen zu erhalten.
  - Verwendet die Methode `Postgres.connection()` für die Datenbankverbindung.

---

## Insights

1. **Sicherheitsrisiken**:
   - Die Methode `fetch` verwendet eine unsichere SQL-Abfrage mit String-Konkatenation, was zu SQL-Injection führen kann. Es wird empfohlen, vorbereitete Anweisungen (`PreparedStatement`) zu verwenden.
   - Die Methode `assertAuth` fängt Ausnahmen ab, gibt jedoch die Fehlermeldung direkt aus. Dies könnte sensible Informationen preisgeben.

2. **Token-Generierung**:
   - Die Token-Generierung verwendet den HMAC-SHA-Algorithmus, der sicher ist, wenn der geheime Schlüssel stark genug ist.

3. **Datenbankverbindung**:
   - Die Methode `fetch` schließt die Datenbankverbindung, aber es gibt keine Garantie, dass die Verbindung immer geschlossen wird, da dies nicht in einem `finally`-Block erfolgt.

4. **Fehlerbehandlung**:
   - Die Fehlerbehandlung in der Methode `fetch` gibt die Fehlermeldung auf der Konsole aus, was für Produktionsumgebungen nicht ideal ist.

---

## Abhängigkeiten

| **Bibliothek** | **Beschreibung** |
|-----------------|------------------|
| `io.jsonwebtoken` | Wird für die JWT-Erstellung und -Validierung verwendet. |
| `javax.crypto`    | Wird für die Erstellung des geheimen Schlüssels verwendet. |
| `java.sql`        | Wird für die Datenbankinteraktion verwendet. |

---

## Hinweise für Kinder
- Ein Token ist wie ein magischer Schlüssel, der zeigt, dass du jemand Bestimmtes bist.
- Die Datenbank ist wie ein großer Schrank, in dem Informationen über Benutzer gespeichert sind.
- Sicherheit ist wichtig! Man sollte immer darauf achten, dass niemand etwas Böses mit den Informationen machen kann.
