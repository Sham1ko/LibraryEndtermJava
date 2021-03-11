package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Connectivity.connectDB;
import Java.Books;
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

public class ControllerSecond {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane booksView;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tfBookID;

    @FXML
    private TextField tfBookName;

    @FXML
    private TextField tfBookAuthor;

    @FXML
    private TextField tfBookGenre;

    @FXML
    private TextField tfBookYear;

    @FXML
    private Button btnTableUsers;

    @FXML
    private Button btnTableBooks;

    @FXML
    private TableView<Books> tableBooks;

    @FXML
    private TableColumn<Books, Integer> bookID;

    @FXML
    private TableColumn<Books, String> bookName;

    @FXML
    private TableColumn<Books, String> bookAuthor;

    @FXML
    private TableColumn<Books, String> bookGenre;

    @FXML
    private TableColumn<Books, String> bookYear;


    @FXML
    void getOnAction(ActionEvent event) {

    }

    @FXML
    public void addBooks(ActionEvent event){

        connection = connectDB.getConnection();
        String sql = "insert into books (bookName,bookAuthor,bookGenre,bookYear) values(?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tfBookName.getText());
            preparedStatement.setString(2,tfBookAuthor.getText());
            preparedStatement.setString(3,tfBookGenre.getText());
            preparedStatement.setString(4,tfBookYear.getText());
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null,"Book has added successfully");
            UpdateTableBooks();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    public void deleteBooks(ActionEvent event){

        connection = connectDB.getConnection();
        String sql = "delete from books where bookid=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(tfBookID.getText()));
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null,"Book has deleted successfully");
            UpdateTableBooks();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    void btnTableUsers(ActionEvent event){
        makeFadeOut();
    }

    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(booksView);
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

            secondView = (AnchorPane) FXMLLoader.load(getClass().getResource("sample.fxml"));

            Scene newScene = new Scene(secondView);

            Stage curStage = (Stage) booksView.getScene().getWindow();

            curStage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ObservableList<Books> listN;

    int index = -1;

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    public void UpdateTableBooks(){
        bookID.setCellValueFactory((new PropertyValueFactory<Books,Integer>("bookID")));
        bookName.setCellValueFactory((new PropertyValueFactory<Books,String>("bookName")));
        bookAuthor.setCellValueFactory((new PropertyValueFactory<Books,String>("bookAuthor")));
        bookGenre.setCellValueFactory((new PropertyValueFactory<Books,String>("bookGenre")));
        bookYear.setCellValueFactory((new PropertyValueFactory<Books,String>("bookYear")));

        listN = connectDB.getDataBooks();
        tableBooks.setItems(listN);
    }

    @FXML
    void initialize() {
        UpdateTableBooks();
    }
}
