package Level2.lesson4;

import java.util.Arrays;
import java.util.List;

interface AvgNumber {
    Double average(List<Integer> list);
}
public class Task5 {
    static Double ReturnFunc(AvgNumber an, List<Integer> list) {
        return an.average(list);
    }
    public static void main(String[] args) {
        AvgNumber avn = (list) -> {
            Double suma = 0.0;
            for (int symbol: list) {
                suma += symbol;
            }
            return suma/list.size();
        };
        System.out.println(ReturnFunc(avn, Arrays.asList(2, 10, 2, 4)));
    }
}
