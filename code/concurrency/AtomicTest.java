import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicTest {
    private final AtomicBoolean flag = new AtomicBoolean(false);

    public void setFlag() {
        flag.set(true);
    }

    public boolean isFlagSet() {
        return flag.get();
    }

    public void clearFlag() {
        flag.set(false);
    }

    public static void main(String[] args) {
        AtomicTest test = new AtomicTest();

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
