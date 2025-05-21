package org.example.eliteltek2_grafikus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class HelloController implements Initializable {

    @FXML
    private CheckBox adultCheckBox;

    @FXML
    private CheckBox allAgesCheckBox;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private ListView<Convicted> listView;

    @FXML
    private Spinner<Integer> spinnerEndYear;

    @FXML
    private Spinner<Integer> spinnerStartYear;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private CheckBox youthCheckBox;

    private ObservableList<Convicted> observableList;

    @FXML
    void loadData(ActionEvent event) {
        Path inputPath = Paths.get("eliteltek.csv");

        List<Convicted> convictedList = new ArrayList<>();

        try{
            List<String> line = Files.readAllLines(inputPath, StandardCharsets.ISO_8859_1);

            for (String s : line) {
                if (s.startsWith("11.1.1.6. Jogerõsen elítéltek") || s.startsWith("Év"))
                    continue;

                String[] split = s.split(";");

                int year = Integer.parseInt(split[0]);
                int youth = Integer.parseInt(split[1].replace(" ", ""));
                int adult = Integer.parseInt(split[2].replace(" ", ""));
                int allAge = Integer.parseInt(split[3].replace(" ", ""));

                convictedList.add(new Convicted(year, youth, adult, allAge));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        observableList = FXCollections.observableArrayList(convictedList);

        listView.setItems(observableList);

        setSpinnerValues(convictedList);

        setLineChartDataWithComboboxAndSpinner(convictedList);

    }

    private void setLineChartDataWithComboboxAndSpinner(List<Convicted> convictedList) {
        lineChart.getData().clear();

        int startYear = spinnerStartYear.getValue();
        int endYear = spinnerEndYear.getValue();

        List<XYChart.Data<String, Number>> youthDataPoints = new ArrayList<>();
        List<XYChart.Data<String, Number>> adultDataPoints = new ArrayList<>();
        List<XYChart.Data<String, Number>> allAgeDataPoints = new ArrayList<>();

        for (Convicted c : convictedList) {
            if (c.year() >= startYear && c.youth() >= endYear){
                youthDataPoints.add(new XYChart.Data<>(String.valueOf(c.year()), c.youth()));
                adultDataPoints.add(new XYChart.Data<>(String.valueOf(c.year()), c.adult()));
                allAgeDataPoints.add(new XYChart.Data<>(String.valueOf(c.year()), c.allAge()));
            }
        }

        Comparator<XYChart.Data<String, Number>> yearComparator = Comparator.comparingInt(data -> Integer.parseInt(data.getXValue()));

        youthDataPoints.sort(yearComparator);
        allAgeDataPoints.sort(yearComparator);
        adultDataPoints.sort(yearComparator);

        XYChart.Series<String, Number> youthSeries = new XYChart.Series<>();
        youthSeries.getData().addAll(youthDataPoints);
        XYChart.Series<String, Number> allAgeSeries = new XYChart.Series<>();
        allAgeSeries.getData().addAll(allAgeDataPoints);
        XYChart.Series<String, Number> adultSeries = new XYChart.Series<>();
        adultSeries.getData().addAll(adultDataPoints);

        if (youthCheckBox.isSelected()){
            lineChart.getData().add(youthSeries);
        }
        if (adultCheckBox.isSelected()){
            lineChart.getData().add(adultSeries);
        }
        if (allAgesCheckBox.isSelected()){
            lineChart.getData().add(allAgeSeries);
        }
    }

    private void setSpinnerValues(List<Convicted> convictedList) {
        Collections.sort(convictedList, Comparator.comparing(Convicted::year));

        int minYear = convictedList.get(0).year();
        int maxYear = convictedList.get(convictedList.size() - 1).year();

        SpinnerValueFactory<Integer> startValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minYear, maxYear, minYear, 1);
        spinnerStartYear.setValueFactory(startValueFactory);
        SpinnerValueFactory<Integer> endValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minYear, maxYear, maxYear, 1);
        spinnerEndYear.setValueFactory(endValueFactory);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        youthCheckBox.setOnAction(event -> updateLineChart(event));
        adultCheckBox.setOnAction(event -> updateLineChart(event));
        allAgesCheckBox.setOnAction(event -> updateLineChart(event));

        youthCheckBox.setSelected(true);
        adultCheckBox.setSelected(true);
        allAgesCheckBox.setSelected(true);

        spinnerStartYear.valueProperty().addListener((observable, oldValue, newValue) -> {updateLineChart(null);});
        spinnerEndYear.valueProperty().addListener((observable, oldValue, newValue) -> {updateLineChart(null);});
    }

    @FXML
    void updateLineChart(ActionEvent event) {
        setLineChartDataWithComboboxAndSpinner(observableList);
    }
}
