package com.openclassrooms.paymybuddy.util;

import java.text.DecimalFormat;

public class Formatter{

    public String formatDoubleToString(double amountNotFormatted){
        DecimalFormat dFormat = new DecimalFormat("0.00");
        String amountFormatted = dFormat.format(amountNotFormatted);
        return amountFormatted;
    }

    public String addCurrency(String amountWithoutCurrency){
        String currency = " €";
        String amountWithCurrency = amountWithoutCurrency.concat(currency);
        return amountWithCurrency;
    }

}
