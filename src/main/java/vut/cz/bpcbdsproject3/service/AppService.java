package vut.cz.bpcbdsproject3.service;

import vut.cz.bpcbdsproject3.Postgre.AppBasicView;
import vut.cz.bpcbdsproject3.data.AppRepository;

import java.util.List;

public class AppService
{
    private AppRepository appRepository;

    public AppService(AppRepository appRepository)
    {
        this.appRepository = appRepository;
    }
    public List<AppBasicView> getMovieBasicView()
    {
        return appRepository.getAllMovies();
    }
}

