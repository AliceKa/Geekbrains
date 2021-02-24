package Level2.lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface ListString {
    List<String> search(List<String> list);
}
public class Task6 {
    static List<String> ReturnFunc(ListString ls, List<String> list) {
        return ls.search(list);
    }
    public static void main(String[] args) {
        // 1st Method
        /*ListString listStr = (list) -> {
            List<String> newList = new ArrayList<String>();
            for (String word: list) {
                if (word.charAt(0) == 'a') newList.add(word);
            }
            return newList;
        };
        System.out.println(ReturnFunc(listStr, Arrays.asList("apple", "ban", "lo", "aa")));
        */
        // 2nd Method
        List<String> list = Arrays.asList("aaa", "bb", "aks");
        List<String> listStr = list.stream().filter(x-> x.charAt(0) == 'a').collect(Collectors.toList());

        System.out.println(listStr);
    }
}
