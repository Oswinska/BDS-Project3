package vut.cz.bpcbdsproject3.service;

import vut.cz.bpcbdsproject3.Postgre.AppBasicView;
import vut.cz.bpcbdsproject3.Postgre.AppCreateView;
import vut.cz.bpcbdsproject3.Postgre.AppDetailedView;
import vut.cz.bpcbdsproject3.Postgre.AppEditView;
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

    //Filtered View
    public List<AppBasicView> getFilteredView(Integer pegi)
    {
        return appRepository.getFilteredMovies(pegi);
    }

    // Create View
    public void createMovie(AppCreateView createView)
    {
        appRepository.createFilm(createView);
    }

    //Edit View
    public void editMovie(AppEditView editView)
    {
        appRepository.editMovie(editView);
    }
}

