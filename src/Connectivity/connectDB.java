package Connectivity;

import Java.Books;
import Java.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class connectDB implements connectIDB {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm","postgres","shamiko");
            if (connection!=null){
                JOptionPane.showMessageDialog(null,"Connection Established");
            }else {
                System.out.println("Connection Failed");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }

    public static ObservableList<Users> getDataUsers(){

        Connection connection = getConnection();
        ObservableList<Users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from Users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Users(Integer.parseInt(rs.getString("userID")),
                        rs.getString("userName"),
                        rs.getString("userDocument"),
                        rs.getString("userPhone"),
                        rs.getString("userAddress"),
                        rs.getString("userBooks")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Books> getDataBooks(){

        Connection connection = getConnection();
        ObservableList<Books> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from Books");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Books(Integer.parseInt(rs.getString("bookID")),
                        rs.getString("bookName"),
                        rs.getString("bookAuthor"),
                        rs.getString("bookGenre"),
                        rs.getString("bookYear")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    
}
