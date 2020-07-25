package entity;

import java.io.Serializable;

public class Book implements Serializable {

    private int bookID;
    private String bookName;
    private int bookCount;
    private String detail;
    private String image;

    public Book() {
    }

    public Book(int bookID, String bookName, int bookCount, String detail, String image) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookCount = bookCount;
        this.detail = detail;
        this.image = image;
    }

    public Book(String bookName, int bookCount, String detail, String image) {
        this.bookName = bookName;
        this.bookCount = bookCount;
        this.detail = detail;
        this.image = image;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", bookCount=" + bookCount +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
