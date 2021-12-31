package vut.cz.bpcbdsproject3.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vut.cz.bpcbdsproject3.Postgre.InjectionView;
import vut.cz.bpcbdsproject3.data.AppRepository;
import vut.cz.bpcbdsproject3.service.AppService;

import java.util.List;

public class InjectionController
{
    private static final Logger logger = LoggerFactory.getLogger(InjectionController.class);

    @FXML
    private Button customButton;
    @FXML
    private TextField injectionTextField;
    @FXML
    private TableView<InjectionView> injectionTable;
    @FXML
    private TableColumn<InjectionView, Long> idColumn;
    @FXML
    private TableColumn<InjectionView, String> firstNameColumn;
    @FXML
    private TableColumn<InjectionView, String> lastNameColumn;
    @FXML
    private TableColumn<InjectionView, String> nickNameColumn;
    @FXML
    private TableColumn<InjectionView, String> emailColumn;

    private AppService appService;
    private AppRepository appRepository;
    private Stage stage;

    public InjectionController()
    {

    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    @FXML
    private void initialize()
    {
        appRepository = new AppRepository();
        appService = new AppService(appRepository);

        idColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, Long>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("lastName"));
        nickNameColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("nickName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("email"));

        logger.info("InjectionController initalized");
    }

    private ObservableList<InjectionView> initData()
    {
        List<InjectionView> person = appService.getInjectionView(injectionTextField.getText());
        return FXCollections.observableArrayList(person);
    }

    public void handleCustomButton()
    {
        ObservableList<InjectionView> observableList = initData();
        injectionTable.setItems(observableList);
    }
}
