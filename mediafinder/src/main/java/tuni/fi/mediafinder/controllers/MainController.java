package tuni.fi.mediafinder.controllers;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.input.MouseButton;
import tuni.fi.mediafinder.App;
import tuni.fi.mediafinder.models.Media;
import tuni.fi.mediafinder.utility.MediaUtility;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

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
    public void search() throws IOException {

        List<Media> mediaList = MediaUtility.getMediasByQuery(mediaSearchField.getText(), 1, 10);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        mediaTypeColumn.setCellValueFactory(new PropertyValueFactory<>("mediaType"));

        ObservableList<Media> mediaData = FXCollections.observableArrayList(mediaList);
        tableView.setItems(mediaData);

    }

    public void initialize() throws IOException {
        singleMediaPane.setVisible(false);
        tableView.setOnMouseClicked(event -> {

            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1 &&  !(event.getTarget() instanceof TableColumnHeader)) {
                Media selectedMedia = tableView.getSelectionModel().getSelectedItem();
                System.out.println(event.getPickResult());
                if (selectedMedia != null) {
                    searchPane.setVisible(false);
                    singleMediaPane.setVisible(true);
                    SingleMediaController.showSingleMediaItem(selectedMedia, singleMediaPane, searchPane);
                }
            }
        });
    }
}
