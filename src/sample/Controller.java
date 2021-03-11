package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TableView<?> tableUsers;

    @FXML
    private TableColumn<?, ?> userID;

    @FXML
    private TableColumn<?, ?> userName;

    @FXML
    private TableColumn<?, ?> userDocument;

    @FXML
    private TableColumn<?, ?> userPhone;

    @FXML
    private TableColumn<?, ?> userAddress;

    @FXML
    private TableColumn<?, ?> userBooks;

    @FXML
    void getOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfUserID != null : "fx:id=\"tfUserID\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfUserName != null : "fx:id=\"tfUserName\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfUserDocument != null : "fx:id=\"tfUserDocument\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfUserPhone != null : "fx:id=\"tfUserPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfUserAddress != null : "fx:id=\"tfUserAddress\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfUserBooks != null : "fx:id=\"tfUserBooks\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnTableUsers != null : "fx:id=\"btnTableUsers\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnTableBooks != null : "fx:id=\"btnTableBooks\" was not injected: check your FXML file 'sample.fxml'.";
        assert tableUsers != null : "fx:id=\"tableUsers\" was not injected: check your FXML file 'sample.fxml'.";
        assert userID != null : "fx:id=\"userID\" was not injected: check your FXML file 'sample.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'sample.fxml'.";
        assert userDocument != null : "fx:id=\"userDocument\" was not injected: check your FXML file 'sample.fxml'.";
        assert userPhone != null : "fx:id=\"userPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert userAddress != null : "fx:id=\"userAddress\" was not injected: check your FXML file 'sample.fxml'.";
        assert userBooks != null : "fx:id=\"userBooks\" was not injected: check your FXML file 'sample.fxml'.";

    }
}
