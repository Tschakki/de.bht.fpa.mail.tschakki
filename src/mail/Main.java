package mail;
import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Message;

import java.io.IOException;


public class Main extends Application {

    //private BorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("../view/mailmain.fxml"));
        primaryStage.setTitle("FPA: Mehli-MailClient");
        primaryStage.getIcons().add(new Image("cat32.png"));
        primaryStage.setScene(new Scene(root, 1000, 600));
        TreeView folderWindow = FXMLLoader.load(getClass().getResource("../view/mailfolders.fxml"));
        root.setLeft(folderWindow);
        SplitPane contentWindow = FXMLLoader.load(getClass().getResource("../view/mailmessage.fxml"));
        root.setCenter(contentWindow);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }


}
