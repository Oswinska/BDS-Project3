package vut.cz.bpcbdsproject3.Postgre;

import javafx.beans.property.*;

public class AppDetailedView
{
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty pegi = new SimpleIntegerProperty();
    private final StringProperty airTime = new SimpleStringProperty();
    private final StringProperty theatre = new SimpleStringProperty();
    private final IntegerProperty screen = new SimpleIntegerProperty();

    public Long getId()
    {
        return this.id.get();
    }

    public void setId(Long id)
    {
        this.id.setValue(id);
    }

    public Integer getPegi()
    {
        return this.pegi.get();
    }

    public String getFilmName()
    {
        return this.name.get();
    }

    public String getTheatre()
    {
        return this.theatre.get();
    }

    public String getAirTime()
    {
        return this.airTime.get();
    }

    public void setAirTime(String airTime)
    {
        this.airTime.setValue(airTime);
    }

    public Integer getScreen()
    {
        return this.screen.get();
    }

    public void setName(String name)
    {
        this.name.setValue(name);
    }

    public void setPegi(Integer pegi)
    {
        this.pegi.setValue(pegi);
    }

    public void setTheatre(String theatre)
    {
        this.theatre.setValue(theatre);
    }

    public void setScreen(Integer screen)
    {
        this.screen.setValue(screen);
    }
}
