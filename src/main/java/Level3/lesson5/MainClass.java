package Level3.lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static Semaphore smp = new Semaphore(CARS_COUNT/2);
    public static CountDownLatch cd_finish = new CountDownLatch(CARS_COUNT);
    public static CountDownLatch cd_start = new CountDownLatch(CARS_COUNT);
    public static CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(smp), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb, cd_finish, cd_start);
        }

        for (int i = 0; i < cars.length; i++) {
            int finalI = i;
            new Thread(()->{
                cars[finalI].start();
            }).start();
        }
        cd_start.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < cars.length; i++) {
            int finalI = i;
            new Thread(()->{
                cars[finalI].run();
            }).start();
        }
        cd_finish.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
