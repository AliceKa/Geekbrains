package ru.geekbrains.lesson7;

import java.util.Scanner;

public class Strings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce your sentence: ");
        String readString = scanner.nextLine();
        stringInput(readString);
    }

    public static void stringInput(String sentence) {
        System.out.println("б) " + sentence.charAt(sentence.length()-1));
        System.out.println("в) " + sentence.endsWith("!!!"));
        System.out.println("г) " + sentence.startsWith("I like"));
        System.out.println("д) " + sentence.contains("Java"));
        System.out.println("е) " + sentence.indexOf("Java"));
        System.out.println("ж) " + sentence.replace('a', 'o'));
        System.out.println("з) " + sentence.toUpperCase());
        System.out.println("и) " + sentence.toLowerCase());
        System.out.println("к) " + sentence.substring(sentence.indexOf("Java"), sentence.indexOf("Java") + "Java".length()));

    }
}
