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
        choiceBox.getItems().add("EUR");
        choiceBox.getItems().add("USD");
        choiceBox.getItems().add("GBP");
        choiceBox.getItems().add("MAD");
        choiceBox.getItems().add("JPY");
        choiceBox.getItems().add("CNY");
        choiceBox.getItems().add("INR");
        choiceBox.getItems().add("RUB");
        choiceBox2.getItems().add("EUR");
        choiceBox2.getItems().add("USD");
        choiceBox2.getItems().add("GBP");
        choiceBox2.getItems().add("MAD");
        choiceBox2.getItems().add("JPY");
        choiceBox2.getItems().add("CNY");
        choiceBox2.getItems().add("INR");
        choiceBox2.getItems().add("RUB");
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

        Scene scene = new Scene(pane);
        scene.getStylesheets().add("application/sans.css");
        stage.setScene(scene);
        stage.show();

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

