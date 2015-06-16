package controller;

import com.sun.webkit.ContextMenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Message;
import model.MessageImportance;
import model.MessageListWrapper;
import model.MessageStakeholder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * Created by tschakki on 04.05.15.
 */
public class MessageController implements Initializable, Observer {

    private ObservableList<Message> messageData = FXCollections.observableArrayList();

    @FXML
    private TableView<Message> messageTable;
    @FXML
    private TableColumn<Message, MessageImportance> importanceOfMessageColumn;
    @FXML
    //private TableColumn<Message, LocalDateTime> receivedAtColumn;
    private TableColumn<Message, LocalDateTime> receivedAtColumn;
    @FXML
    private TableColumn<Message, Boolean> readStatusColumn;
    @FXML
    private TableColumn<Message, String> senderColumn;
    @FXML
    private TableColumn<Message, String> subjectColumn;
    @FXML
    private MenuItem markAsUnread;
    @FXML
    private TextArea textArea;
    @FXML
    private Label recipientsLabel;
    @FXML
    private Label fromLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label dateLabel;


    /*private final Node importanceIcon = new ImageView(
            new Image(getClass().getResourceAsStream("../cat32.png"))
    );*/

    @FXML
    public void markUnread(){
        messageTable.getSelectionModel().getSelectedItem().setReadStatus(false);
        try {
            saveMessage(messageTable.getSelectionModel().getSelectedItem());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        connectTableCells();
        //generateMessages();
        fillTable("TreeRoot/INBOX");
        System.out.println("initialize messagetable");
        FolderSelectionObservable.getInstance().addObserver(this);
        // Clear person details.
        showMessageDetails(null);
        // Listen for selection changes and show the person details when changed.
        messageTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMessageDetails(newValue));

    }

    private void connectTableCells(){
        importanceOfMessageColumn.setCellValueFactory(cellData -> cellData.getValue().importanceOfMessageProperty());
        importanceOfMessageColumn.setCellFactory(cellData -> new TableCell<Message, MessageImportance>() {
            @Override
            protected void updateItem(MessageImportance item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null) {
                    if (item.getImportanceAsString().equals("low")) {
                        setGraphic(new ImageView(
                                new Image(getClass().getResourceAsStream("../low.png"))
                        ));
                    } else if (item.getImportanceAsString().equals("normal")) {
                        setGraphic(new ImageView(
                                new Image(getClass().getResourceAsStream("../normal.png"))
                        ));
                    } else {
                        setGraphic(new ImageView(
                                new Image(getClass().getResourceAsStream("../high.png"))
                        ));
                    }

                }
            }
        });
        //receivedAtColumn.setCellValueFactory(cellData -> cellData.getValue().receivedAtProperty());
        receivedAtColumn.setCellValueFactory(cellData -> cellData.getValue().receivedAtProperty());
        readStatusColumn.setCellFactory(cellData -> new TableCell<Message, Boolean>() {
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    if (!item) {
                        setGraphic(new ImageView(
                                new Image(getClass().getResourceAsStream("../readFalse.png"))
                        ));
                    } else {
                        setGraphic(new ImageView(
                                new Image(getClass().getResourceAsStream("../readTrue.png"))
                        ));
                    }
                }

            }
        });

        readStatusColumn.setCellValueFactory(cellData -> cellData.getValue().readStatusProperty().asObject());
        senderColumn.setCellValueFactory(cellData -> cellData.getValue().senderProperty().get().nameProperty());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
    }

    /**
     *
     *
     */
    public void saveMessage(Message msg) throws JAXBException{
        final File currentDir = new File("TreeRoot/INBOX");
        JAXBContext context = JAXBContext.newInstance(Message.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(msg, new File(currentDir.getAbsolutePath() + "/" + msg.getId() + ".xml"));
    }


    public ObservableList<Message> getMessageData() {
        return messageData;
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param message the person or null
     */
    private void showMessageDetails(Message message) {
        if (message != null) {
            setMessageAsRead(message);
            // Fill the labels with info from the person object.
            fromLabel.setText(String.valueOf(message.getSender().getMailAddress()));
            recipientsLabel.setText(String.valueOf(message.getRecipients().get(0).getMailAddress()));
            dateLabel.setText(String.valueOf(DateUtil.format(message.getReceivedAt())));
            subjectLabel.setText(message.getSubject());
            textArea.setText(message.getText());

            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            fromLabel.setText("");
            recipientsLabel.setText("");
            dateLabel.setText("");
            subjectLabel.setText("");
            textArea.setText("");
        }
    }

    /**
     * Opens the xml file, reads all the information and returns a new message
     * object.
     *
     * @param file The passed xml file
     * @return The resulting Message object
     */
    private Message readMessage(File file) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Message.class);
            Unmarshaller um = jc.createUnmarshaller();
            return (Message) um.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Fills the table by the xml files within the passed path.
     *
     * @param path the path containing the xml files.
     */
    private void fillTable(String path) {
        File file = new File(path);
        for (File each : file.listFiles()) {
            messageData.add(readMessage(each));
        }
        if (messageData != null) {
            messageTable.setItems(messageData);
        }

    }

    private void setMessageAsRead(Message message){
        message.setReadStatus(true);
        try {
            saveMessage(messageTable.getSelectionModel().getSelectedItem());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void update(Observable o, Object arg) {
        System.out.println("ARG Text : " + "TreeRoot/" + arg.toString());
        String pfad = "TreeRoot/"+arg.toString();
        messageTable.getItems().clear();
        fillTable(pfad);
    }









//Message


/*    private void generateMessages() {
        int index = 0;
        MessageStakeholder stakeHolder = new MessageStakeholder("Tschakki", "tschakki@mehli.org");
        LinkedList<MessageStakeholder> absenderListe = new LinkedList<MessageStakeholder>();
        absenderListe.add(index, stakeHolder);
        Message nachricht = new Message();
        nachricht.setSubject("Betreff der Nachricht");
        nachricht.setSender(new MessageStakeholder("Catz", "flying@cat.org"));
        nachricht.setImportanceOfMessage(MessageImportance.HIGH);
        nachricht.setReceivedAt(LocalDateTime.now());
        nachricht.setText("Hallo sagt die Catz!");
        //nachricht.setRecipients(new MessageStakeholder("Tschakki", "tschakki@mehli.org"));
        nachricht.setRecipients(absenderListe);
        Message nachricht2 = new Message();
        nachricht2.setSubject("zweiter Betreff der Nachricht");
        nachricht2.setSender(new MessageStakeholder("Mehli", "mehli@entogrow.org"));
        nachricht2.setImportanceOfMessage(MessageImportance.NORMAL);
        //nachricht2.setRecipients(new MessageStakeholder("Tschakki", "tschakki@mehli.org"));
        nachricht2.setRecipients(absenderListe);
        nachricht2.setReceivedAt(LocalDateTime.now());
        nachricht2.setText("Viele Grüße von Mehli");
        nachricht2.setReadStatus(true);
        messageData.add(nachricht);
        messageData.add(nachricht2);
        messageData.add(nachricht);
        messageData.add(nachricht2);
        messageData.add(nachricht);
        messageData.add(nachricht2);
    }*/

    /**
     * Returns all .xml files from the given path as an Array filled with files.
     *
     * @return File[]
     */
    /*public File[] loadFiles() {
        final String extension = ".xml";
        final File currentDir = new File("../xml-messages/");
        return currentDir.listFiles((File pathname) -> pathname.getName().endsWith(extension));
    }*/

    /**
     * This method creates Message objects out of XML-files.
     */
    /*public void createExampleMessages() {
        File[] files = loadFiles();
        for (File file : files) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Message msg = (Message) jaxbUnmarshaller.unmarshal(file);
                messageData.add(msg);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }*/



}