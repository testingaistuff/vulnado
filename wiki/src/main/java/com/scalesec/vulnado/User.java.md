# Dokumentation: User.java

## Übersicht
Die Klasse `User` repräsentiert einen Benutzer mit grundlegenden Informationen wie ID, Benutzername und einem verschlüsselten Passwort. Sie bietet Funktionen zur Token-Erstellung, Authentifizierung und Datenbankabfrage, um Benutzerinformationen abzurufen.

---

## Eigenschaften der Klasse

| **Eigenschaft** | **Typ**   | **Beschreibung** |
|-----------------|-----------|------------------|
| `id`           | `String`  | Die eindeutige ID des Benutzers. |
| `username`     | `String`  | Der Benutzername des Benutzers. |
| `hashedPassword` | `String` | Das verschlüsselte Passwort des Benutzers. |

---

## Konstruktor

### `User(String id, String username, String hashedPassword)`
- **Beschreibung**: Erstellt eine neue Instanz der `User`-Klasse mit den angegebenen Werten.
- **Parameter**:
  - `id`: Die eindeutige ID des Benutzers.
  - `username`: Der Benutzername des Benutzers.
  - `hashedPassword`: Das verschlüsselte Passwort des Benutzers.

---

## Methoden

### `String token(String secret)`
- **Beschreibung**: Erstellt ein JSON Web Token (JWT) für den Benutzer basierend auf einem geheimen Schlüssel.
- **Parameter**:
  - `secret`: Ein geheimer Schlüssel, der für die Token-Erstellung verwendet wird.
- **Rückgabewert**: Ein JWT-Token als `String`.

---

### `static void assertAuth(String secret, String token)`
- **Beschreibung**: Überprüft die Authentizität eines gegebenen Tokens mit einem geheimen Schlüssel.
- **Parameter**:
  - `secret`: Der geheime Schlüssel, der zur Überprüfung verwendet wird.
  - `token`: Das zu überprüfende Token.
- **Fehlerbehandlung**: Wirft eine `Unauthorized`-Ausnahme, wenn die Authentifizierung fehlschlägt.

---

### `static User fetch(String un)`
- **Beschreibung**: Ruft die Benutzerinformationen aus der Datenbank ab, basierend auf dem angegebenen Benutzernamen.
- **Parameter**:
  - `un`: Der Benutzername des Benutzers, dessen Informationen abgerufen werden sollen.
- **Rückgabewert**: Eine Instanz der `User`-Klasse mit den abgerufenen Informationen oder `null`, wenn kein Benutzer gefunden wurde.
- **Fehlerbehandlung**: Gibt Fehlerdetails aus, wenn die Datenbankabfrage fehlschlägt.

---

## Insights

### Sicherheitsaspekte
1. **SQL-Injection-Risiko**: Die Methode `fetch` verwendet eine unsichere String-Konkatenation für die SQL-Abfrage. Dies kann zu SQL-Injection führen, wenn der Benutzername nicht ordnungsgemäß validiert wird. Es wird empfohlen, vorbereitete Statements (`PreparedStatement`) zu verwenden.
2. **Token-Sicherheit**: Die Token-Erstellung und -Überprüfung verwenden HMAC-SHA-Algorithmen, was eine sichere Methode ist. Der geheime Schlüssel sollte jedoch sicher gespeichert und nicht leicht zugänglich sein.

### Datenbankverbindung
- Die Methode `fetch` öffnet eine Datenbankverbindung, schließt sie jedoch nicht ordnungsgemäß im Fehlerfall. Es wird empfohlen, `try-with-resources` zu verwenden, um sicherzustellen, dass die Verbindung immer geschlossen wird.

### Fehlerbehandlung
- Die Methode `assertAuth` gibt detaillierte Fehler aus, was hilfreich für Debugging ist, aber potenziell sensible Informationen preisgeben könnte. Es wird empfohlen, die Fehlerausgabe zu minimieren.

### Verwendung von Bibliotheken
- Die Klasse verwendet die Bibliothek `io.jsonwebtoken` für die Token-Verwaltung und `javax.crypto` für die Schlüsselgenerierung. Diese Bibliotheken sind leistungsstark und weit verbreitet für Sicherheitsanwendungen.

---

## Mögliche Verbesserungen
- **Sicherheitsverbesserung**: Implementierung von vorbereiteten Statements in der `fetch`-Methode.
- **Ressourcenmanagement**: Verwendung von `try-with-resources` für die Datenbankverbindung.
- **Fehlerausgabe**: Begrenzung der Fehlerdetails, um sensible Informationen zu schützen.
