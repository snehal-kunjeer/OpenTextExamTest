import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class PartnerSolutionJoiner {

    public static void main(String[] args) throws Exception {
        List<String> partners = getPartners();
        Map<String, List<String>> solutions = getSolutions();

        List<Map<String, Object>> result = new ArrayList<>();
        for (String partner : partners) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("partner", partner);
            entry.put("solutions", solutions.getOrDefault(partner, new ArrayList<>()));
            result.add(entry);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        System.out.println(json);
    }

    static List<String> getPartners() throws Exception {
        Document doc = Jsoup.connect("https://www.opentext.com/partners/partner-directory").get();
        Elements names = doc.select(".partner-name"); // Assumes class name
        List<String> result = new ArrayList<>();
        for (Element name : names) {
            result.add(name.text().trim());
        }
        return result;
    }

    static Map<String, List<String>> getSolutions() throws Exception {
        Document doc = Jsoup.connect("https://www.opentext.com/partners/find-a-partner-solution").get();
        Elements items = doc.select(".solution-item"); // Assumes class name
        Map<String, List<String>> map = new HashMap<>();
        for (Element item : items) {
            String partner = item.select(".partner-name").text().trim();
            String solution = item.select(".solution-title").text().trim();
            map.computeIfAbsent(partner, k -> new ArrayList<>()).add(solution);
        }
        return map;
    }
}
