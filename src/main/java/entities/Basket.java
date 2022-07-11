package entities;

public class Basket {
    private long id;
    private long customerId;

    public Basket() {
        throw new NullPointerException("Basket cannot not have a customer linked!");
    }

    public Basket(long customerId) {
        setCustomerId(customerId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
