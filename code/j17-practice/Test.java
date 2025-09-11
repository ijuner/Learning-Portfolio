
public class Test {
public static void main(String[] args) {
    Order onlineOrder = new OnlineOrder();
    Order inStoreOrder = new InStoreOrder();

    System.out.println(orderType(onlineOrder));
    System.out.println(orderType(inStoreOrder));

    if(onlineOrder instanceof OnlineOrder) {
        System.out.println("This is an online order.");
    } else if (onlineOrder instanceof InStoreOrder) {
        System.out.println("This is an in-store order.");
    } else {
        System.out.println("Unknown order type.");
    }

String json = """
    {
        "name": "John Doe",
        "email": "john.doe@example.com",
        "phoneNumber": "123-456-7890"
    }""";


// 多线程测试类（验证并发安全）
 CounterAtomic counter = new CounterAtomic(); // 替换为不同实现测试

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final count: " + counter.get()); // 应该是20000
    

}
    public static String orderType(Order order) {
        return switch (order) {
            case OnlineOrder onlineOrder -> "Online Order";
            case InStoreOrder inStoreOrder -> "In-Store Order";
            default -> "Unknown Order Type";
        };
    }
}
