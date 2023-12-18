package controller;

import application.CurrencyCalculatorView;
import dao.TransactionDao;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.CurrencyDao;
import entity.transaction;
import entity.currencies;
import jakarta.transaction.Transaction;

public class CurrencyCalculatorController {
    private CurrencyCalculatorView gui;
    private currencies currency;
    private CurrencyDao currencyDao;
    private ArrayList<currencies> currencies2 = new ArrayList<currencies>();

    public CurrencyCalculatorController(CurrencyCalculatorView gui) {
        this.gui = gui;
        this.currencyDao = new CurrencyDao();
    }
    public int getCurrenciesSize() {
        return currencyDao.getNumberOfCurrencies();
    }
    public currencies getCurrencybyID(int id){
        return currencyDao.getCurrencybyID(id);
    }

    public void persistTransaction(transaction transaction) throws SQLException {
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.persist(transaction);
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
                transaction transaction = new transaction(amount,currencyDao.findCurrency(fromCurrencyAbbreviation), currencyDao.findCurrency(toCurrencyAbbreviation));
                persistTransaction(transaction);
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
    public currencies makeCurrency(String name, String abbreviation, String rate) {
        currencies curreny = new currencies(name, abbreviation, Double.parseDouble(rate));
        return curreny;
    }
    public transaction makeTransaction(double amount, currencies fromCurrency, currencies toCurrency){
        transaction transaction = new transaction(amount, fromCurrency, toCurrency);
        return transaction;
    }
    public currencies findCurrencyByAbbrev(String abbreviation) throws SQLException {
        try {
            currencies result = currencyDao.findCurrency(abbreviation);
            return result;
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
    public void addCurrency(currencies currency) throws SQLException {
        currencyDao.persist(currency);
    }

    private double findCurrencyByAbbreviation(String abbreviation) throws SQLException {
        double result = currencyDao.find(abbreviation);
         return result;
    }


    public static void main(String[] args) {
        CurrencyCalculatorView.launch(CurrencyCalculatorView.class);
    }
}
