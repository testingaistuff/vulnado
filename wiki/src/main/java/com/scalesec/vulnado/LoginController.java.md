# Dokumentation: LoginController

## Übersicht
Die `LoginController`-Klasse ist eine Java-Webanwendung, die mit Spring Boot erstellt wurde. Sie bietet eine Login-Funktionalität, bei der Benutzer ihre Anmeldedaten senden können, um ein Token zu erhalten, das ihre Authentifizierung bestätigt. Die Klasse enthält Logik zur Verarbeitung von Anfragen und zur Validierung von Benutzerdaten.

---

## Datei-Name
`LoginController.java`

---

## Hauptbestandteile

### 1. **LoginController**
Die Hauptklasse `LoginController` ist ein REST-Controller, der die Login-Funktionalität bereitstellt.

#### Eigenschaften:
- **`secret`**: Ein geheimer Schlüssel, der aus der Konfigurationsdatei geladen wird (`@Value("${app.secret}")`). Dieser Schlüssel wird verwendet, um Tokens zu generieren.

#### Methoden:
- **`login(LoginRequest input)`**:
  - **Beschreibung**: Diese Methode verarbeitet eine POST-Anfrage an die `/login`-Route. Sie überprüft die Anmeldedaten des Benutzers und gibt ein Token zurück, wenn die Authentifizierung erfolgreich ist.
  - **Parameter**: 
    - `LoginRequest input`: Ein Objekt, das den Benutzernamen und das Passwort enthält.
  - **Rückgabewert**: Ein `LoginResponse`-Objekt mit einem Token.
  - **Logik**:
    - Holt die Benutzerdaten basierend auf dem Benutzernamen (`User.fetch(input.username)`).
    - Vergleicht das gehashte Passwort mit dem gespeicherten Passwort.
    - Gibt ein Token zurück, wenn die Authentifizierung erfolgreich ist.
    - Wirft eine `Unauthorized`-Ausnahme, wenn die Authentifizierung fehlschlägt.

---

### 2. **LoginRequest**
Eine Datenstruktur, die die Anmeldedaten des Benutzers enthält.

#### Eigenschaften:
- **`username`**: Der Benutzername des Benutzers.
- **`password`**: Das Passwort des Benutzers.

---

### 3. **LoginResponse**
Eine Datenstruktur, die das Token enthält, das nach erfolgreicher Authentifizierung generiert wird.

#### Eigenschaften:
- **`token`**: Das Authentifizierungs-Token.
- **Konstruktor**: Initialisiert das Token mit einer Nachricht.

---

### 4. **Unauthorized**
Eine benutzerdefinierte Ausnahme, die ausgelöst wird, wenn die Authentifizierung fehlschlägt.

#### Eigenschaften:
- **HTTP-Status**: Gibt den Status `401 Unauthorized` zurück.
- **Konstruktor**: Initialisiert die Ausnahme mit einer Nachricht.

---

## Endpunkte

| **Endpunkt** | **HTTP-Methode** | **Beschreibung** | **Eingabe** | **Ausgabe** |
|--------------|------------------|------------------|-------------|-------------|
| `/login`     | POST             | Authentifiziert den Benutzer und gibt ein Token zurück. | `LoginRequest` (JSON) | `LoginResponse` (JSON) |

---

## Insights

- **Sicherheitsaspekt**: Der geheime Schlüssel (`secret`) wird aus der Konfiguration geladen und sollte sicher aufbewahrt werden, da er für die Token-Generierung verwendet wird.
- **Passwort-Hashing**: Die Methode `Postgres.md5()` wird verwendet, um das Passwort zu hashen. Es ist wichtig sicherzustellen, dass die Hashing-Methode sicher und aktuell ist.
- **Fehlerbehandlung**: Die Klasse `Unauthorized` wird verwendet, um Fehler bei der Authentifizierung zu behandeln. Dies sorgt für eine klare und standardisierte Fehlerkommunikation.
- **Cross-Origin Resource Sharing (CORS)**: Die Annotation `@CrossOrigin(origins = "*")` erlaubt Anfragen von allen Ursprüngen. Dies kann ein Sicherheitsrisiko darstellen, wenn nicht richtig konfiguriert.

---

## Abhängigkeiten

| **Abhängigkeit** | **Beschreibung** |
|------------------|------------------|
| `Spring Boot`    | Framework für die Erstellung von Java-Webanwendungen. |
| `Postgres`       | Wird für das Hashing von Passwörtern verwendet. |
| `Serializable`   | Ermöglicht die Serialisierung von Objekten (`LoginRequest`, `LoginResponse`). |

---

## Datenstrukturen vs. Logik

- **Datenstrukturen**:
  - `LoginRequest`: Enthält die Anmeldedaten.
  - `LoginResponse`: Enthält das Authentifizierungs-Token.
- **Logik**:
  - Die Methode `login()` enthält die Logik zur Authentifizierung des Benutzers und zur Token-Generierung.
  - Fehlerbehandlung durch die Klasse `Unauthorized`.
