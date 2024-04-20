package com.openclassrooms.paymybuddy.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Formatter{

    public String formatDoubleToString(double amountNotFormatted) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat dFormat = new DecimalFormat("0.00", symbols);
        String amountFormatted = dFormat.format(amountNotFormatted);
        return amountFormatted;
    }

    public String addCurrency(String amountWithoutCurrency){
        String currency = " €";
        String amountWithCurrency = amountWithoutCurrency.concat(currency);
        return amountWithCurrency;
    }

}
