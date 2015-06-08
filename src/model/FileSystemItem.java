package model;

import javafx.scene.control.TreeItem;

import java.io.File;

/**
 * Created by tschakki on 08.06.15.
 */
public class FileSystemItem extends TreeItem {
    protected File file;

    public FileSystemItem(File file){
        super(file.getName());
        this.file = file;
    }

    public boolean isLeaf() {
        return file.isFile();
    }//leaf == null ? true : leaf.getValue(); }
}
