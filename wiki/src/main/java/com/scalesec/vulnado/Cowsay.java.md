# Dokumentation: Cowsay.java

## Übersicht
Die Klasse `Cowsay` ist ein Programm, das eine externe Anwendung namens `cowsay` ausführt. Diese Anwendung erzeugt eine lustige Ausgabe, bei der eine Kuh eine Nachricht "sagt". Die Klasse verwendet Java-Prozesssteuerung, um die externe Anwendung auszuführen und die Ausgabe zu erfassen.

---

## Dateiname
**Cowsay.java**

---

## Hauptfunktionalität
Die Hauptfunktionalität der Klasse besteht darin, eine Nachricht an die `cowsay`-Anwendung zu übergeben und die generierte Ausgabe zurückzugeben.

---

## Aufbau der Klasse

### Paket
Die Klasse gehört zum Paket:
```java
package com.scalesec.vulnado;
```

### Importierte Klassen
Die Klasse verwendet folgende Java-Bibliotheken:
- `java.io.BufferedReader`: Zum Lesen der Ausgabe der `cowsay`-Anwendung.
- `java.io.InputStreamReader`: Zum Umwandeln des Eingabestreams in lesbare Zeichen.
- `java.lang.ProcessBuilder`: Zum Erstellen und Starten eines Prozesses.

---

## Methoden

### `run(String input)`
Diese Methode führt die `cowsay`-Anwendung aus und gibt die Ausgabe als String zurück.

#### Parameter
| Name   | Typ     | Beschreibung                          |
|--------|---------|---------------------------------------|
| `input`| `String`| Die Nachricht, die die Kuh "sagen" soll.|

#### Ablauf
1. **Kommando erstellen**: Das Kommando wird als String zusammengesetzt, wobei die Nachricht (`input`) eingebettet wird.
   ```java
   String cmd = "/usr/games/cowsay '" + input + "'";
   ```
2. **Prozess starten**: Ein `ProcessBuilder` wird verwendet, um das Kommando auszuführen.
   ```java
   processBuilder.command("bash", "-c", cmd);
   ```
3. **Ausgabe erfassen**: Die Ausgabe des Prozesses wird Zeile für Zeile gelesen und in einem `StringBuilder` gespeichert.
   ```java
   BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
   ```
4. **Fehlerbehandlung**: Falls ein Fehler auftritt, wird der Stacktrace ausgegeben.
   ```java
   catch (Exception e) {
      e.printStackTrace();
   }
   ```

#### Rückgabewert
Die Methode gibt die vollständige Ausgabe der `cowsay`-Anwendung als String zurück.

---

## Einblicke

### Sicherheitsaspekte
- **Befehlseinschleusung**: Die Methode verwendet die Eingabe direkt im Kommando. Dies kann zu Sicherheitsproblemen führen, wenn die Eingabe nicht validiert wird. Ein Benutzer könnte schädliche Befehle einschleusen.

### Abhängigkeiten
- Die Funktionalität der Klasse hängt von der externen Anwendung `cowsay` ab, die auf dem System installiert sein muss. Der Pfad zur Anwendung ist festgelegt als `/usr/games/cowsay`.

### Verwendung
- Diese Klasse kann verwendet werden, um Nachrichten auf humorvolle Weise darzustellen, indem sie die `cowsay`-Ausgabe generiert.

---

## Beispiel
```java
public class Main {
  public static void main(String[] args) {
    String message = "Hallo, Welt!";
    String result = Cowsay.run(message);
    System.out.println(result);
  }
}
```
**Ausgabe**:
```
  _______________
< Hallo, Welt! >
  ---------------
         \   ^__^
          \  (oo)\_______
             (__)\       )\/\
                 ||----w |
                 ||     ||
```
