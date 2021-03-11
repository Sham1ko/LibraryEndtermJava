package Java;

public class Books {


    int bookID;
    String bookName;

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public String getBookYear() {
        return bookYear;
    }

    String bookAuthor;
    String bookGenre;

    public Books(int bookID, String bookName, String bookAuthor, String bookGenre, String bookYear) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.bookYear = bookYear;
    }

    String bookYear;
}
