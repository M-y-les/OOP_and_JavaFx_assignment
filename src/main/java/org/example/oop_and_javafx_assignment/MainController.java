package org.example.oop_and_javafx_assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class MainController implements Initializable {

    @FXML private MenuBar menuBar;
    @FXML private ImageView profileImageView;
    @FXML private Label     profilePlaceholder;
    @FXML private TableView<Profile>            studentTable;
    @FXML private TableColumn<Profile, Integer> colId;
    @FXML private TableColumn<Profile, String>  colFirstName;
    @FXML private TableColumn<Profile, String>  colLastName;
    @FXML private TableColumn<Profile, String>  colDepartment;
    @FXML private TableColumn<Profile, String>  colMajor;
    @FXML private TableColumn<Profile, String>  colEmail;
    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private TextField tfDepartment;
    @FXML private TextField tfMajor;
    @FXML private TextField tfEmail;
    @FXML private TextField tfImageURL;
    @FXML private Button btnClear;
    @FXML private Button btnAdd;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;
    private final ObservableList<Profile> data      = FXCollections.observableArrayList();
    private final AtomicInteger           idCounter = new AtomicInteger(1);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getId()));
        colFirstName.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getFirstName()));
        colLastName.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getLastName()));
        colDepartment.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getDepartment()));
        colMajor.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getMajor()));
        colEmail.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));

        studentTable.setItems(data);
        studentTable.setPlaceholder(new Label("No content in table"));

        // Clicking a row populates the form
        studentTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, sel) -> {
                    if (sel == null) return;
                    tfFirstName.setText(sel.getFirstName());
                    tfLastName.setText(sel.getLastName());
                    tfDepartment.setText(sel.getDepartment());
                    tfMajor.setText(sel.getMajor());
                    tfEmail.setText(sel.getEmail());
                    tfImageURL.setText(sel.getImageURL());
                    refreshProfileImage(sel.getImageURL());
                });

        // Live image preview while typing the URL
        tfImageURL.textProperty().addListener(
                (obs, old, newUrl) -> refreshProfileImage(newUrl));
    }

    private void refreshProfileImage(String url) {
        if (url == null || url.isBlank()) {
            profileImageView.setImage(null);
            profilePlaceholder.setVisible(true);
            return;
        }
        try {
            profileImageView.setImage(new Image(url, true));
            profilePlaceholder.setVisible(false);
        } catch (Exception ex) {
            profileImageView.setImage(null);
            profilePlaceholder.setVisible(true);
        }
    }

    private void clearForm() {
        tfFirstName.clear();
        tfLastName.clear();
        tfDepartment.clear();
        tfMajor.clear();
        tfEmail.clear();
        tfImageURL.clear();
        profileImageView.setImage(null);
        profilePlaceholder.setVisible(true);
        studentTable.getSelectionModel().clearSelection();
    }

    private void alert(Alert.AlertType type, String title, String msg) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    /* ── Button handlers ── */

    @FXML
    private void handleClear() {
        clearForm();
    }

    @FXML
    private void handleAdd() {
        String fn = tfFirstName.getText().trim();
        String ln = tfLastName.getText().trim();
        if (fn.isEmpty() || ln.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Validation", "First Name and Last Name are required.");
            return;
        }
        data.add(new Profile(
                idCounter.getAndIncrement(),
                fn, ln,
                tfDepartment.getText().trim(),
                tfMajor.getText().trim(),
                tfEmail.getText().trim(),
                tfImageURL.getText().trim()
        ));
        clearForm();
    }

    @FXML
    private void handleDelete() {
        Profile p = studentTable.getSelectionModel().getSelectedItem();
        if (p == null) {
            alert(Alert.AlertType.WARNING, "No Selection", "Select a profile to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete " + p.getFirstName() + " " + p.getLastName() + "?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirm Delete");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            data.remove(p);
            clearForm();
        }
    }

    @FXML
    private void handleEdit() {
        Profile p = studentTable.getSelectionModel().getSelectedItem();
        if (p == null) {
            alert(Alert.AlertType.WARNING, "No Selection", "Select a profile to edit.");
            return;
        }
        String fn = tfFirstName.getText().trim();
        String ln = tfLastName.getText().trim();
        if (fn.isEmpty() || ln.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Validation", "First Name and Last Name are required.");
            return;
        }
        p.setFirstName(fn);
        p.setLastName(ln);
        p.setDepartment(tfDepartment.getText().trim());
        p.setMajor(tfMajor.getText().trim());
        p.setEmail(tfEmail.getText().trim());
        p.setImageURL(tfImageURL.getText().trim());
        studentTable.refresh();
        clearForm();
    }



    @FXML
    private void handleNew() {
        data.clear();
        idCounter.set(1);
        clearForm();
    }

    @FXML
    private void handleOpen() {
        alert(Alert.AlertType.INFORMATION, "Open", "Open – coming soon.");
    }

    @FXML
    private void handleSave() {
        alert(Alert.AlertType.INFORMATION, "Save", "Save – coming soon.");
    }

    @FXML
    private void handleExit() {
        ((Stage) menuBar.getScene().getWindow()).close();
    }

    @FXML
    private void handleCut() {
        alert(Alert.AlertType.INFORMATION, "Cut", "Cut – coming soon.");
    }

    @FXML
    private void handleCopy() {
        alert(Alert.AlertType.INFORMATION, "Copy", "Copy – coming soon.");
    }

    @FXML
    private void handlePaste() {
        alert(Alert.AlertType.INFORMATION, "Paste", "Paste – coming soon.");
    }

    @FXML
    private void handleDefaultTheme() {
        // single built-in theme, nothing to switch
    }

    @FXML
    private void handleAbout() {
        alert(Alert.AlertType.INFORMATION, "About",
                "FSC CSC325 – Full Stack Project\nJavaFX Profile Management System");
    }
}
