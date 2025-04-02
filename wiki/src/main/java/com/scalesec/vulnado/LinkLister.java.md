# Dokumentation: LinkLister

## Dateiname
`LinkLister.java`

## Beschreibung
Die Klasse `LinkLister` ist ein Programm, das Links aus einer Webseite extrahiert. Es bietet zwei Methoden, um Links zu sammeln: `getLinks` und `getLinksV2`. Die zweite Methode enthält zusätzliche Sicherheitsprüfungen, um die Verwendung von privaten IP-Adressen zu verhindern.

---

## Funktionen

### `getLinks(String url)`
Diese Methode ruft alle Links von einer angegebenen Webseite ab.

#### Parameter:
- **`url`**: Die URL der Webseite, von der die Links extrahiert werden sollen.

#### Rückgabewert:
- Eine Liste von Strings, die die absoluten URLs der Links auf der Webseite enthalten.

#### Funktionsweise:
1. Verbindet sich mit der angegebenen URL.
2. Extrahiert alle `<a>`-Tags aus dem HTML-Dokument.
3. Fügt die absoluten URLs der Links in eine Liste ein.
4. Gibt die Liste zurück.

---

### `getLinksV2(String url)`
Diese Methode ruft ebenfalls Links von einer Webseite ab, führt jedoch zusätzliche Sicherheitsprüfungen durch.

#### Parameter:
- **`url`**: Die URL der Webseite, von der die Links extrahiert werden sollen.

#### Rückgabewert:
- Eine Liste von Strings, die die absoluten URLs der Links auf der Webseite enthalten.

#### Funktionsweise:
1. Überprüft die URL, um sicherzustellen, dass sie keine private IP-Adresse verwendet.
   - Private IP-Adressen wie `172.x.x.x`, `192.168.x.x` oder `10.x.x.x` sind nicht erlaubt.
2. Wenn die URL eine private IP-Adresse enthält, wird eine Ausnahme (`BadRequest`) ausgelöst.
3. Wenn die URL gültig ist, ruft die Methode `getLinks` auf, um die Links zu extrahieren.

---

## Datenstrukturen
Die Klasse verwendet folgende Datenstrukturen:
- **`List<String>`**: Eine Liste, die die extrahierten Links speichert.
- **`Document`**: Repräsentiert das HTML-Dokument der Webseite.
- **`Elements`**: Eine Sammlung von HTML-Elementen, die die `<a>`-Tags enthalten.

---

## Ausnahmen
### `BadRequest`
Die Methode `getLinksV2` wirft eine benutzerdefinierte Ausnahme `BadRequest`, wenn:
- Die URL eine private IP-Adresse enthält.
- Ein Fehler beim Verarbeiten der URL auftritt.

---

## Insights
- **Bibliotheken**: Die Klasse verwendet die `Jsoup`-Bibliothek, um HTML-Dokumente zu analysieren und Links zu extrahieren.
- **Sicherheitsaspekt**: Die Methode `getLinksV2` schützt vor der Verarbeitung von URLs mit privaten IP-Adressen, was für Sicherheitszwecke wichtig ist.
- **Flexibilität**: Die Klasse kann für verschiedene Webseiten verwendet werden, solange die URL gültig ist.
- **Fehlerbehandlung**: Die Methode `getLinksV2` enthält eine robuste Fehlerbehandlung, um unerwartete Probleme zu vermeiden.
