package Level2.lesson4;

interface MaxInt {
    Integer maximum(Integer[] list);
}
public class Task4 {
    static Integer ReturnFunc(MaxInt mx, Integer[] list) {
        return  mx.maximum(list);
    }
    public static void main(String[] args) {
        MaxInt maxint = (list) -> {
            int max = 0;
            for (int symbol : list) {
                if (symbol > max) max = symbol;
            }
            return max;
        };
        System.out.println(ReturnFunc(maxint, new Integer[]{2,1,9,3}));
    }
}
