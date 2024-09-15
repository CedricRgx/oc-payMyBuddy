package com.openclassrooms.paymybuddy.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * The Formatter class provides methods to format numeric values into standardized string representations.
 * This includes methods for formatting double values into a currency string format.
 */
public class Formatter {

    /**
     * Formats a double value to a string with two decimal places (uses the US locale to ensure the decimal separator is a dot)
     *
     * @param amountNotFormatted the double value to be formatted.
     * @return a string representation of the double value formatted to two decimal places.
     */
    public String formatDoubleToString(double amountNotFormatted) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat dFormat = new DecimalFormat("0.00", symbols);
        String amountFormatted = dFormat.format(amountNotFormatted);
        return amountFormatted;
    }

    /**
     * Appends the Euro currency symbol to a given string representing an amount.
     *
     * @param amountWithoutCurrency the string representing the numeric amount to which the currency will be added.
     * @return the amount string concatenated with the Euro currency symbol.
     */
    public String addCurrency(String amountWithoutCurrency){
        String currency = " €";
        String amountWithCurrency = amountWithoutCurrency.concat(currency);
        return amountWithCurrency;
    }

}
