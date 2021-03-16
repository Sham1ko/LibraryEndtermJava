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

public class ControllerSecond {

    @FXML
    private AnchorPane booksView;

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
    private TextField tfSearch;


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

    ObservableList<Books> listN;
    ObservableList<Books> dataList;


    int index = -1;

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;


    @FXML
    void getOnAction(ActionEvent event) {

    }

    @FXML
    public void addBooks(ActionEvent event){

        connection = connectDB.getConnection();
        String sql = "insert into books (bookId,bookName,bookAuthor,bookGenre,bookYear) values(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(tfBookID.getText()));
            preparedStatement.setString(2,tfBookName.getText());
            preparedStatement.setString(3,tfBookAuthor.getText());
            preparedStatement.setString(4,tfBookGenre.getText());
            preparedStatement.setString(5,tfBookYear.getText());
            preparedStatement.execute();
            UpdateTableBooks();

            JOptionPane.showMessageDialog(null,"Book has added successfully");
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
    public void updateBooks(ActionEvent event){

        try {
            connection = connectDB.getConnection();
            int value1=Integer.parseInt(tfBookID.getText());
            String value2=tfBookName.getText();
            String value3=tfBookAuthor.getText();
            String value4=tfBookGenre.getText();
            String value5=tfBookYear.getText();

            String sql = "update books set bookId='" + value1 + "',bookname='" +
                    value2 + "',bookauthor='" + value3 + "',bookgenre='" + value4 + "',bookyear='" +
                    value5 + "'where bookid='" + value1+"' ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null,"Book has edited successfully");
            UpdateTableBooks();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }


    }

    @FXML
    void getSelected(MouseEvent event){
        index=tableBooks.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        tfBookID.setText(bookID.getCellData(index).toString());
        tfBookName.setText(bookName.getCellData(index));
        tfBookAuthor.setText(bookAuthor.getCellData(index));
        tfBookGenre.setText(bookGenre.getCellData(index));
        tfBookYear.setText(bookYear.getCellData(index));

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

    @FXML
    void searchBook(){
        bookName.setCellValueFactory((new PropertyValueFactory<>("bookName")));
        bookAuthor.setCellValueFactory((new PropertyValueFactory<>("bookAuthor")));
        bookGenre.setCellValueFactory((new PropertyValueFactory<>("bookGenre")));
        bookYear.setCellValueFactory((new PropertyValueFactory<>("bookYear")));

        dataList = connectDB.getDataBooks();
        tableBooks.setItems(dataList);
        FilteredList<Books> filteredData = new FilteredList<>(dataList, b -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getBookName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getBookAuthor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getBookGenre().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else return person.getBookYear().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Books> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableBooks.comparatorProperty());
        tableBooks.setItems(sortedData);
    }

    public void UpdateTableBooks(){
        bookID.setCellValueFactory((new PropertyValueFactory<>("bookID")));
        bookName.setCellValueFactory((new PropertyValueFactory<>("bookName")));
        bookAuthor.setCellValueFactory((new PropertyValueFactory<>("bookAuthor")));
        bookGenre.setCellValueFactory((new PropertyValueFactory<>("bookGenre")));
        bookYear.setCellValueFactory((new PropertyValueFactory<>("bookYear")));

        listN = connectDB.getDataBooks();
        tableBooks.setItems(listN);
        searchBook();
    }

    @FXML
    void initialize() {
        UpdateTableBooks();
    }
}
