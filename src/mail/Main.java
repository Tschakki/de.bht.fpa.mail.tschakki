package mail;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mailmain.fxml"));
        primaryStage.setTitle("FPA Mail Client");
        primaryStage.getIcons().add(new Image("cat32.png"));
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
