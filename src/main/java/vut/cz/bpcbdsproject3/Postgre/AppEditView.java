package vut.cz.bpcbdsproject3.Postgre;

import java.sql.Timestamp;

public class AppEditView
{
    private Long id;
    private String name;
    private Integer pegi;
    private Timestamp airTime;
    private String theatre;
    private Integer screen;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public String getTheatre()
    {
        return theatre;
    }

    public void setTheatre(String theatre)
    {
        this.theatre = theatre;
    }

    public Integer getScreen()
    {
        return screen;
    }

    public void setScreen(Integer screen)
    {
        this.screen = screen;
    }
}
