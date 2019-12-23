package sjn.project.djcode.value_objects;

import java.io.Serializable;

public class Reservation implements Serializable {
    String name;
    String tel_no;

    public Reservation() {

    }

    public Reservation(String name, String tel_no) {
        this.name = name;
        this.tel_no = tel_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel_no() {
        return tel_no;
    }

    public void setTel_no(String tel_no) {
        this.tel_no = tel_no;
    }
}
