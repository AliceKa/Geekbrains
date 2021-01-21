package ru.geekbrains.lesson7;

public class Cat {
    protected String name;
    protected int appetite;
    protected boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        if (this.appetite >0) {
            this.fullness = false;
        }
    }
    public void eat(Plate p) {
        p.decreaseFood(appetite);
        decreaseAppetite(appetite);
        changeFull();
    }
    public void decreaseAppetite(int n) {
        appetite -=n;
    }

    public void increaseAppetite(int n) {
        appetite +=n;
    }

    public void changeFull() {
        if (fullness) {
            fullness = false;
        }
        else {
            fullness = true;
        }
    }
    public void info() {
        System.out.println("Cat: " + name + " has an appetite of " + appetite + " and fullness of " + fullness);
    }
}
