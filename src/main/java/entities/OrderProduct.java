package entities;

public class OrderProduct {

    private long orderId;
    private long productId;

    public OrderProduct() {
        throw new NullPointerException("Order-Product link is empty.");
    }

    public OrderProduct(long orderId, long productId)
    {
        setOrderId(orderId);
        setProductId(productId);
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
