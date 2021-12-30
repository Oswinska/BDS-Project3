package vut.cz.bpcbdsproject3.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.App;
import vut.cz.bpcbdsproject3.Postgre.AppBasicView;
import vut.cz.bpcbdsproject3.Postgre.AppDetailedView;
import vut.cz.bpcbdsproject3.data.AppRepository;
import vut.cz.bpcbdsproject3.service.AppService;

import java.io.IOException;

public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @FXML
    private Button registerMovieButton;
    @FXML
    private Button refreshButton;
    @FXML
    private TableView<AppBasicView> movieTable;
    @FXML
    private TableColumn<AppBasicView, Long> filmIDColumn;
    @FXML
    private TableColumn<AppBasicView, String> filmNameColumn;
    @FXML
    private TableColumn<AppBasicView, String> airTimeColumn;
    @FXML
    private TableColumn<AppBasicView, Integer> pegiColumn;
    @FXML
    private Button searchButton;
    @FXML
    private TextField filterTextField;

    private AppService appService;
    private AppRepository appRepository;

    public AppController()
    {
    }

    @FXML
    private void initialize()
    {
        appRepository = new AppRepository();
        appService = new AppService(appRepository);

        filmIDColumn.setCellValueFactory(new PropertyValueFactory<AppBasicView, Long>("id"));
        filmNameColumn.setCellValueFactory(new PropertyValueFactory<AppBasicView, String>("name"));
        airTimeColumn.setCellValueFactory(new PropertyValueFactory<AppBasicView, String>("airTime"));
        pegiColumn.setCellValueFactory(new PropertyValueFactory<AppBasicView, Integer>("pegi"));
        searchButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                handleSearchButton(event);
            }
        });

        ObservableList<AppBasicView> observableList = FXCollections.observableArrayList(appService.getMovieBasicView());
        movieTable.setItems(observableList);

        movieTable.getSortOrder().add(filmIDColumn);
        initializeDetailedViewSelect();
        logger.info("AppController initialized");
    }

    private void initializeDetailedViewSelect()
    {
        MenuItem detailedView = new MenuItem("More Info");
        MenuItem edit = new MenuItem("Edit Movie");
        MenuItem delete = new MenuItem("Delete Movie");

        detailedView.setOnAction((ActionEvent event) ->
        {
            AppBasicView appBasicView = movieTable.getSelectionModel().getSelectedItem();
            try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(App.class.getResource("AppDetailed.fxml"));
                    Stage stage = new Stage();
                    Long film_id = appBasicView.getId();
                    AppDetailedView appDetailedView = appService.getSelectedMovie(film_id);
                    stage.setUserData(appDetailedView);
                    stage.setTitle("Movie Detailed View");
                    AppDetailedController controller = new AppDetailedController();
                    controller.setStage(stage);
                    fxmlLoader.setController(controller);
                    Scene scene = new Scene(fxmlLoader.load(), 600, 500);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                    logger.error("Couldn't open Detailed View");
                }
        });
        edit.setOnAction((ActionEvent event) ->
        {
            AppBasicView appBasicView = movieTable.getSelectionModel().getSelectedItem();
            try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(App.class.getResource("AppEdit.fxml"));
                    Stage stage = new Stage();
                    stage.setUserData(appBasicView);
                    stage.setTitle("Movie Edit");
                    AppEditController controller = new AppEditController();
                    controller.setStage(stage);
                    fxmlLoader.setController(controller);
                    Scene scene = new Scene(fxmlLoader.load(), 600, 500);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                    logger.error("Couldn't open Edit View");
                }
        });


        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(detailedView);
        menu.getItems().addAll(edit);
        movieTable.setContextMenu(menu);
    }

    public void handleRegisterMovieButton(ActionEvent actionEvent)
    {
        try
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("AppCreateMovie.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 500);
                Stage stage = new Stage();
                stage.setTitle("Register Movie");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex)
            {
                ex.printStackTrace();
                logger.error("Couldn't open register movie window");
            }
    }

    public void handleRefreshButton(ActionEvent actionEvent)
    {
        ObservableList<AppBasicView> observableList =
                FXCollections.observableArrayList(appService.getMovieBasicView());
        movieTable.setItems(observableList);
        movieTable.refresh();
        movieTable.sort();
    }

    public void handleSearchButton(ActionEvent actionEvent)
    {
        try
            {
                Integer pegi = Integer.valueOf(filterTextField.getText());
                ObservableList<AppBasicView> observableList =
                        FXCollections.observableArrayList(appService.getFilteredView(pegi));
                movieTable.setItems(observableList);
                movieTable.refresh();
                movieTable.sort();
            } catch (NumberFormatException ex)
            {
                logger.error("Couldn't filter, wrong user input" + ex.getMessage());
            }

    }
}

