package entities;

public class Role {
    private long id;
    private String name;

    public Role() {
        throw new NullPointerException("Cannot create empty role!");
    }

    public Role(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
