package model;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Created by tschakki on 22.05.15.
 */
@XmlRootElement(name = "messages")
public class MessageListWrapper {
    private List<Message> messages;

    @XmlElement(name = "person")
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
