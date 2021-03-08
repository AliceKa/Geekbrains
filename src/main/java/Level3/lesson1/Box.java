package Level3.lesson1;

import java.util.ArrayList;

public class Box<T extends Fruits> {

    private T obj;

    private ArrayList<T> arrayList = new ArrayList<>();

    public Box(T obj) {
        this.obj = obj;
    }

    public void addToCollection(T element) {
        arrayList.add(element);
    }

    public double getWeight() {
        return obj.getWeight()* arrayList.size();
    }

    public boolean sameWeight(Box<?> another) {
        return this.getWeight() == another.getWeight();
    }

    public void sendObjects(Box<T> another) {
        for (int i = 0; i < arrayList.size(); i++) {
            another.arrayList.add(arrayList.get(i));
        }
        arrayList.clear();
    }

    public static void main(String[] args) {
        Box<Orange> boxOrange = new Box<>(new Orange());
        boxOrange.addToCollection(new Orange());
        boxOrange.addToCollection(new Orange());
        System.out.println("boxOrange has a weight of " + boxOrange.getWeight());

        Box<Apple> boxApple = new Box<>(new Apple());
        boxApple.addToCollection(new Apple());
        boxApple.addToCollection(new Apple());
        System.out.println("boxApple has a weight of " + boxApple.getWeight());
        System.out.println("boxApple and boxOrange have same weight: " + boxApple.sameWeight(boxOrange));

        Box<Apple> boxApple1 = new Box<>(new Apple());
        boxApple1.addToCollection(new Apple());
        boxApple1.addToCollection(new Apple());
        boxApple1.addToCollection(new Apple());
        System.out.println("boxApple1 - Before adding new elements: " + boxApple1.getWeight());

        boxApple.sendObjects(boxApple1);
        System.out.println("boxApple1 - After adding new elements: " + boxApple1.getWeight());
        System.out.println("boxApple - After sending new elements: " + boxApple.getWeight());

    }
}

