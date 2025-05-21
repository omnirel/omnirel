package org.example.naptar5_grafikus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField eventDescription;

    @FXML
    private TextField eventTitle;

    @FXML
    private ListView<Naptar> listView;

    @FXML
    private Spinner<Integer> spinnerHour;

    @FXML
    private Spinner<Integer> spinnerMinute;

    @FXML
    private TextArea textArea;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private ObservableList<Naptar> observableList;

    @FXML
    void deleteEvent(ActionEvent event) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        listView.getItems().remove(selectedIndex);
    }

    @FXML
    void addEvent(ActionEvent event) {
        String title = eventTitle.getText();
        String description = eventDescription.getText();
        LocalDate date = datePicker.getValue();
        LocalTime time = LocalTime.of(spinnerHour.getValue(), spinnerMinute.getValue());

        Naptar newEvent = new Naptar(title, date, time, description);

        listView.getItems().add(newEvent);
        listView.scrollTo(newEvent);
        listView.getSelectionModel().select(newEvent);
    }

    @FXML
    void loadData(ActionEvent event) {
        Path inputPath = Paths.get("Naptar_grafikus.txt");

        List<Naptar> events = new ArrayList<>();

        try{
            List<String> line = Files.readAllLines(inputPath);

            for (String s : line){
                String[] split = s.split(";");

                String title = split[0].trim();
                LocalDate date = LocalDate.parse(split[1].trim(), dateFormatter);
                LocalTime time = LocalTime.parse(split[2].trim(), timeFormatter);
                String description = split[3].trim();

                events.add(new Naptar(title, date, time, description));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        observableList = FXCollections.observableArrayList(events);

        listView.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDisplayedEvents();

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayEventsTextArea();
            }
        });

        datePicker.setValue(LocalDate.now());
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute();

        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, hour, 1);
        spinnerHour.setValueFactory(hourValueFactory);
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, minute, 1);
        spinnerMinute.setValueFactory(minuteValueFactory);
    }

    private void displayEventsTextArea() {
        Naptar selectedItem = listView.getSelectionModel().getSelectedItem();

        String title = selectedItem.title();
        String description = selectedItem.description();
        LocalDate date = selectedItem.date();
        LocalTime time = selectedItem.time();

        textArea.setText(title + " " + date + " " + time + "\n\n" + description);
    }

    private void updateDisplayedEvents() {
        listView.setCellFactory(lv -> new ListCell<Naptar>(){
            @Override
            protected void updateItem(Naptar item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                }else{
                    String displayedText = String.format("%s %s - %s", item.title(), item.date().format(dateFormatter), item.time().format(timeFormatter));
                    setText(displayedText);
                }
            }
        });
    }

}
