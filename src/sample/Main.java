package sample;

import Connectivity.connectDB;
import Connectivity.connectIDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
        connectIDB DB =new connectDB();
        DB.getConnection();
//        Statement statement;
//        String query;
//        try {
//            query = """
//
//                    """;
//            statement = options.connection.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Tables have been created!");
//        } catch (Exception var12) {
//            System.out.println("You have already created same tables.");
//        }
    }
}
