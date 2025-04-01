# Dokumentation: LinksController

## Übersicht

Die `LinksController`-Klasse ist ein REST-Controller, der zwei Endpunkte bereitstellt, um Links von einer angegebenen URL zu extrahieren. Sie verwendet die Spring Boot-Framework-Funktionen und bietet JSON-Antworten. Die Klasse enthält zwei Methoden, die jeweils unterschiedliche Versionen der Link-Extraktion verwenden.

---

## Klassenname

**`LinksController`**

### Annotationen
- **`@RestController`**: Markiert die Klasse als REST-Controller, der HTTP-Anfragen verarbeitet und JSON-Antworten zurückgibt.
- **`@EnableAutoConfiguration`**: Aktiviert die automatische Konfiguration von Spring Boot.

---

## Endpunkte

### 1. **`/links`**
- **HTTP-Methode**: GET
- **Beschreibung**: Extrahiert Links von einer angegebenen URL und gibt sie als JSON-Liste zurück.
- **Parameter**:
  - **`url`** (String): Die URL, von der die Links extrahiert werden sollen.
- **Rückgabewert**: Eine Liste von Strings, die die extrahierten Links darstellen.
- **Fehlerbehandlung**: Kann eine `IOException` auslösen, wenn ein Problem beim Zugriff auf die URL auftritt.

### 2. **`/links-v2`**
- **HTTP-Methode**: GET
- **Beschreibung**: Eine erweiterte Version des `/links`-Endpunkts, die Links von einer angegebenen URL extrahiert.
- **Parameter**:
  - **`url`** (String): Die URL, von der die Links extrahiert werden sollen.
- **Rückgabewert**: Eine Liste von Strings, die die extrahierten Links darstellen.
- **Fehlerbehandlung**: Kann eine benutzerdefinierte `BadRequest`-Ausnahme auslösen, wenn die Anfrage ungültig ist.

---

## Abhängigkeiten

- **`LinkLister`**: Eine externe Klasse, die die Methoden `getLinks` und `getLinksV2` bereitstellt, um Links von einer URL zu extrahieren. Die Implementierung dieser Klasse ist nicht in der Datei enthalten.

---

## Einblicke

- Die Klasse ist darauf ausgelegt, HTTP-Anfragen zu verarbeiten und JSON-Antworten bereitzustellen.
- Es gibt zwei Versionen der Link-Extraktion, was darauf hindeutet, dass die zweite Version (`linksV2`) möglicherweise zusätzliche oder verbesserte Funktionen bietet.
- Die Fehlerbehandlung unterscheidet sich zwischen den beiden Endpunkten: Während `/links` eine allgemeine `IOException` auslöst, verwendet `/links-v2` eine spezifische `BadRequest`-Ausnahme.
- Die Klasse ist modular aufgebaut und delegiert die eigentliche Logik der Link-Extraktion an die `LinkLister`-Klasse.
