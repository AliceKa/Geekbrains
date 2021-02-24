package Level2.lesson4;

interface Check {
    int search(Integer n, Integer[] list);
}
public class Task2 {
    public static void main(String[] args) {
        Check check = (n, list) -> {
            int result = -1;
            for (int i = 0; i<list.length; i++) {
                if (list[i] == n) {
                    result = i;
                    break;
                }
            }
            return result;
        };
        System.out.println(check.search(1, new Integer[]{2, 2, 2, 3, 1}));
    }
}
