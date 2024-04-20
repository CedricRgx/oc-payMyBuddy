package com.openclassrooms.paymybuddy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormatterTest {
    private Formatter formatter;

    @BeforeEach
    void setUp() {
        formatter = new Formatter();
    }

    @Test
    void formatDoubleToString_ReturnsCorrectlyFormattedString() {
        // Arrange
        double input = 1234.5678;

        // Act
        String formatted = formatter.formatDoubleToString(input);

        // Assert
        assertEquals("1234.57", formatted, "The double should be formatted to two decimal places.");
    }

    @Test
    void addCurrency_AppendsCurrencySymbol() {
        // Arrange
        String amount = "1234.57";

        // Act
        String result = formatter.addCurrency(amount);

        // Assert
        assertEquals("1234.57 €", result, "The currency symbol should be appended to the amount.");
    }
}
