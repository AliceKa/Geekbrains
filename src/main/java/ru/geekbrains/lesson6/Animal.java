package ru.geekbrains.lesson6;

public abstract class Animal {

    public abstract void run(double length);
    public abstract void swim(double length);
    public abstract void jump(double height);

    private static int numInstances = 0;

    protected static int getCount() {
        return numInstances;
    }
    private static void addInstance(){
        numInstances++;
    }
    Animal() {
        addInstance();
    }
}
