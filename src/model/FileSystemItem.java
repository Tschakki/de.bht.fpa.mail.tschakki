package model;

import javafx.scene.control.TreeItem;

import java.io.File;

/**
 * Created by tschakki on 08.06.15.
 */
public class FileSystemItem extends TreeItem {
    protected File file;
    private boolean isFirstTimeLeaf;
    private boolean isLeaf;

    public FileSystemItem(File file){
        super(file.getName());
        this.file = file;
        isFirstTimeLeaf = true;
    }

    public boolean isLeaf() {
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            isLeaf = file.isFile();
            //file.isFile();
        }
        return isLeaf;
    }//leaf == null ? true : leaf.getValue(); }
}
