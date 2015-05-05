package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Message;
import model.MessageImportance;
import model.MessageStakeholder;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Created by tschakki on 04.05.15.
 */
public class MessageController implements Initializable{

    private ObservableList<Message> messageData = FXCollections.observableArrayList();

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

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
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
}
