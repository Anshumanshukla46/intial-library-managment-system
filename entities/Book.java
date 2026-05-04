package entities;

public class Book {
    private int id;
    private int totalCopies;
    private String name;

    public Book() {
    }

    public Book(int id, int totalCopies, String name) {
        this.id = id;
        this.totalCopies = totalCopies;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
