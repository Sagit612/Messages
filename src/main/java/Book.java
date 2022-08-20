public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity;

    public Book(int id, String title, String author, double price, int quantity) {
        this.setId(id);
        this.setTitle(title);
        this.setAuthor(author);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book. Id: " + getId() + " | Title: " + getTitle() + " | Author: " + getAuthor() +
                " | Price: " + getPrice() + " | Quantity: " + getQuantity() + "\n";
    }
}
