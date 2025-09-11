public class OrderDetail {
    private String orderId;
    private String customerName;

    public OrderDetail(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }
    @Override
    public boolean equals(Object obj) { 
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderDetail that = (OrderDetail) obj;
        return orderId.equals(that.orderId) && customerName.equals(that.customerName);
    }   
    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + customerName.hashCode();
        return result;
    }
}
