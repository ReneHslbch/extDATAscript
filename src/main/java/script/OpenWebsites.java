package script;

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
      // Suche Buttons mit typischen Texten
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
    // Chrome-Optionen
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--no-sandbox");

    // Extension im Entwicklermodus laden (entpackter Ordner)
    options.addArguments(
        "load-extension=C:/Studium/softwareprojekt/ubitrans-browserextension-HazelNEW/Frontend/build");

    // WebDriver starten
    WebDriver driver = new ChromeDriver(options);

    // Eingabeaufforderung
    Scanner scanner = new Scanner(System.in);
    System.out.println("Tippe 'start', um die Webseiten zu öffnen, oder 'stop', um zu beenden:");
    String input = scanner.nextLine();

    if (!"start".equalsIgnoreCase(input)) {
      System.out.println("Beendet.");
      driver.quit();
      return;
    }

    // Nacheinander abarbeiten
    for (String url : DomainLists.demirDElist) {
      try {
        driver.get(url);
        System.out.println("Geöffnet: " + url);
        Thread.sleep(2000);
        handleCookies(driver);
        Thread.sleep(30000);


      } catch (Exception e) {
        System.out.println("Fehler bei " + url + ": " + e.getMessage());
      }
    }


    // Browser schließen
    //driver.quit();
  }
}
