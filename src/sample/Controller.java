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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
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
    private TextField tfSearch;


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
    ObservableList<Users> dataList;

    int index = -1;

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    @FXML
    void getSelected(MouseEvent event){
        index=tableUsers.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        tfUserID.setText(userID.getCellData(index).toString());
        tfUserName.setText(userName.getCellData(index).toString());
        tfUserDocument.setText(userDocument.getCellData(index).toString());
        tfUserPhone.setText(userPhone.getCellData(index).toString());
        tfUserAddress.setText(userAddress.getCellData(index).toString());
        tfUserBooks.setText(userBooks.getCellData(index).toString());

    }

    @FXML
    public void addUsers(ActionEvent event){

        connection = connectDB.getConnection();
        String sql = "insert into users (userID,userName,userDocument,userPhone,userAddress,userBooks) values(?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(tfUserID.getText()));
            preparedStatement.setString(2,tfUserName.getText());
            preparedStatement.setString(3,tfUserDocument.getText());
            preparedStatement.setString(4,tfUserPhone.getText());
            preparedStatement.setString(5,tfUserAddress.getText());
            preparedStatement.setString(6,tfUserBooks.getText());
            preparedStatement.execute();
            UpdateTableUsers();

            JOptionPane.showMessageDialog(null,"User has added successfully");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    public void deleteUsers(ActionEvent event){

        connection = connectDB.getConnection();
        String sql = "delete from users where userid=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(tfUserID.getText()));
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null,"User has deleted successfully");
            UpdateTableUsers();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    public void updateUsers(ActionEvent event){

        try {
            connection = connectDB.getConnection();
            int value1=Integer.parseInt(tfUserID.getText());
            String value2=tfUserName.getText();
            String value3=tfUserDocument.getText();
            String value4=tfUserPhone.getText();
            String value5=tfUserAddress.getText();
            String value6=tfUserBooks.getText();

            String sql = "update users set userId='" + value1 + "',username='" +
                    value2 + "',userdocument='" + value3 + "',userphone='" + value4 + "',useraddress='" +
                    value5 + "',userbooks='" + value6 + "'where userid='" + value1+"' ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null,"User has edited successfully");
            UpdateTableUsers();

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

    public void UpdateTableUsers(){
            userID.setCellValueFactory((new PropertyValueFactory<Users,Integer>("userID")));
            userName.setCellValueFactory((new PropertyValueFactory<Users,String>("userName")));
            userDocument.setCellValueFactory((new PropertyValueFactory<Users,String>("userDocument")));
            userPhone.setCellValueFactory((new PropertyValueFactory<Users,String>("userPhone")));
            userAddress.setCellValueFactory((new PropertyValueFactory<Users,String>("userAddress")));
            userBooks.setCellValueFactory((new PropertyValueFactory<Users,String>("userBooks")));

            listM = connectDB.getDataUsers();
            tableUsers.setItems(listM);
            searchUser();
    }

    @FXML
    void searchUser(){
        userID.setCellValueFactory((new PropertyValueFactory<Users,Integer>("userID")));
        userName.setCellValueFactory((new PropertyValueFactory<Users,String>("userName")));
        userDocument.setCellValueFactory((new PropertyValueFactory<Users,String>("userDocument")));
        userPhone.setCellValueFactory((new PropertyValueFactory<Users,String>("userPhone")));
        userAddress.setCellValueFactory((new PropertyValueFactory<Users,String>("userAddress")));
        userBooks.setCellValueFactory((new PropertyValueFactory<Users,String>("userBooks")));

        dataList = connectDB.getDataUsers();
        tableUsers.setItems(dataList);
        FilteredList<Users> filteredData = new FilteredList<>(dataList, b -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                } else if (person.getUserDocument().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (person.getUserPhone().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                } else if (person.getUserAddress().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                } else if (person.getUserBooks().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }


                else
                    return false; // Does not match.
            });
        });
        SortedList<Users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableUsers.comparatorProperty());
        tableUsers.setItems(sortedData);
    }

    @FXML
    void initialize() {
        UpdateTableUsers();
        searchUser();
    }


}
