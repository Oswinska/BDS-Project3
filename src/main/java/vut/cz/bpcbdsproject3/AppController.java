package vut.cz.bpcbdsproject3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import vut.cz.bpcbdsproject3.postgress.Authorization;
import vut.cz.bpcbdsproject3.postgress.postgreSQL;

import java.util.Optional;


public class AppController
{
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @FXML
    public Label usernameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button signInButton;

    private postgreSQL postgreSQL;
    private Authorization Authorization;

    private ValidationSupport validation;

    public AppController()
    {

    }

    @FXML
    private void init()
    {
        usernameText.setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ENTER)
                {
                    signIn();
                }
        });
        passwordText.setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ENTER)
                {
                    signIn();
                }
        });
        logger.info("AppController started");
    }

    private void initValidation()
    {
        validation = new ValidationSupport();
        validation.registerValidator(usernameText, Validator.createEmptyValidator("username must not be empty"));
    }

    private void signInAction(ActionEvent event)
    {
        signIn();
    }

    private void signIn()

    {
        String username = usernameText.getText();
        String password = passwordText.getText();

        System.out.print("Username: " + username);
        System.out.print("Password: " + password);

//        try
//            {
//                boolean authentication = authService.authenticate(username,password);
//                if (authentication)
//                    {
//                        showPersonsView();
//                    }
//                else
//                    {
//                        showInvalidDialog();
//                    }
//            }
//        catch (MissingResourceException | SQLDataException e)
//            {
//                showInvalidDialog();
//            }
    }

    private void showInvalidDialog()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unauthenticated");
        alert.setHeaderText("User is not authenticated");
        alert.setContentText("Provide valid username and password");
        alert.showAndWait();
    }

    private void authConfirmDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logging confirmation");
        alert.setHeaderText("You were successfully logged in.");

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.out.println("ok clicked");
        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("cancel clicked");
        }
    }

    public void handleOnEnterActionPassword(ActionEvent dragEvent) {
        signIn();
    }

}