package mail;
import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Message;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/mailmain.fxml"));
        primaryStage.setTitle("FPA: Mehli-MailClient");
        primaryStage.getIcons().add(new Image("cat32.png"));
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }


}
