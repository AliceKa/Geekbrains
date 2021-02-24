package Level2.lesson4;

interface ReverseString {
    String reverse(String s);
}

public class Task3 {
    static String changeStr(ReverseString rs, String s) {
        return rs.reverse(s);
    }
    public static void main(String[] args) {
        ReverseString reverse = (str) -> {
            String result = "";
            for (int i = str.length()-1; i>= 0; i--) {
                result +=str.charAt(i);
            }
            return result;
        };

        System.out.println(changeStr(reverse,"Hello"));
    }
}
