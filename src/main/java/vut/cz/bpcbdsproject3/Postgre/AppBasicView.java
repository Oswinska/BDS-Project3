package vut.cz.bpcbdsproject3.Postgre;

import javafx.beans.property.*;

public class AppBasicView
{
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty airtime = new SimpleStringProperty();
    private final IntegerProperty pegi = new SimpleIntegerProperty();

    public Long getId()
    {
        return idProperty().get();
    }

    public void setId(Long id)
    {
        this.idProperty().setValue(id);
    }

    public String getName()
    {
        return nameProperty().get();
    }

    public void setName(String name) {
        this.nameProperty().setValue(name);
    }

    public String getAirTime() {
        return airTimeProperty().get();
    }

    public void setAirtime(String airtime) {
        this.airTimeProperty().setValue(airtime);
    }

    public Integer pegi() {
        return pegiProperty().get();
    }

    public void setPegi(Integer pegi) {
        this.pegiProperty().setValue(pegi);
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty airTimeProperty() {
        return airtime;
    }

    public IntegerProperty pegiProperty() {
        return pegi;
    }

}