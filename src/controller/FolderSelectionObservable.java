package controller;

import java.util.Observable;

/**
 * Created by tschakki on 09.06.15.
 */
public class FolderSelectionObservable extends Observable {

    private static FolderSelectionObservable instance = new FolderSelectionObservable();

    private FolderSelectionObservable(){

    }

    public static FolderSelectionObservable getInstance(){
        return FolderSelectionObservable.instance;
    }
    public void changeSomething(String pfad) {

        // Notify observers of change
        setChanged();
        notifyObservers(pfad);
    }
}
