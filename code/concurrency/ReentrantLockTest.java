import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    // private final ReentrantLock lock = new ReentrantLock();
    private final ReentrantLock lock = new ReentrantLock(true); // 公平锁
    private int count = 0;
    private final Condition condition = lock.newCondition();
    private boolean ready = false;

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    //Reentrancy
    public void outer() {
        lock.lock();
        try {
            System.out.println("Outer method");
            inner(); // 同一个线程再次获取锁
        } finally {
            lock.unlock();
        }
    }

    public void inner() {
        lock.lock();
        try {
            System.out.println("Inner method");
        } finally {
            lock.unlock();
        }
    }

    //lockinterruptibly
    public void runTask() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " acquired lock");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted");
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    //tryLock
     public void tryLockTask() {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " got the lock");
                    Thread.sleep(30000);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " couldn't get the lock");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    //fair lock
    public void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " accessed resource");
        } finally {
            lock.unlock();
        }
    }


    //Condition
public void awaitTask() {
        lock.lock();
        try {
            while (!ready) {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " proceeding");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void signalTask() {
        lock.lock();
        try {
            ready = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        // Thread t1 = new Thread(() -> {
        //     for (int i = 0; i < 1000; i++) {
        //         test.increment();
        //     }
        // });

        // Thread t2 = new Thread(() -> {
        //     for (int i = 0; i < 1000; i++) {
        //         test.increment();
        //     }
        // });

        // t1.start();
        // t2.start();
        // t1.join();
        // t2.join();

        // System.out.println("Final count: " + test.getCount());
        // test.outer();

        // Thread t1 = new Thread(test::runTask, "Thread-1");
        // Thread t2 = new Thread(test::runTask, "Thread-2");
        // t1.start();
        // Thread.sleep(100); // 确保 t1 先获取锁
        // t2.start();
        // Thread.sleep(1000);
        // t2.interrupt(); // 中断 t2

        // new Thread(test::tryLockTask, "Thread-A").start();
        // new Thread(test::tryLockTask, "Thread-B").start();

        // for (int i = 0; i < 5; i++) {
        //     new Thread(test::accessResource, "Thread-" + i).start();
        // }

        // new Thread(test::awaitTask, "Worker-1").start();
        // new Thread(test::awaitTask, "Worker-2").start();

        // Thread.sleep(2000);
        // test.signalTask(); // 唤醒所有等待线程

    }

}
