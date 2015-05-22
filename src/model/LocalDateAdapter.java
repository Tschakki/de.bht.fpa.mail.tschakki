package model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * Created by tschakki on 22.05.15.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v);
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }
}
