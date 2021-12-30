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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.Postgre.AppDetailedView;
import vut.cz.bpcbdsproject3.Postgre.AppEditView;
import vut.cz.bpcbdsproject3.data.AppRepository;
import vut.cz.bpcbdsproject3.service.AppService;

import java.sql.Timestamp;
import java.util.Optional;


public class AppEditController
{

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    public Stage stage;
    @FXML
    private Button editMovieButton;
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
    private AppService appDetailedService;
    private AppRepository appDetailedRepository;
    private ValidationSupport validation;


    public AppEditController()
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

        validation = new ValidationSupport();
        validation.registerValidator(filmIdTextField, Validator.createEmptyValidator("ID must not be empty"));
        filmIdTextField.setEditable(false);
        validation.registerValidator(filmNameTextField, Validator.createEmptyValidator("Name must not be empty"));
        validation.registerValidator(pegiTextField, Validator.createEmptyValidator("PEGI must not be empty"));
        validation.registerValidator(airTimeTextField, Validator.createEmptyValidator("Air Time must not be empty"));
        editMovieButton.disableProperty().bind(validation.invalidProperty());

        loadWantedData();
        logger.info("AppEdit Controller initialized");
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

    @FXML
    public void handleEditMovieButton(ActionEvent event)
    {
        Long id = Long.valueOf(filmIdTextField.getText());
        String name = filmNameTextField.getText();
        Integer pegi = Integer.valueOf(pegiTextField.getText());
        Timestamp airtime = Timestamp.valueOf(airTimeTextField.getText());
        String theatre = theatreTextField.getText();
        Integer screen = Integer.valueOf(screenTextField.getText());

        AppEditView appEditView = new AppEditView();
        appEditView.setId(id);
        appDetailedService.editMovie(appEditView);
        editConfirmation();
    }

    private void editConfirmation()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Movie Edited");
        alert.setHeaderText("Movie Edited");
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
