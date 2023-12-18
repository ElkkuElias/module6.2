package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import controller.CurrencyCalculatorController;
import javafx.scene.control.ChoiceBox;
import dao.CurrencyDao;

import java.sql.SQLException;

public class CurrencyCalculatorView extends Application{
    private Label result = new Label("");

    private CurrencyCalculatorController controller;
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
    TextField searchField = new TextField();

    Label label = new Label("Euro");
    Label label2 = new Label("US Dollar");

    Label instructionsbtn = new Label("Press to calculate");
    Label instructions = new Label("Enter amount to convert");

    Button calculate = new Button("Calculate");
    Button newCurrency = new Button("Add new currency");

    Label from = new Label("Select currency to convert from");

    Label to = new Label("Select currency to convert to");


    Label empty = new Label("                                                                        ");

    @Override
    public void start(Stage stage) {
        FlowPane pane = new FlowPane();

        stage.setTitle("Currency Calculator");

        Insets insets = new Insets(10, 10, 10, 10);
        Insets insets2 = new Insets(3, 3, 3, 3);


        stage.setMinWidth(600);
        stage.setMinHeight(600);
        for (int i=1; i<=controller.getCurrenciesSize(); i++){
            choiceBox.getItems().add(controller.getCurrencybyID(i).getAbbreviation());
            choiceBox2.getItems().add(controller.getCurrencybyID(i).getAbbreviation());
        }
        pane.setMargin(choiceBox, insets);
        pane.setMargin(choiceBox2, insets);
        pane.setMargin(calculate, insets);
        pane.setMargin(searchField, insets);
        pane.setMargin(result, insets2);
        pane.setMargin(label, insets2);
        pane.setMargin(instructionsbtn, insets);
        pane.setMargin(instructions, insets);
        pane.setMargin(from, insets);
        pane.setMargin(to, insets);
        pane.setMargin(newCurrency, insets);
        result.setMinWidth(300);
        result.setAlignment(Pos.CENTER);
        searchField.setMinWidth(300);
        searchField.setAlignment(Pos.CENTER);

        pane.getChildren().add(calculate);
        pane.getChildren().add(instructionsbtn);
        pane.getChildren().add(result);
        pane.getChildren().add(searchField);
        pane.getChildren().add(instructions);
        pane.getChildren().add(from);
        pane.getChildren().add(choiceBox);
        pane.getChildren().add(empty);
        pane.getChildren().add(to);
        pane.getChildren().add(choiceBox2);
        pane.getChildren().add(newCurrency);

        Scene scene = new Scene(pane);
        scene.getStylesheets().add("application/sans.css");
        stage.setScene(scene);
        stage.show();

        newCurrency.setOnAction(new EventHandler<ActionEvent>() {
            Stage newStage = new Stage();
            public void handle(ActionEvent event) {
                FlowPane pane = new FlowPane();
                newStage.setTitle("Add new currency");
                newStage.setMinWidth(600);
                newStage.setMinHeight(600);
                TextField currencyName = new TextField();
                TextField currencyAbbreviation = new TextField();
                TextField currencyRate = new TextField();
                Button addCurrency = new Button("Add currency");
                Button close = new Button("Close");
                Label name = new Label("Enter currency name");
                Label abbreviation = new Label("Enter currency abbreviation");
                Label rate = new Label("Enter currency rate");
                Label empty = new Label("                                                                        ");
                pane.setMargin(currencyName, insets);
                pane.setMargin(currencyAbbreviation, insets);
                pane.setMargin(currencyRate, insets);
                pane.setMargin(addCurrency, insets);
                pane.setMargin(close, insets);
                pane.setMargin(name, insets);
                pane.setMargin(abbreviation, insets);
                pane.setMargin(rate, insets);
                pane.setMargin(empty, insets);
                pane.getChildren().add(name);
                pane.getChildren().add(currencyName);
                pane.getChildren().add(abbreviation);
                pane.getChildren().add(currencyAbbreviation);
                pane.getChildren().add(rate);
                pane.getChildren().add(currencyRate);
                pane.getChildren().add(empty);
                pane.getChildren().add(addCurrency);
                pane.getChildren().add(close);
                Scene scene = new Scene(pane);
                scene.getStylesheets().add("application/sans.css");
                newStage.setScene(scene);
                newStage.show();
                addCurrency.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        String name = currencyName.getText();
                        String abbreviation = currencyAbbreviation.getText();
                        String rate = currencyRate.getText();
                        try {
                            controller.addCurrency(controller.makeCurrency(name, abbreviation, rate));
                            choiceBox.getItems().add(abbreviation);
                            choiceBox2.getItems().add(abbreviation);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        newStage.close();
                    }
                });
                close.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        newStage.close();
                    }
                });
            }
        });

        calculate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                    controller.calculate();


            }
        });
        stage.show();
    }
        public void init() {
            controller = new CurrencyCalculatorController(this);
        }
        public String getAmount(){
            return searchField.getText();
        }

        public String getChoiceBox(){
            return choiceBox.getValue();
        }
    public String getChoiceBox2(){
        return choiceBox2.getValue();
    }
        public void setResult(String result) {
            this.result.setText(String.valueOf(result));
        }


}

