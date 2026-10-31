import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {

        String paragraph = "Java is easy. Java is powerful, and Java is popular.";

        Map<String, Integer> map = new HashMap<>();

        String[] words = paragraph.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .split("\\s+");

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(map.entrySet());

        list.sort((a, b) -> {
            int result = b.getValue().compareTo(a.getValue());
            return result != 0 ? result :
                    a.getKey().compareTo(b.getKey());
        });
        
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " : "
                    + entry.getValue());
        }
    }
}