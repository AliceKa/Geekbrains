package Level2.lesson3;

import java.util.*;

public class WordCounter {
    public static void main(String[] args) {

        List<String > listString = new ArrayList<>(Arrays.asList("apple", "banana", "apple","berry", "apple", "strawberry", "orange", "pineapple", "orange"));
        System.out.println(listString);
        listString.forEach(System.out::println);

        Set<String> setString = new TreeSet<>(listString);
        for (String key: setString) {
            System.out.println(key + ": " + Collections.frequency(listString, key));
        }
    }

}
