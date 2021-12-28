package vut.cz.bpcbdsproject3.Postgre;

import javafx.beans.property.*;

public class AppDetailedView
{
    private final LongProperty film_id = new SimpleLongProperty();
    private final StringProperty film_name = new SimpleStringProperty();
    private final IntegerProperty pegi = new SimpleIntegerProperty();
    private final StringProperty air_time = new SimpleStringProperty();
    private final StringProperty theatre = new SimpleStringProperty();
    private final IntegerProperty screen = new SimpleIntegerProperty();

    public Long getFilmId()
    {
        return this.film_id.get();
    }

    public void setFilmId(Long filmId)
    {
        this.film_id.setValue(filmId);
    }

    public String getFilmName()
    {
        return this.film_name.get();
    }

    public void setFilmName(String filmName)
    {
        this.film_name.setValue(filmName);
    }

    public Integer getPegi()
    {
        return this.pegi.get();
    }

    public void setPegi(Integer pegi)
    {
        this.pegi.setValue(pegi);
    }

    public String getAirTime()
    {
        return this.air_time.get();
    }

    public void setAirTime(String airTime)
    {
        this.air_time.setValue(airTime);
    }

    public String getTheatre()
    {
        return this.theatre.get();
    }

    public void setTheatre(String theatre)
    {
        this.theatre.setValue(theatre);
    }

    public Integer getscreen()
    {
        return this.screen.get();
    }

    public void setScreen(Integer screen)
    {
        this.screen.setValue(screen);
    }
}
