package vut.cz.bpcbdsproject3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application
{
    private FXMLLoader loader;
    private AnchorPane mainStage;
    public static int userId;
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        try
            {
                loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                mainStage =  loader.load();
                primaryStage.setTitle("PostgreSQL App");
                Scene scene = new Scene(mainStage);
                setUserAgentStylesheet(STYLESHEET_MODENA);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        catch (Exception e)
            {
                e.printStackTrace();
                logger.error("Exception in method start: " + e.getMessage());
                return;
            }
    }
}