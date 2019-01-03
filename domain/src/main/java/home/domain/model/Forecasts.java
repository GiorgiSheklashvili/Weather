package home.domain.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@Entity
@XmlRootElement(name="forecasts")
@Table(name = "forecasts")
public class Forecasts
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Forecast> forecast;

    public List<Forecast> getForecast() {
        return forecast;
    }
    @XmlElement(name = "forecast")
    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [forecast = "+ forecast +"]";
    }
}

