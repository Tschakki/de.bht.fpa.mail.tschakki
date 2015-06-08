package controller;

import java.util.Observable;

/**
 * Created by tschakki on 09.06.15.
 */
public class FolderSelectionObservable extends Observable {
    public void changeSomething() {

        // Notify observers of change
        setChanged();
        notifyObservers();
    }
}
