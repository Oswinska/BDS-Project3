package vut.cz.bpcbdsproject3.Postgre;

import javafx.beans.property.*;

public class AppBasicView
{
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty pegi = new SimpleIntegerProperty();
    private final StringProperty airTime = new SimpleStringProperty();

    public Long getId()
    {
        return idProperty().get();
    }

    public String getName()
    {
        return nameProperty().get();
    }

    public Integer getPegi()
    {
        return pegiProperty().get();
    }

    public String getAirTime()
    {
        return airTimeProperty().get();
    }

    public void setId(Long id)
    {
        this.idProperty().setValue(id);
    }

    public void setName(String name)
    {
        this.nameProperty().setValue(name);
    }

    public void setPegi(Integer pegi)
    {
        this.pegiProperty().setValue(pegi);
    }

    public void setAirtime(String airTime)
    {
        this.airTimeProperty().setValue(airTime);
    }

    public LongProperty idProperty()
    {
        return id;
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public IntegerProperty pegiProperty()
    {
        return pegi;
    }

    public StringProperty airTimeProperty()
    {
        return airTime;
    }

}