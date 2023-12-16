package controller;

import application.CurrencyCalculatorView;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.CurrencyDao;
import entity.currencies;

public class CurrencyCalculatorController {
    private CurrencyCalculatorView gui;
    private currencies currency;
    private CurrencyDao currencyDao;
    private ArrayList<currencies> currencies2 = new ArrayList<currencies>();

    public CurrencyCalculatorController(CurrencyCalculatorView gui) {
        this.gui = gui;
        this.currencyDao = new CurrencyDao();
    }

    public void calculate()  {
        String amountStr = gui.getAmount();
        String fromCurrencyAbbreviation = gui.getChoiceBox();
        String toCurrencyAbbreviation = gui.getChoiceBox2();

        try {
            double amount = Double.parseDouble(amountStr);

            double fromCurrency = findCurrencyByAbbreviation(fromCurrencyAbbreviation);
            if (fromCurrency < 0) {
                gui.setResult("Database error");
                return;
            }

            double toCurrency = findCurrencyByAbbreviation(toCurrencyAbbreviation);

            if (fromCurrency != 0 && toCurrency != 0) {
                double amountInBase = amount / fromCurrency;
                double convertedAmount = amountInBase * toCurrency;
                String result = String.format("%.2f", convertedAmount);
                gui.setResult(result);
            } else {
                gui.setResult("Currency not found");
            }
        } catch (NumberFormatException e) {
            gui.setResult("Invalid amount");
        }
        catch (SQLException e) {
            gui.setResult("Database error");
        }

    }

    private double findCurrencyByAbbreviation(String abbreviation) throws SQLException {
        double result = currencyDao.find(abbreviation);
         return result;
    }
    public static void main(String[] args) {
        CurrencyCalculatorView.launch(CurrencyCalculatorView.class);
    }
}
