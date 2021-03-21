package Level3.lesson5;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public abstract  class Stage {
    protected int length;
    protected String description;
    protected Semaphore smp;
    protected ReentrantLock lock;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
