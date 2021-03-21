package Level3.lesson5;
import java.util.concurrent.locks.ReentrantLock;

public class Road extends Stage{
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.lock = new ReentrantLock();
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (length == 40) {
            try {
                winner(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void winner(Car c) throws InterruptedException {
        if (lock.isLocked() == false) {
            lock.lock();
            System.out.println(c.getName() + " - WIN");
        }
    }
}
