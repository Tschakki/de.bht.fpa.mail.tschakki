package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tschakki on 07.07.15.
 */
public class IsSubjectFilter extends Filter {

    public IsSubjectFilter(FpaMessageLoader fpaMessageLoader, String filterCrit){
        super(fpaMessageLoader, filterCrit);
    }


    @Override
    public ObservableList<Message> getMessages(String path) {
        ObservableList<Message> msgList = fpaMessageLoader.getMessages(path);
        ObservableList<Message> filteredMsgList = FXCollections.observableArrayList();
        for (Message msg : msgList){
            if (msg.getSubject().equalsIgnoreCase(filterCriteria)){
                filteredMsgList.add(msg);
            }
        }
        return filteredMsgList;
    }

    /**
     * Opens the xml file, reads all the information and returns a new message
     * object.
     *
     * @param file The passed xml file
     * @return The resulting Message object
     */
    private Message readMessage(File file) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Message.class);
            Unmarshaller um = jc.createUnmarshaller();
            return (Message) um.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
