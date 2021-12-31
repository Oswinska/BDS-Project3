package vut.cz.bpcbdsproject3.Postgre;

import java.sql.Timestamp;

public class AppEditView
{
    private Long id;
    private String name;
    private Integer pegi;
    private Timestamp airTime;
    private String theatre;
    private Long screen;

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public Integer getPegi()
    {
        return this.pegi;
    }

    public Timestamp getAirTime()
    {
        return this.airTime;
    }

    public String getTheatre()
    {
        return this.theatre;
    }

    public Long getScreen()
    {
        return this.screen;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setPegi(Integer pegi)
    {
        this.pegi = pegi;
    }
    public void setAirTime(Timestamp airTime)
    {
        this.airTime = airTime;
    }

    public void setTheatre(String theatre)
    {
        this.theatre = theatre;
    }

    public void setScreen(Long screen)
    {
        this.screen = screen;
    }
}
