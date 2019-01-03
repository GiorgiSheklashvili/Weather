package home.domain.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement(name="forecast")
@Table(name = "forecast")
public class Forecast
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Night night;
    @OneToOne(cascade = CascadeType.ALL)
    private Day day;
    @XmlAttribute(name = "date")
    public String date;

    public Long getId() {
        return id;
    }


    public Night getNight ()
    {
        return night;
    }

    public void setNight (Night night)
    {
        this.night = night;
    }

    public Day getDay ()
    {
        return day;
    }

    public void setDay (Day day)
    {
        this.day = day;
    }


    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [night = "+night+", day = "+day+", date = "+date+"]";
    }
}