# Dokumentation: LinkLister

## Dateiname
`LinkLister.java`

---

## Beschreibung
Die Klasse `LinkLister` bietet Funktionen, um alle Links von einer Webseite zu extrahieren. Sie verwendet die Bibliothek `Jsoup`, um HTML-Dokumente zu analysieren und Links zu identifizieren. Zusätzlich gibt es eine erweiterte Methode, die sicherstellt, dass keine privaten IP-Adressen verwendet werden.

---

## Funktionen

### `getLinks(String url)`
Diese Methode extrahiert alle Links von einer angegebenen Webseite.

#### Parameter:
- `url`: Die URL der Webseite, von der die Links extrahiert werden sollen.

#### Rückgabewert:
- Eine Liste von Strings (`List<String>`), die die absoluten URLs der Links auf der Webseite enthält.

#### Funktionsweise:
1. Verbindet sich mit der angegebenen URL und lädt das HTML-Dokument.
2. Sucht alle `<a>`-Tags im Dokument.
3. Extrahiert die absolute URL (`href`) jedes Links und fügt sie der Ergebnisliste hinzu.

---

### `getLinksV2(String url)`
Diese Methode ist eine erweiterte Version von `getLinks`, die zusätzliche Sicherheitsprüfungen durchführt.

#### Parameter:
- `url`: Die URL der Webseite, von der die Links extrahiert werden sollen.

#### Rückgabewert:
- Eine Liste von Strings (`List<String>`), die die absoluten URLs der Links auf der Webseite enthält.

#### Funktionsweise:
1. Überprüft die URL, um sicherzustellen, dass sie keine private IP-Adresse verwendet.
   - Private IP-Adressen wie `172.x.x.x`, `192.168.x.x` oder `10.x.x.x` sind nicht erlaubt.
2. Wenn die URL eine private IP-Adresse enthält, wird eine Ausnahme (`BadRequest`) ausgelöst.
3. Wenn die URL gültig ist, ruft die Methode `getLinks` auf, um die Links zu extrahieren.

---

## Datenstrukturen
Die Klasse verwendet folgende Datenstrukturen:
- **`List<String>`**: Eine Liste, um die extrahierten Links zu speichern.

---

## Ausnahmen
### `BadRequest`
Eine benutzerdefinierte Ausnahme, die ausgelöst wird, wenn:
- Eine private IP-Adresse verwendet wird.
- Ein Fehler beim Verarbeiten der URL auftritt.

---

## Insights
- **Bibliothek**: Die Klasse verwendet die `Jsoup`-Bibliothek, die speziell für die Analyse und Bearbeitung von HTML-Dokumenten entwickelt wurde.
- **Sicherheitsaspekt**: Die Methode `getLinksV2` schützt vor der Verwendung von privaten IP-Adressen, was wichtig ist, um Sicherheitsrisiken zu minimieren.
- **Flexibilität**: Die Klasse kann für verschiedene Webseiten verwendet werden, solange die URL gültig ist.
- **Fehlerbehandlung**: Die Methode `getLinksV2` enthält eine robuste Fehlerbehandlung, um unerwartete Probleme zu vermeiden.
