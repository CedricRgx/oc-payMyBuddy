package com.openclassrooms.paymybuddy.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FormatterTest {

    private Formatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new Formatter();
    }

    @Test
    public void testFormatDoubleToString_ReturnsCorrectlyFormattedString() {
        // Given
        double input = 1234.5678;

        // When
        String formatted = formatter.formatDoubleToString(input);

        // Then
        assertEquals("1234.57", formatted);
    }

    @Test
    public void testAddCurrency_AppendsCurrencySymbol() {
        // Given
        String amount = "1234.57";

        // When
        String result = formatter.addCurrency(amount);

        // Then
        assertEquals("1234.57 €", result);
    }
}
