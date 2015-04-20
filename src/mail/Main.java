package mail;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import messages.Message;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mailmain.fxml"));
        primaryStage.setTitle("FPA: Mehli-MailClient");
        primaryStage.getIcons().add(new Image("cat32.png"));
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    private ObservableList<Message> messageData = FXCollections.observableArrayList();

    public Main() {
        // Add some sample data
        messageData.add(new Message());
        messageData.add(new Message());
        messageData.add(new Message());
        messageData.add(new Message());
        messageData.add(new Message());
        messageData.add(new Message());
        messageData.add(new Message());
        messageData.add(new Message());
        messageData.add(new Message());
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Message> getMessageData() {
        return messageData;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
