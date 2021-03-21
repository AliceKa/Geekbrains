package Level3.lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable{
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cb;
    private CountDownLatch cd_finish;
    private CountDownLatch cd_start;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cd_finish, CountDownLatch cd_start) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
        this.cd_finish = cd_finish;
        this.cd_start = cd_start;
    }

    public void start() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        cd_start.countDown();
    }

    @Override
    public void run() {
        for (int i = 0; i < race.getStages().size(); i++) race.getStages().get(i).go(this);
        cd_finish.countDown();
    }

}
