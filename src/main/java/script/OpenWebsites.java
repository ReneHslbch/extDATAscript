package script;

import java.util.concurrent.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Scanner;

public class OpenWebsites {

  public static void handleCookies(WebDriver driver) {
    try {
      List<WebElement> buttons = driver.findElements(By.xpath(
          "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'ablehnen') or " +
              "contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'reject') or " +
              "contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'decline') or " +
              "contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'notwendig')]"
      ));

      if (!buttons.isEmpty()) {
        buttons.get(0).click();
        System.out.println("Cookie-Banner abgelehnt ✅");
      } else {
        System.out.println("Kein Ablehnen-Button gefunden ❌");
      }

    } catch (NoSuchElementException e) {
      System.out.println("Kein Cookie-Banner gefunden.");
    } catch (Exception e) {
      System.out.println("Fehler beim Schließen des Cookie-Banners");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dns-prefetch");

    WebDriver driver = new ChromeDriver(options);
    Scanner scanner = new Scanner(System.in);

    // Warten auf Start
    System.out.println("Tippe 'start', um die Webseiten zu öffnen, oder 'quit', um zu beenden:");
    while (true) {
      String input = scanner.nextLine().trim().toLowerCase();
      if (input.equals("start")) {
        break;
      } else if (input.equals("quit")) {
        System.out.println("Programm beendet.");
        driver.quit();
        System.exit(0);
      } else {
        System.out.println("Ungültiger Befehl. Tippe 'start' oder 'quit':");
      }
    }

    int index = 0; // merken, bei welcher Domain wir sind
    while (index < DomainLists.demirDElist.size()) {
      String url = DomainLists.demirDElist.get(index);

      try {
        driver.get(url);
        System.out.println("Geöffnet: " + url);
        Thread.sleep(2000);
        handleCookies(driver);
      } catch (Exception e) {
        System.out.println("Fehler beim Öffnen von " + url + ": " + e.getMessage());
        index++;
        continue;
      }

      boolean paused = true;
      long startTime = System.currentTimeMillis();
      while (paused) {
        System.out.println("Tippe 'weiter' für nächste Seite, 'stop' zum Pausieren, 'quit' zum Beenden (automatisch nach 30s weiter):");

        String input = null;
        long timeout = 30000; // 30 Sekunden
        while ((System.currentTimeMillis() - startTime) < timeout && input == null) {
          try {
            if (System.in.available() > 0) {  // prüft, ob etwas eingegeben wurde
              input = scanner.nextLine().trim().toLowerCase();
            } else {
              Thread.sleep(200); // kurz warten, um CPU zu schonen
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

        if (input == null) {
          System.out.println("Keine Eingabe in 30 Sekunden. Automatisch weiter ✅");
          paused = false;
          index++;
          break;
        }

        switch (input) {
          case "weiter":
            paused = false;
            index++;
            break;
          case "stop":
            System.out.println("Pausiert. Tippe 'weiter', um fortzufahren, oder 'quit', um zu beenden.");
            startTime = System.currentTimeMillis(); // Pause beginnt neu
            break;
          case "quit":
            System.out.println("Sofortiger Abbruch. Browser wird geschlossen.");
            driver.quit();
            System.exit(0);
          default:
            System.out.println("Ungültiger Befehl. Tippe 'weiter', 'stop' oder 'quit'.");
        }
      }


    }
    System.out.println("Alle Seiten geöffnet. Browser wird geschlossen.");

  }
}
