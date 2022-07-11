package entities;

public class Category {
    private int id;
    private int parentId;
    private String name;

    public Category()
    {
        throw new NullPointerException("Category cannot be empty!");
    }

    public Category(String name)
    {
        setName(name);
    }

    public Category(int parentId, String name)
    {
        setParentId(parentId);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
