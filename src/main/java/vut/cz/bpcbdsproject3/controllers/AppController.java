package vut.cz.bpcbdsproject3.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.App;
import vut.cz.bpcbdsproject3.Postgre.AppBasicView;
import vut.cz.bpcbdsproject3.data.AppRepository;
import vut.cz.bpcbdsproject3.service.AppService;

import java.io.IOException;
import java.util.List;

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

    private AppService appService;
    private AppRepository appRepository;

    public AppController() {
    }

    @FXML
    private void initialize() {
        appRepository = new AppRepository();
        appService = new AppService(appRepository);

        filmIDColumn.setCellValueFactory(new PropertyValueFactory<AppBasicView, Long>("id"));
        filmNameColumn.setCellValueFactory(new PropertyValueFactory<AppBasicView, String>("name"));
        airTimeColumn.setCellValueFactory(new PropertyValueFactory<AppBasicView, String>("airtime"));
        pegiColumn.setCellValueFactory(new PropertyValueFactory<AppBasicView, Integer>("pegi"));

        ObservableList<AppBasicView> observableList = FXCollections.observableArrayList(appService.getMovieBasicView());
        movieTable.setItems(observableList);

        movieTable.getSortOrder().add(filmIDColumn);

        logger.info("AppController initialized");
    }


    public void handleRegisterMovieButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("RegisterMovie.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("BDS JavaFX Register Movie");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error("Couldn't open register movie window");
        }
    }

    public void handleRefreshButton(ActionEvent actionEvent) {
        ObservableList<AppBasicView> observablePersonsList =
                FXCollections.observableArrayList(appService.getMovieBasicView());
        movieTable.setItems(observablePersonsList);
        movieTable.refresh();
        movieTable.sort();
    }
}

