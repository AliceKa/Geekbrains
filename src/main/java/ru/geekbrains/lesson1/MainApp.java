package ru.geekbrains.lesson1;

public class MainApp {
    // Task 1
    public static void main(String[] args) {
        // Task 2
        byte xByte = -120;
        short xShort = 12442;
        int xInt = 1000;
        long xLong = 200000L;
        float yFloat = 3.4e+38f;
        double yDouble = 1.7e+308;
        char zChar = '*';
        boolean bool = true;
        String j = "Hello";

        float aFloat = 21;
        float bFloat = 20;
        float cFloat = 10;
        float dFloat = 5;

        // Task 3

        System.out.println("Результат Task 3: " + calculate(aFloat,bFloat,cFloat,dFloat));

        // Task 4

        int aInt = -10;
        int bInt = 19;

        System.out.println("Результат Task 4: " + check(aInt,bInt));

        // Task 5

        pos_neg(aInt);

        // Task 6
        boolean check_pos_neg2 = pos_neg2(aInt);
        System.out.println("Результат Task 6: " + check_pos_neg2);

        // Task 7
        String name = "Алиса";
        hello(name);

        // Task 8
        int leapYear = 2000;
        isleapYear(leapYear);


    }

    public static float calculate(float a, float b, float c, float d) {
        float result = a*(b+(c/d));
        return result;
    }

    public static boolean check(int a, int b) {
        int summa = a+b;
        boolean result = (summa >= 10 && summa <=20);
        return result;
    }

    public static void pos_neg(int a) {
        String result;
        if (a >= 0) {
            System.out.println("Положительное число");
        }
        else {
            System.out.println("Отрицательное число");
        }
    }

    static boolean pos_neg2(int a) {
        boolean result;
        result = a < 0;
        return result;
    }

    public static void hello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    public static void isleapYear(int a) {
        String result = "Невисокосный год";
        if (a % 4 != 0 && a % 100 == 0 && a % 400 != 0) {
            result = "Високосный год";
        }
        System.out.println("Год " + a + " : " + result);
    }
}
