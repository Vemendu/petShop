package entities;

public class ProductCategory {
    private long productId;
    private long categoryId;

    public ProductCategory() {
        throw new NullPointerException("Cannot create empty Product-Category link!");
    }

    public ProductCategory(long productId, int categoryId)
    {
        setProductId(productId);
        setCategoryId(categoryId);
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
