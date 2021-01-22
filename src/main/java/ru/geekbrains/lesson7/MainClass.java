package ru.geekbrains.lesson7;

import java.util.Random;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Random r = new Random();
        Plate plate = new Plate(100);
        Cat[] catsArray = new Cat[4];
        for (int i = 0; i<4; i++) {
            catsArray[i] = new Cat("Cat"+i, r.nextInt(2));
        }
        for (Cat cat : catsArray) {
            System.out.println("---- " + cat.name + " ----");
            step(cat,plate);
        }

    }
    public static void step(Cat cat, Plate plate) {
        Scanner sc = new Scanner(System.in);
        // Case 1: Food is enough in the plate and cat is hungry for less than the food
        if (plate.food >= cat.appetite && cat.appetite > 0) {
            // Initial state of the cat and the plate
            cat.info();
            plate.info();
            // Cat decreases appetite to 0 & Cat changes fullness to True & Plate decreases food to appetite
            cat.eat(plate);
            System.out.println("(Action) Cat is eating...");
            // Final state of the cat and the plate
            cat.info();
            plate.info();
        }
        // Case 2: Cat is full, option to add the fullness and feed it again
        else {
            if (cat.appetite <= 0) {
                System.out.println(cat.name + " is full!");
                System.out.println("Add his appetite or leave it as it is by pressing ENTER");
                String input = sc.nextLine();
                if (!input.isEmpty()) {
                    cat.increaseAppetite(Integer.parseInt(input));
                    step(cat, plate);
                }
                else {
                    System.out.println("Ok, cat is still full!");
                }
            }
            // Case 3: Food is not enough in the plate, option to add it and feed the cat again
            else {
                System.out.println("Not enough food in the plate");
                System.out.println("Add food to the plate or leave it as it is by pressing ENTER");
                String input = sc.nextLine();
                if (!input.isEmpty()) {
                    plate.increaseFood(Integer.parseInt(input));
                    step(cat, plate);
                }
                else {
                    System.out.println("Ok, the food has not changed in the plate");
                }
            }
        }
    }
}
