package script;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomainListsFromCSV {

  public static List<String> loadDomains(String csvPath) {
    List<String> domains = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
      String line;
      boolean firstLine = true;

      while ((line = br.readLine()) != null) {
        if (firstLine) { // Header überspringen
          firstLine = false;
          continue;
        }

        String[] parts = line.split(",");
        if (parts.length >= 2) {
          String origin = parts[0].trim();
          String rank = parts[1].trim();

          if ("1000".equals(rank)) {
            domains.add(origin);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return domains;
  }
}
