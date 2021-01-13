package ru.geekbrains.lesson6;

public class Dog extends Animal{

    protected double limitRun;
    protected double limitSwim;
    protected double limitJump;

    private static int numInstances = 0;

    protected static int getCount() {
        return numInstances;
    }
    private static void addInstance(){
        numInstances++;
    }
    public Dog() {
        this.limitRun = 500;
        this.limitSwim = 10;
        this.limitJump = 0.5;
        addInstance();
    }

    public void run(double length) {
        if (length <=limitRun) {
            System.out.println("run: true");
        }
        else {
            System.out.println("run: false");
        }
    }
    public void swim(double length) {
        if (length <=limitSwim) {
            System.out.println("swim: true");
        }
        else {
            System.out.println("swim: false");
        }
    }
    public void jump(double height) {
        if (height <=limitJump) {
            System.out.println("jump: true");
        }
        else {
            System.out.println("jump: false");
        }
    }
}
