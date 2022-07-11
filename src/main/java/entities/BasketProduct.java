package entities;

public class BasketProduct {
    private long basketId;
    private long productId;
    private int count;
    private int total_price;

    public BasketProduct() {
        throw new NullPointerException("BasketProduct cannot be null!");
    }

    public BasketProduct(long basketId, long productId, int count, int total_price) {
        setBasketId(basketId);
        setProductId(productId);
        setCount(count);
        setTotal_price(total_price);
    }

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
