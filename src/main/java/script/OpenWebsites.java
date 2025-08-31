package script;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
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
    options.addArguments("load-extension=C:/Studium/softwareprojekt/ubitrans-browserextension-HazelNEW/Frontend/build");

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

    // Liste der Webseiten
    // Liste der Webseiten
    List<String> urls = Arrays.asList(
        "https://example.com",
        "https://openai.com",
        "https://wikipedia.org",
        "https://www.google.com",
        "https://www.youtube.com",
        "https://www.twitter.com",
        "https://www.facebook.com",
        "https://www.instagram.com",
        "https://www.reddit.com",
        "https://www.linkedin.com",
        "https://www.stackoverflow.com",
        "https://www.github.com",
        "https://www.medium.com",
        "https://www.bbc.com",
        "https://www.cnn.com",
        "https://www.nytimes.com",
        "https://www.theguardian.com",
        "https://www.spiegel.de",
        "https://www.zeit.de",
        "https://www.tagesschau.de",
        "https://www.heise.de",
        "https://www.golem.de",
        "https://www.chip.de",
        "https://www.t3n.de",
        "https://www.arstechnica.com",
        "https://www.techcrunch.com",
        "https://www.wired.com",
        "https://www.engadget.com",
        "https://www.howtogeek.com",
        "https://www.makeuseof.com",
        "https://www.quora.com",
        "https://www.khanacademy.org",
        "https://www.coursera.org",
        "https://www.edx.org",
        "https://www.udemy.com",
        "https://www.duolingo.com",
        "https://www.wikipedia.org",
        "https://www.nationalgeographic.com",
        "https://www.scientificamerican.com",
        "https://www.nature.com",
        "https://www.sciencemag.org",
        "https://www.space.com",
        "https://www.nasa.gov",
        "https://www.esa.int",
        "https://www.mit.edu",
        "https://www.stanford.edu",
        "https://www.harvard.edu",
        "https://www.ox.ac.uk",
        "https://www.cam.ac.uk",
        "https://www.uni-heidelberg.de",
        "https://www.uni-muenchen.de",
        "https://www.uni-koeln.de",
        "https://www.uni-hamburg.de",
        "https://www.uni-frankfurt.de",
        "https://www.uni-bonn.de",
        "https://www.uni-mannheim.de",
        "https://www.uni-stuttgart.de",
        "https://www.uni-tuebingen.de",
        "https://www.uni-freiburg.de",
        "https://www.uni-dresden.de",
        "https://www.amazon.com",
        "https://www.ebay.com",
        "https://www.alibaba.com",
        "https://www.booking.com",
        "https://www.airbnb.com",
        "https://www.tripadvisor.com",
        "https://www.imdb.com",
        "https://www.netflix.com",
        "https://www.spotify.com",
        "https://www.soundcloud.com",
        "https://www.twitch.tv",
        "https://www.discord.com",
        "https://www.slack.com",
        "https://www.microsoft.com",
        "https://www.apple.com",
        "https://www.samsung.com",
        "https://www.intel.com",
        "https://www.amd.com",
        "https://www.nvidia.com",
        "https://www.oracle.com",
        "https://www.ibm.com",
        "https://www.adobe.com",
        "https://www.autodesk.com",
        "https://www.paypal.com",
        "https://www.visa.com",
        "https://www.mastercard.com",
        "https://www.fifa.com",
        "https://www.uefa.com",
        "https://www.nba.com",
        "https://www.formula1.com",
        "https://www.olympics.com",
        "https://www.un.org",
        "https://www.europa.eu",
        "https://www.bund.de",
        "https://www.usa.gov",
        "https://www.whitehouse.gov",
        "https://www.bundesregierung.de"
    );



    // Nacheinander abarbeiten
    for (String url : urls) {
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
    driver.quit();
  }
}
