package controller;

import javafx.collections.ObservableList;
import model.Message;

import java.util.List;

/**
 * Created by tschakki on 30.06.15.
 */
public abstract class FpaMessageLoader {

    public abstract ObservableList<Message> getMessages(String path);

}
