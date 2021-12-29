package vut.cz.bpcbdsproject3.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.Postgre.AppDetailedView;
import vut.cz.bpcbdsproject3.data.AppRepository;
import vut.cz.bpcbdsproject3.service.AppService;

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

    public Stage stage;

    private AppService appDetailedService;
    private AppRepository appDetailedRepository;


    public AppDetailedController()
    {
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    @FXML
    private void initialize()
    {
        appDetailedRepository = new AppRepository();
        appDetailedService = new AppService(appDetailedRepository);

        loadWantedData();
        logger.info("AppDetailed Controller initialized");
    }

    private void loadWantedData()
    {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof AppDetailedView)
            {
                AppDetailedView appDetailedView = (AppDetailedView) stage.getUserData();

                filmIdTextField.setText(String.valueOf(appDetailedView.getFilmId()));
                filmNameTextField.setText(appDetailedView.getFilmName());
                pegiTextField.setText(String.valueOf(appDetailedView.getPegi()));
                airTimeTextField.setText(appDetailedView.getAirTime());
                theatreTextField.setText(appDetailedView.getTheatre());
                screenTextField.setText(String.valueOf(appDetailedView.getscreen()));
            }
    }
}