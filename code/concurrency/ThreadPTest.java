import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class ThreadPTest {

    public static String callA() {
    return "A";
}
    public static String callB() {
    return "B";
}
    public static void main(String[] args) {
        var pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
try {
  CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> ThreadPTest.callA(), pool);
  CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> ThreadPTest.callB(), pool);

  String result =
      a.orTimeout(300, java.util.concurrent.TimeUnit.MILLISECONDS)
       .exceptionally(ex -> "A_TIMEOUT")
       .thenCombine(
          b.completeOnTimeout("B_TIMEOUT", 300, java.util.concurrent.TimeUnit.MILLISECONDS),
          (ra, rb) -> ra + "|" + rb
       ).join();

  System.out.println(result);
} finally {
  pool.shutdown();
}
    }
}
