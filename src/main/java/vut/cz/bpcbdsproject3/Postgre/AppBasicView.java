package vut.cz.bpcbdsproject3.Postgre;

import javafx.beans.property.*;

public class AppBasicView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty airtime = new SimpleStringProperty();
    private IntegerProperty pegi = new SimpleIntegerProperty();
    private StringProperty theatre = new SimpleStringProperty();

    public Long getId() {
        return idProperty().get();
    }

    public void setId(Long id) {
        this.idProperty().setValue(id);
    }

    public String getName() {
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

    public String getTheatre() {
        return theatreProperty().get();
    }

    public void setTheatre(String theatre) {
        this.theatre.setValue(theatre);
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

    public StringProperty theatreProperty() {
        return theatre;
    }

}