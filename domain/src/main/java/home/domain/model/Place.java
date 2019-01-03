package home.domain.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement(name="place")
@Table(name = "place")
public class Place
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String phenomenon;
    private String tempmin;
    private String tempmax;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTempmax() {
        return tempmax;
    }

    public void setTempmax(String tempmax) {
        this.tempmax = tempmax;
    }

    public String getPhenomenon ()
    {
        return phenomenon;
    }

    public void setPhenomenon (String phenomenon)
    {
        this.phenomenon = phenomenon;
    }

    public String getTempmin ()
    {
        return tempmin;
    }

    public void setTempmin (String tempmin)
    {
        this.tempmin = tempmin;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [phenomenon = "+phenomenon+", tempmin = "+tempmin+", name = "+name+"]";
    }
}
