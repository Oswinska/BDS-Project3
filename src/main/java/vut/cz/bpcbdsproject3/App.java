package vut.cz.bpcbdsproject3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application
{
    private FXMLLoader loader;
    private VBox mainStage;

    public static void main(String[] args)
    {
        launch(args);
    }

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
            } catch (Exception e)
            {
                System.out.print(System.out.format("Exception %s thrown",e));
            }
    }
}