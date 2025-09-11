
/**
 * The synchronized keyword ensures both visibility and atomicity. 
 * When one thread writes to a shared variable inside a synchronized block, 
 * and another thread reads it inside a synchronized block on the same monitor,
 *  the write is guaranteed to be visible to the reader.
 */
public class SynchronizedTest {
private boolean flag = false;

public synchronized void setFlag() {
    flag = true;
}

public synchronized boolean isFlagSet() {
    return flag;
}

    public synchronized void clearFlag() {
        flag = false;
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();

        Thread t1 = new Thread(() -> {
            test.setFlag();
            System.out.println("Thread 1 set flag");
        });

        Thread t2 = new Thread(() -> {
            if (test.isFlagSet()) {
                System.out.println("Thread 2 sees flag is set");
            }
        });

        t1.start();
        t2.start();
    }
}
