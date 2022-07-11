package entities;

public class Product {
    private long id;
    private String name;
    private String description;
    private int cost;
    private int inStock;
    private long supplierId;
    private long categoryId;

    public Product() {
        throw new NullPointerException("Product cannot be empty!");
    }

    public Product(String name, String description, int cost, int inStock, long supplierId) {
        setName(name);
        setDescription(description);
        setCost(cost);
        setInStock(inStock);
        setSupplierId(supplierId);
    }

    public Product(String name, String description, int cost, int inStock, long supplierId, long categoryId) {
        setName(name);
        setDescription(description);
        setCost(cost);
        setInStock(inStock);
        setSupplierId(supplierId);
        setCategoryId(categoryId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
