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

    public static void main(String[] args) {
        Box<Orange> boxOrange = new Box<>(new Orange());
        boxOrange.addToCollection(new Orange());
        boxOrange.addToCollection(new Orange());
        System.out.println(boxOrange.getWeight());

        Box<Apple> boxApple = new Box<>(new Apple());
        boxApple.addToCollection(new Apple());
        boxApple.addToCollection(new Apple());
        System.out.println(boxApple.getWeight());

        System.out.println(boxApple.sameWeight(boxOrange));
    }
}

