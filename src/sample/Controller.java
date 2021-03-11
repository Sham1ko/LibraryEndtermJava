package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Connectivity.connectDB;
import Java.Users;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tfUserID;

    @FXML
    private TextField tfUserName;

    @FXML
    private TextField tfUserDocument;

    @FXML
    private TextField tfUserPhone;

    @FXML
    private TextField tfUserAddress;

    @FXML
    private TextField tfUserBooks;

    @FXML
    private Button btnTableUsers;

    @FXML
    private Button btnTableBooks;

    @FXML
    private TableView<Users> tableUsers;

    @FXML
    private TableColumn<Users, Integer> userID;

    @FXML
    private TableColumn<Users, String> userName;

    @FXML
    private TableColumn<Users, String> userDocument;

    @FXML
    private TableColumn<Users, String > userPhone;

    @FXML
    private TableColumn<Users, String> userAddress;

    @FXML
    private TableColumn<Users, String> userBooks;


    @FXML
    void getOnAction(ActionEvent event) {

    }

    ObservableList<Users> listM;

    int index = -1;

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;



    @FXML
    public void addUsers(ActionEvent event){

        connection = connectDB.getConnection();
        String sql = "insert into users (userName,userDocument,userPhone,userAddress,userBooks) values(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tfUserName.getText());
            preparedStatement.setString(2,tfUserDocument.getText());
            preparedStatement.setString(3,tfUserPhone.getText());
            preparedStatement.setString(4,tfUserAddress.getText());
            preparedStatement.setString(5,tfUserBooks.getText());
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null,"User has added successfully");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    public void updateUsers(ActionEvent event){

        connection = connectDB.getConnection();
        String sql = "insert into users (userName,userDocument,userPhone,userAddress,userBooks) values(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tfUserName.getText());
            preparedStatement.setString(2,tfUserDocument.getText());
            preparedStatement.setString(3,tfUserPhone.getText());
            preparedStatement.setString(4,tfUserAddress.getText());
            preparedStatement.setString(5,tfUserBooks.getText());
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null,"User has added successfully");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    void btnTableBooks(ActionEvent event){
        makeFadeOut();
    }

    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadNextScene();
            }
        });
        fadeTransition.play();
    }

    private void loadNextScene(){
        try {
            Parent secondView;

            secondView = (AnchorPane) FXMLLoader.load(getClass().getResource("scene2.fxml"));

            Scene newScene = new Scene(secondView);

            Stage curStage = (Stage) rootPane.getScene().getWindow();

            curStage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        userID.setCellValueFactory((new PropertyValueFactory<Users,Integer>("userID")));
        userName.setCellValueFactory((new PropertyValueFactory<Users,String>("userName")));
        userDocument.setCellValueFactory((new PropertyValueFactory<Users,String>("userDocument")));
        userPhone.setCellValueFactory((new PropertyValueFactory<Users,String>("userPhone")));
        userAddress.setCellValueFactory((new PropertyValueFactory<Users,String>("userAddress")));
        userBooks.setCellValueFactory((new PropertyValueFactory<Users,String>("userBooks")));

        listM = connectDB.getDataUsers();
        tableUsers.setItems(listM);
    }
}
