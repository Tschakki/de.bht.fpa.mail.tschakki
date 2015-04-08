package mail;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.awt.*;

public class Controller  implements Initializable {
        @FXML
        private MenuItem fileExit;
        @FXML
        private MenuItem helpAbout;
        @FXML
        private MenuItem editBasePath;
        @FXML
        private MenuItem filterSetFilter;

    public void handleMenuAction(ActionEvent event) {
        System.out.println("Exit");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileExit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Mehli sagt Tschüss!");
            }
        });
        helpAbout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Über Mehli gibt es viel wichtiges zu wissen!");
            }
        });
        editBasePath.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Mehli sagt Tschüss!");
            }
        });
        filterSetFilter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Mehli sagt Tschüss!");
            }
        });
    }
}
