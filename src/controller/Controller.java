package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mail.Main;
import model.Message;
import model.MessageImportance;
import model.MessageStakeholder;

import java.awt.*;

import static java.time.LocalDate.*;

public class Controller  implements Initializable {

    private ObservableList<Message> messageData = FXCollections.observableArrayList();
        @FXML
        private MenuItem fileExit;
        @FXML
        private MenuItem helpAbout;
        @FXML
        private MenuItem editBasePath;
        @FXML
        private MenuItem filterSetFilter;
    @FXML
    private TableView<Message> messageTable;
    @FXML
    private TableColumn<Message, MessageImportance> importanceOfMessageColumn;
    @FXML
    private TableColumn<Message, LocalDateTime> receivedAtColumn;
    @FXML
    private TableColumn<Message, Boolean> readStatusColumn;
    @FXML
    private TableColumn<Message, String> senderColumn;
    @FXML
    private TableColumn<Message, String> subjectColumn;
    @FXML
    private TableColumn<Message, String> textColumn;
    @FXML
    private TableColumn<Message, String> recipientsColumn;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public Controller() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        //importanceOfMessageColumn.setCellValueFactory(cellData -> cellData.getValue().importanceOfMessageProperty());
        receivedAtColumn.setCellValueFactory(cellData -> cellData.getValue().receivedAtProperty());
        readStatusColumn.setCellValueFactory(cellData -> cellData.getValue().readStatusProperty().asObject());
        senderColumn.setCellValueFactory(cellData -> cellData.getValue().senderProperty().get().nameProperty());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        generateMessages();
        System.out.println(messageData.toString());
        System.out.println("test");
        messageTable.setItems(messageData);
    }

    private void generateMessages(){
        Message nachricht = new Message();
        nachricht.setSubject("Betreff der Nachricht");
        nachricht.setSender(new MessageStakeholder("Catz", "flying@cat.org"));
        //nachricht.setImportanceOfMessage(new MessageImportance("low"));
        nachricht.setReceivedAt(LocalDateTime.now());
        messageData.add(nachricht);
        messageData.add(nachricht);
        messageData.add(nachricht);
        messageData.add(nachricht);
        messageData.add(nachricht);
        messageData.add(nachricht);
    }

    public ObservableList<Message> getMessageData() {
        return messageData;
    }

   // public void handleMenuAction(ActionEvent event) {
     //   System.out.println("Exit");
    //}

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        fileExit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.out.println("Mehli sagt Tschüss!");
            }
        });
        helpAbout.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.out.println("Über Mehli gibt es viel wichtiges zu wissen!");
            }
        });
        editBasePath.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.out.println("Mehli setzt den BasePath");
            }
        });
        filterSetFilter.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.out.println("Mehli setzt den Filter");
            }
        });
        initialize();
    }
}
