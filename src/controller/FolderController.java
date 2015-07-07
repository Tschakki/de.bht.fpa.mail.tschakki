package controller;

import java.io.File;
import java.io.Serializable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Directory;

import java.net.URL;

/**
 * Created by tschakki on 04.05.15.
 */
public class
        FolderController implements Initializable {

    @FXML
    private TreeView<String> dateiBaum;
    private Directory rootDir = new Directory(new File("TreeRoot"));
    //private FolderSelectionObservable treeObservable;
    //private MessageController msgController;

    /*private final Node folderIcon = new ImageView(
            new Image(getClass().getResourceAsStream("../cat16.png"))
    );*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TreeItem<String> treeRoot = new TreeItem<>("Folder");
        treeRoot.setGraphic(new ImageView(
                new Image(getClass().getResourceAsStream("../ordner.png"))
        ));
        for(int i = 1; i < 6; ++i) {
           // TreeItem<String> subFolder = new TreeItem<>("Subfolder " + i);
            treeRoot.getChildren().add(new TreeItem<String>("Subfolder " + i));

        }

        for (int i=0; i<5; i++) {
            treeRoot.getChildren().get(i).setGraphic(new ImageView(
                    new Image(getClass().getResourceAsStream("../ordner.png"))
            ));
        }
        treeRoot.setExpanded(true);

        dateiBaum.setRoot(rootDir);
        //msgController = new MessageController();
        //treeObservable = FolderSelectionObservable.getInstance();
        //treeObservable.addObserver(msgController);
        treeListener();

    }

    public void treeListener(){
        dateiBaum.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<String>>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends TreeItem<String>> observable,
                            TreeItem<String> old_val, TreeItem<String> new_val) {
                        TreeItem<String> selectedItem = new_val;
                        System.out.println("Selected Text : " + selectedItem.getValue());
                        FolderSelectionObservable.getInstance().changeSomething(selectedItem.getValue());
                    }

                });
    }

}
