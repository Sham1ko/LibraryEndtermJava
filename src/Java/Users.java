package Java;

public class Users {
    int userID;

    public Users(int userID, String userName, String userDocument,  String userPhone, String userAddress, String userBooks) {
        this.userID = userID;
        this.userName = userName;
        this.userDocument = userDocument;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userBooks = userBooks;
    }


    public int getUserID() {
        return userID;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public String getUserBooks() {
        return userBooks;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    String userName;
    String userPhone;
    String userAddress;
    String userDocument;
    String userBooks;

}
