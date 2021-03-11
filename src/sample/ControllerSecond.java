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
    void addUsers(ActionEvent event) {

    }

    @FXML
    void getOnAction(ActionEvent event) {

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


    @FXML
    void initialize() {
        bookID.setCellValueFactory((new PropertyValueFactory<Books,Integer>("bookID")));
        bookName.setCellValueFactory((new PropertyValueFactory<Books,String>("bookName")));
        bookAuthor.setCellValueFactory((new PropertyValueFactory<Books,String>("bookAuthor")));
        bookGenre.setCellValueFactory((new PropertyValueFactory<Books,String>("bookGenre")));
        bookYear.setCellValueFactory((new PropertyValueFactory<Books,String>("bookYear")));

        listN = connectDB.getDataBooks();
        tableBooks.setItems(listN);
    }
}
