package vut.cz.bpcbdsproject3.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.data.AppDetailedRepository;
import vut.cz.bpcbdsproject3.service.AppDetailedService;

public class AppDetailedController
{
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @FXML
    private TextField filmIdTextField;
    @FXML
    private TextField filmNameTextField;
    @FXML
    private TextField pegiTextField;
    @FXML
    private TextField airTimeTextField;
    @FXML
    private TextField theatreTextField;
    @FXML
    private TextField screenTextField;

    private AppDetailedService appDetailedService;
    private AppDetailedRepository appDetailedRepository;


    public AppDetailedController()
    {
    }


    @FXML
    private void initialize()
    {
        appDetailedRepository = new AppDetailedRepository();
        appDetailedService = new AppDetailedService(appDetailedRepository);


        logger.info("AppDetailed Controller initialized");
    }
}