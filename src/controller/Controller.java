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
import javafx.scene.control.Alert;
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

        @FXML
        private MenuItem fileExit;
        @FXML
        private MenuItem helpAbout;
        @FXML
        private MenuItem editBasePath;
        @FXML
        private MenuItem filterSetFilter;


    @FXML
    public void onClickExit(){
        System.out.println("Exit application");
        System.exit(0);
    }

    @FXML
    public void onClickSetBasePath(){
        System.out.println("Set Base Path...");
    }

    @FXML
    public void onClickSetFilter(){
        System.out.println("Set Filter...");
    }

    @FXML
    public void onClickAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Über Mehli-MailClient");
        alert.setHeaderText(null);
        alert.setContentText("Mehli bringt dir die Mails gemächlich");

        alert.showAndWait();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {


    }



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


   // public void handleMenuAction(ActionEvent event) {
     //   System.out.println("Exit");
    //}

    /*public void initialize(URL location, ResourceBundle resources) {
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
        //initialize();
    }*/
}
