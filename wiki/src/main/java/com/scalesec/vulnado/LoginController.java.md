# Dokumentation: LoginController

## Datei-Name
`LoginController.java`

## Übersicht
Diese Datei enthält eine Java-Klasse, die als Controller für die Login-Funktionalität einer Webanwendung dient. Sie verwendet das Spring Framework, um HTTP-Anfragen zu verarbeiten und eine Authentifizierung durchzuführen. Die Klasse ermöglicht es Benutzern, sich mit einem Benutzernamen und einem Passwort anzumelden, und gibt bei erfolgreicher Authentifizierung ein Token zurück.

---

## Hauptbestandteile

### 1. **LoginController**
Die Hauptklasse `LoginController` ist ein REST-Controller, der die Login-Funktionalität bereitstellt.

#### Eigenschaften:
- **`secret`**: Ein geheimer Schlüssel, der aus der Konfigurationsdatei geladen wird (`@Value("${app.secret}")`). Dieser Schlüssel wird verwendet, um ein Token für den Benutzer zu generieren.

#### Methoden:
- **`login(LoginRequest input)`**:
  - **Beschreibung**: Diese Methode verarbeitet eine POST-Anfrage an die URL `/login`. Sie überprüft die Anmeldedaten des Benutzers und gibt ein Token zurück, wenn die Authentifizierung erfolgreich ist.
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
Eine Datenstruktur, die die Antwort auf eine erfolgreiche Anmeldung enthält.

#### Eigenschaften:
- **`token`**: Ein Token, das dem Benutzer nach erfolgreicher Authentifizierung zugewiesen wird.

#### Konstruktor:
- **`LoginResponse(String msg)`**: Initialisiert das Token mit dem übergebenen Wert.

---

### 4. **Unauthorized**
Eine benutzerdefinierte Ausnahme, die geworfen wird, wenn die Authentifizierung fehlschlägt.

#### Eigenschaften:
- **HTTP-Status**: `HttpStatus.UNAUTHORIZED` (401).
- **Konstruktor**: 
  - **`Unauthorized(String exception)`**: Initialisiert die Ausnahme mit einer Fehlermeldung.

---

## Insights

### Technologien und Frameworks:
- **Spring Boot**: Wird verwendet, um die Webanwendung zu erstellen und zu konfigurieren.
- **REST-Controller**: Ermöglicht die Verarbeitung von HTTP-Anfragen.
- **Cross-Origin Resource Sharing (CORS)**: Die Methode `@CrossOrigin(origins = "*")` erlaubt Anfragen von allen Ursprüngen.

### Sicherheitsaspekte:
- **Passwort-Hashing**: Das Passwort wird mit der Funktion `Postgres.md5` gehasht, bevor es überprüft wird.
- **Token-Generierung**: Ein geheimer Schlüssel (`secret`) wird verwendet, um ein Token für den Benutzer zu erstellen.
- **Fehlerbehandlung**: Bei fehlerhafter Authentifizierung wird eine `Unauthorized`-Ausnahme geworfen, die einen HTTP-Status 401 zurückgibt.

### Datenfluss:
1. Der Benutzer sendet eine POST-Anfrage mit Benutzernamen und Passwort.
2. Die Methode `login` überprüft die Anmeldedaten.
3. Bei erfolgreicher Authentifizierung wird ein Token zurückgegeben.
4. Bei fehlerhafter Authentifizierung wird eine Fehlermeldung zurückgegeben.

### Klassenübersicht:
| Klasse            | Typ            | Zweck                                                                 |
|--------------------|----------------|-----------------------------------------------------------------------|
| `LoginController` | Logik          | Verarbeitet Login-Anfragen und führt die Authentifizierung durch.    |
| `LoginRequest`    | Datenstruktur  | Enthält die Anmeldedaten des Benutzers.                              |
| `LoginResponse`   | Datenstruktur  | Enthält das Token nach erfolgreicher Authentifizierung.              |
| `Unauthorized`    | Ausnahme       | Wird bei fehlerhafter Authentifizierung geworfen.                    |

### Endpunkt:
| URL     | HTTP-Methode | Beschreibung                     |
|---------|--------------|-----------------------------------|
| `/login` | POST         | Verarbeitet Login-Anfragen.      |
