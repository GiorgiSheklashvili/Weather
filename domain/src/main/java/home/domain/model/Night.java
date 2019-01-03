package home.domain.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@Entity
@XmlRootElement(name = "night")
@Table(name = "nights")
public class Night {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String phenomenon;
    private String tempmax;
    private String tempmin;
    @Column(length = 1024)
    private String text;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Place> places;

    public List<Place> getPlaces() {
        return places;
    }
    @XmlElement(name = "place")
    public void setPlaces(List<Place> places) {
        this.places = places;
    }



    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public String getTempmax() {
        return tempmax;
    }

    public void setTempmax(String tempmax) {
        this.tempmax = tempmax;
    }

    public String getTempmin() {
        return tempmin;
    }

    public void setTempmin(String tempmin) {
        this.tempmin = tempmin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ClassPojo [phenomenon = " + phenomenon + ", tempmax = " + tempmax + ", tempmin = " + tempmin + ", text = " + text + "]";
    }
}