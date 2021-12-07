package vut.cz.bpcbdsproject3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    // private PersonRepository personRepository;
    // private AuthService authService;

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

    private void signIn()
    {
        String username = usernameText.getText();
        String password = passwordText.getText();
    }
}