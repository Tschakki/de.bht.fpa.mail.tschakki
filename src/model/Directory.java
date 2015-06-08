package model;

import java.io.File;

/**
 * Created by tschakki on 08.06.15.
 */
public class Directory extends FileSystemItem  {

    public Directory(File file) {
        super(file);
        init();
    }

    private void init(){
        File[] fileList = file.listFiles();
        for (File datei : fileList){
            if (datei.isDirectory()){
                Directory folder = new Directory(datei);
                super.getChildren().add(folder);
            }
        }
    }
}
