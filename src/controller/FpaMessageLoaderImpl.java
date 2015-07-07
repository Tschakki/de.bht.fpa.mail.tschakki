package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tschakki on 30.06.15.
 */
public class FpaMessageLoaderImpl extends FpaMessageLoader {

    private ObservableList<Message> messageData = FXCollections.observableArrayList();


    public ObservableList<Message> getMessages(String path) {
        File file = new File(path);
        //if (each.isFile() && each.getName().endsWith(".xml"))
        if (file.listFiles() != null) {
            for (File each : file.listFiles()) {

                messageData.add(readMessage(each));
            }

        }
        return messageData;
        //hier einfach fillTable und getMessage reinkopieren (mit observalbeList statt List<Message>)
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
