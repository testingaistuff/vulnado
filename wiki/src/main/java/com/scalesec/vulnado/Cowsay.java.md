# Dokumentation: Cowsay.java

## Übersicht
Die Klasse `Cowsay` ermöglicht es, eine Nachricht an das Programm `cowsay` zu übergeben, das eine lustige ASCII-Kunst einer Kuh mit der Nachricht ausgibt. Die Klasse verwendet Java-Prozesssteuerung, um das externe Programm auszuführen und die Ausgabe zu erfassen.

---

## Dateiname
**Cowsay.java**

---

## Hauptfunktionalität
Die Klasse enthält eine Methode `run`, die eine Eingabezeichenkette entgegennimmt, das `cowsay`-Programm ausführt und die Ausgabe als Zeichenkette zurückgibt.

---

## Methodenbeschreibung

### `run(String input)`
- **Parameter**: 
  - `input`: Eine Zeichenkette, die die Nachricht enthält, die von der Kuh "gesagt" werden soll.
- **Rückgabewert**: 
  - Eine Zeichenkette, die die ASCII-Ausgabe des `cowsay`-Programms enthält.
- **Funktionsweise**:
  1. Erstellt einen Befehl, der das Programm `cowsay` mit der übergebenen Nachricht ausführt.
  2. Führt den Befehl mit Hilfe von `ProcessBuilder` aus.
  3. Liest die Ausgabe des Prozesses zeilenweise und fügt sie zu einem `StringBuilder` hinzu.
  4. Gibt die gesammelte Ausgabe zurück.

---

## Wichtige Details

### Sicherheitsaspekte
- **Befehlseinschleusung**: Die Methode fügt die Eingabe direkt in den Shell-Befehl ein, was potenziell gefährlich ist. Wenn die Eingabe nicht überprüft wird, könnte ein Benutzer schädliche Befehle einschleusen.

### Externe Abhängigkeiten
- Das Programm `cowsay` muss auf dem System installiert sein und unter `/usr/games/cowsay` verfügbar sein.
- Die Methode verwendet die Bash-Shell (`bash -c`), um den Befehl auszuführen.

---

## Einblicke

### Was ist `cowsay`?
`cowsay` ist ein beliebtes Unix-Programm, das eine ASCII-Kuh anzeigt, die eine Nachricht "sagt". Es wird oft für humorvolle Zwecke verwendet.

### Wie funktioniert die Prozesssteuerung in Java?
Die Klasse verwendet `ProcessBuilder`, um externe Programme auszuführen. Dies ist eine leistungsstarke Möglichkeit, mit der Java-Anwendungen mit der Betriebssystem-Shell interagieren können.

### Potenzielle Verbesserungen
- **Eingabevalidierung**: Es wäre sinnvoll, die Eingabe zu überprüfen, um sicherzustellen, dass keine schädlichen Befehle eingeschleust werden können.
- **Fehlerbehandlung**: Die Methode fängt Ausnahmen ab, gibt jedoch nur den Stack-Trace aus. Eine benutzerfreundlichere Fehlerbehandlung könnte hinzugefügt werden.

---

## Beispiel für die Nutzung

```java
public class Main {
  public static void main(String[] args) {
    String message = "Hallo, Welt!";
    String result = Cowsay.run(message);
    System.out.println(result);
  }
}
```

---

## Tabelle: Wichtige Klassen und Methoden

| Klasse       | Methode       | Beschreibung                                                                 |
|--------------|---------------|-----------------------------------------------------------------------------|
| `Cowsay`     | `run(String)` | Führt das `cowsay`-Programm aus und gibt die ASCII-Ausgabe als Zeichenkette zurück. |

---

## Voraussetzungen

- Java-Umgebung
- Installiertes `cowsay`-Programm
- Zugriff auf die Bash-Shell
