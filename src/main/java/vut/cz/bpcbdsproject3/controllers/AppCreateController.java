package vut.cz.bpcbdsproject3.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.Postgre.AppCreateView;
import vut.cz.bpcbdsproject3.data.AppRepository;
import vut.cz.bpcbdsproject3.service.AppService;

import java.sql.Timestamp;
import java.util.Optional;

public class AppCreateController
{

    private static final Logger logger = LoggerFactory.getLogger(AppCreateController.class);

    @FXML
    private Button createNewMovieButton;
    @FXML
    private TextField filmIdTextField;
    @FXML
    private TextField filmNameTextField;
    @FXML
    private TextField pegiTextField;
    @FXML
    private TextField airTimeTextField;
    @FXML
    private TextField screenTextField;

    private AppService appService;
    private AppRepository appRepository;
    private ValidationSupport validation;

    @FXML
    public void initialize()
    {
        appRepository = new AppRepository();
        appService = new AppService(appRepository);

        validation = new ValidationSupport();
        validation.registerValidator(filmNameTextField, Validator.createEmptyValidator("Film Name must not be empty"));
        validation.registerValidator(pegiTextField, Validator.createEmptyValidator("PEGI must not be empty"));
        validation.registerValidator(airTimeTextField, Validator.createEmptyValidator("Air Time must not be empty"));
        validation.registerValidator(screenTextField, Validator.createEmptyValidator("Screen ID must not be empty"));

        //createNewMovieButton.disableProperty().bind(validation.invalidProperty());  // This causes FXML to fail to load
        logger.info("AppCreateController initialized");
    }

    @FXML
    void handleCreateFilm(ActionEvent event)
    {
        AppCreateView createView = new AppCreateView();
        createView.setName(filmNameTextField.getText());
        createView.setPegi(Integer.valueOf(pegiTextField.getText()));
        createView.setAirTime(Timestamp.valueOf(airTimeTextField.getText()));
        createView.setScreen(Long.valueOf(screenTextField.getText()));

        appService.createMovie(createView);
        filmCreatedConfirmationDialog();
    }

    private void filmCreatedConfirmationDialog()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Movie Created");
        alert.setHeaderText("Movie Created");
        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();
        Optional<ButtonType> result = alert.showAndWait();
    }


}
