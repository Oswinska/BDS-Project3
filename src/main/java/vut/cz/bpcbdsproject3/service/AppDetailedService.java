package vut.cz.bpcbdsproject3.service;

import vut.cz.bpcbdsproject3.Postgre.AppDetailedView;
import vut.cz.bpcbdsproject3.data.AppDetailedRepository;

public class AppDetailedService
{
    private final AppDetailedRepository appDetailedRepository;

    public AppDetailedService(AppDetailedRepository appDetailedRepository)
    {
        this.appDetailedRepository = appDetailedRepository;
    }

    public AppDetailedView getSelectedMovie(Long id)
    {
        return appDetailedRepository.getDetailedView(id);
    }
}
