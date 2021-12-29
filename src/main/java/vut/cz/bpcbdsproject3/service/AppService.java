package vut.cz.bpcbdsproject3.service;

import vut.cz.bpcbdsproject3.Postgre.AppBasicView;
import vut.cz.bpcbdsproject3.Postgre.AppDetailedView;
import vut.cz.bpcbdsproject3.data.AppRepository;

import java.util.List;

public class AppService
{
    private final AppRepository appRepository;

    public AppService(AppRepository appRepository)
    {
        this.appRepository = appRepository;
    }

    // Basic View
    public List<AppBasicView> getMovieBasicView()
    {
        return appRepository.getAllMovies();
    }

    // Detailed View
    public AppDetailedView getSelectedMovie(Long id)
    {
        return appRepository.getDetailedView(id);
    }
}

