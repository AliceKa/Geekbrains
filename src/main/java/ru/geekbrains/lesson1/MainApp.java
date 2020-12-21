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

        int aInt = 21;
        int bInt = 20;
        int cInt = 10;
        int dInt = 5;

        // Task 3

        System.out.println("Результат Task 3: " + calculate(aInt,bInt,cInt,dInt));

        // Task 4

        System.out.println("Результат Task 4: " + check(aInt,bInt));

        // Task 5

        System.out.println("Результат Task 5: " + pos_neg(aInt));

        // Task 6
        boolean check_pos_neg2 = pos_neg2(aInt);
        System.out.println("Результат Task 6: " + check_pos_neg2);

        // Task 7
        String name = "Алиса";
        hello(name);

        // Task 8
        int leapYear = 2000;
        System.out.println(leapYear + " : " + year(leapYear));


    }

    static int calculate(int a, int b, int c, int d) {
        int result = a*(b+(c/d));
        return result;
    }

    static boolean check(int a, int b) {
        int summa = a+b;
        boolean result = (summa >= 10 && summa <=20);
        return result;
    }

    static String pos_neg(int a) {
        String result;
        if (a >= 0) {
            result = "Положительное";
        }
        else {
            result = "Отрицательное";
        }
        return result;
    }

    static boolean pos_neg2(int a) {
        boolean result;
        result = a < 0;
        return result;
    }

    static String hello(String name) {
        System.out.println("Привет, " + name + "!");
        return null;
    }

    static String year(int a) {
        String result;
        if (a % 4 == 0) {
            if (a % 100 == 0) {
                result = "Не високосный год";
                if (a % 400 == 0) {
                    result = "Високосный год";
                }
            }
            else {
                result = "Високосный год";
            }
        }
        else {
            result = "Не високосный год";
        }
        return result;
    }

}
