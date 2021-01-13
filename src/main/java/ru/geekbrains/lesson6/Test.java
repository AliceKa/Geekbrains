package ru.geekbrains.lesson6;

import javax.sound.midi.Soundbank;

public class Test {
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Dog dog1 = new Dog();
        cat2.limitRun = 150;
        dog1.limitRun = 500;

        System.out.print("(Cat 1) With limit: "+ cat1.limitRun + " result for 180 meters ");
        cat1.run(180);
        System.out.print("(Cat 2) With limit: "+ cat2.limitRun + " result for 180 meters ");
        cat2.run(180);
        System.out.print("(Dog 1) With limit: "+ dog1.limitSwim + " result for 20 meters ");
        dog1.swim(20);
        System.out.print("Total number of animals: ");
        System.out.println(Animal.getCount());
        System.out.print("Total number of cats: ");
        System.out.println(Cat.getCount());
        System.out.print("Total number of dogs: ");
        System.out.println(Dog.getCount());
    }
}
