package ru.geekbrains.lesson3;

import java.util.Random;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        checkWordInSet(words);

    }
    public static void checkWordInSet(String[] arr) {
        Scanner sc = new Scanner(System.in);
        String word;
        StringBuilder word_guessed = new StringBuilder("###############");

        // ---- Random element ----
        Random rand = new Random();
        int randomIndex = (int)(Math.random()*arr.length);
        String randomElement = arr[randomIndex];


        while (true) {
            System.out.println("Type your word below: ");
            word = sc.next();
            if (word.equalsIgnoreCase(randomElement)) {
                System.out.println("Correct!");
                break;
            }
            else {
                if (word.length() > randomElement.length()) {
                    for (int i = 0; i < randomElement.length(); i++) {
                        if (randomElement.charAt(i) == word.charAt(i)) {
                            word_guessed.setCharAt(i, randomElement.charAt(i));
                        }
                    }
                } else {
                    for (int i = 0; i < word.length(); i++) {
                        if (randomElement.charAt(i) == word.charAt(i)) {
                            word_guessed.setCharAt(i, word.charAt(i));
                        }
                    }
                }
            }
            System.out.println(word_guessed);
        }
    }
}

