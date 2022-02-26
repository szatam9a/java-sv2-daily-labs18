package day02;

public class Book {
    private long id;
    private String author;
    private String title;
    private int price;
    private int pieces;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", pieces=" + pieces +
                '}';
    }

    public Book(long id, String author, String title, int price, int pieces) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.pieces = pieces;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getPieces() {
        return pieces;
    }
}
