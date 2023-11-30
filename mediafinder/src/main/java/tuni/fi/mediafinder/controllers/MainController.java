package tuni.fi.mediafinder.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.input.MouseButton;
import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.utility.MediaUtility;
import tuni.fi.mediafinder.utility.Utility;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;

public class MainController {

    @FXML
    private Pane searchPane;

    @FXML
    private StackPane singleMediaPane;

    @FXML
    private TextField mediaSearchField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Media> tableView;

    @FXML
    private TableColumn<Media, String> titleColumn;

    @FXML
    private TableColumn<Media, String> releaseDateColumn;

    @FXML
    private TableColumn<Media, String> ratingColumn;

    @FXML
    private TableColumn<Media, String> mediaTypeColumn;

    @FXML
    private CheckBox movieCheck;

    @FXML
    private CheckBox bookCheck;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private AnchorPane graphAnchorPane;

    @FXML
    private AnchorPane ratingsTab;

    @FXML
    private AnchorPane yearsTab;

    @FXML
    private TabPane graphTabs;

    @FXML
    public void search() throws IOException {

        List<Media> mediaList = MediaUtility.getMediasByQuery(mediaSearchField.getText(), movieCheck.isSelected(),
                bookCheck.isSelected(), startDate.getValue(), endDate.getValue());

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        mediaTypeColumn.setCellValueFactory(new PropertyValueFactory<>("mediaType"));

        ObservableList<Media> mediaData = FXCollections.observableArrayList(mediaList);
        tableView.setItems(mediaData);

    }

    public void initialize() throws IOException {
        singleMediaPane.setVisible(false);
        graphAnchorPane.setVisible(false);
        bookCheck.setSelected(true);
        movieCheck.setSelected(true);
        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1
                    && !(event.getTarget() instanceof TableColumnHeader)) {
                Media selectedMedia = tableView.getSelectionModel().getSelectedItem();
                if (selectedMedia != null) {
                    searchPane.setVisible(false);
                    singleMediaPane.setVisible(true);
                    SingleMediaController.showSingleMediaItem(selectedMedia, singleMediaPane, searchPane);
                }
            }
        });
        singleMediaPane.setPrefHeight(650);
        singleMediaPane.setPrefWidth(980);
        graphAnchorPane.setPrefHeight(650);
        graphAnchorPane.setPrefWidth(980);
        graphTabs.setPrefHeight(650);
        graphTabs.setPrefWidth(980);


    }

    @FXML
    public void checkBoxClickHandler() throws IOException {
        search();
    }

    @FXML
    public void onDateSelect() throws IOException {
        search();
    }

    @FXML
    public void onDatePickerKeyPressed() throws IOException {
        startDate.setValue(null);
        endDate.setValue(null);
    }

    @FXML
    public void switchToMainView() throws IOException {
        searchPane.setVisible(true);
        singleMediaPane.setVisible(false);
        graphAnchorPane.setVisible(false);
    }

    @FXML
    public void summary() throws IOException {
        searchPane.setVisible(false);
        singleMediaPane.setVisible(false);
        graphAnchorPane.setVisible(true);
        // The query is not saved anywhere so getting text from search input for now
        // Could use media data from tableView maybe?
        Map<Utility.MediaType, Map<String, Long>> releaseYearData = MediaUtility
                .getMediaByReleaseYear(mediaSearchField.getText());
        Map<Utility.MediaType, Map<String, Long>> ratingData = MediaUtility
                .getMediaByRatings(mediaSearchField.getText());
        GraphViewController.plotGraphs(releaseYearData, ratingData, ratingsTab, yearsTab);
    }
}
