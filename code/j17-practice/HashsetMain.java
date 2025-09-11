import java.util.HashSet;

public class HashsetMain {
public static void main(String[] args) {
    OrderDetail order1 = new OrderDetail("1", "Laptop");
    OrderDetail order11 = new OrderDetail("1", "Laptop");
    OrderDetail order2 = new OrderDetail("2", "Smartphone");
    HashSet<OrderDetail> orders = new HashSet<>();
    orders.add(order1);
    orders.add(order2);
    orders.add(order11);
    System.out.println(order1.equals(order11)); // true, same orderId and customerName
    System.out.println(orders.size()); // 2, because order1 and order11 are considered equal
    System.out.println(orders.contains(order1)); // true, order1 is in the set
    System.out.println(orders.contains(order11)); // true, order11 is considered equal to order1
    

    System.out.println("Orders in HashSet:");
    for (OrderDetail order : orders) {
        System.out.println("Order ID: " + order.getOrderId() + ", Customer Name: " + order.getCustomerName());
    }
}
}
