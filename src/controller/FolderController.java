package controller;

import java.io.Serializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

/**
 * Created by tschakki on 04.05.15.
 */
public class FolderController implements Initializable {

    @FXML
    private TreeView<String> dateiBaum;

    /*private final Node folderIcon = new ImageView(
            new Image(getClass().getResourceAsStream("../cat16.png"))
    );*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TreeItem<String> treeRoot = new TreeItem<>("Folder");
        treeRoot.setGraphic(new ImageView(
                new Image(getClass().getResourceAsStream("../cat16.png"))
        ));
        for(int i = 1; i < 6; ++i) {
           // TreeItem<String> subFolder = new TreeItem<>("Subfolder " + i);
            treeRoot.getChildren().add(new TreeItem<String>("Subfolder " + i));

        }

        for (int i=0; i<5; i++) {
            treeRoot.getChildren().get(i).setGraphic(new ImageView(
                    new Image(getClass().getResourceAsStream("../cat16.png"))
            ));
        }
        treeRoot.setExpanded(true);
        dateiBaum.setRoot(treeRoot);
    }
}
