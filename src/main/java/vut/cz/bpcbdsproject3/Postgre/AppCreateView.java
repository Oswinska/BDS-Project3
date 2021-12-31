package vut.cz.bpcbdsproject3.Postgre;

import java.sql.Timestamp;

public class AppCreateView
{
    private Long id;
    private String name;
    private Integer pegi;
    private Timestamp airTime;
    private Long screen;

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getPegi()
    {
        return this.pegi;
    }

    public Timestamp getAirTime()
    {
        return this.airTime;
    }

    public Long getScreen()
    {
        return this.screen;
    }

    public void setPegi(Integer pegi)
    {
        this.pegi = pegi;
    }

    public void setAirTime(Timestamp airTime)
    {
        this.airTime = airTime;
    }

    public void setScreen(Long screen)
    {
        this.screen = screen;
    }
}
