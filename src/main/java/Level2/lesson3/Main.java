package Level2.lesson3;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        Map<String, Set<String>> contactsMap = new TreeMap<>();
        Contacts contact0 = new Contacts("Babich", "84501210917");
        push(contactsMap, contact0.getSurname(), contact0.getPhoneNumber());
        Contacts contact1 = new Contacts("Sigal", "84501210919");
        push(contactsMap, contact1.getSurname(), contact1.getPhoneNumber());
        Contacts contact2 = new Contacts("Sigal", "89094713389");
        push(contactsMap, contact2.getSurname(), contact2.getPhoneNumber());
        Contacts contact3 = new Contacts("Akberdin", "89304590339");
        push(contactsMap, contact3.getSurname(), contact2.getPhoneNumber());

        System.out.println(contactsMap);
        System.out.println("Phone numbers of surname Sigal: ");
        contactsMap.get("Sigal").forEach(System.out::println);

    }

    public static void push(Map<String, Set<String>> map, String key, String value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);

        }
        else {
            Set<String> tempSet = new HashSet<>(Arrays.asList(value));
            map.put(key, tempSet);
        }
    }
}
