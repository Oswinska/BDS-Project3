package vut.cz.bpcbdsproject3.Postgre;

import java.sql.Timestamp;

public class AppCreateView
{
    private String filmName;
    private Integer pegi;
    private Timestamp airTime;
    private Long filmId;
    private Long screenId;

    public String getFilmName()
    {
        return filmName;
    }

    public void setFilmName(String filmName)
    {
        this.filmName = filmName;
    }

    public Integer getPegi()
    {
        return pegi;
    }

    public void setPegi(Integer pegi)
    {
        this.pegi = pegi;
    }

    public Timestamp getAirTime()
    {
        return airTime;
    }

    public void setAirTime(Timestamp airTime)
    {
        this.airTime = airTime;
    }

    public Long getFilmId()
    {
        return filmId;
    }

    public void setFilmId(Long filmId)
    {
        this.filmId = filmId;
    }

    public Long getScreenId()
    {
        return screenId;
    }

    public void setScreenId(Long screenId)
    {
        this.screenId = screenId;
    }
}
