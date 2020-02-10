package com.codedead.advancedpassgen.domain.controls;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public final class NumberTextField extends TextField {

    private long minValue = Long.MIN_VALUE;
    private long maxValue = Long.MAX_VALUE;

    /**
     * Initialize a new NumberTextField
     */
    public NumberTextField() {
        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                long currentValue = getValue();
                currentValue++;
                if (!validateNumber(currentValue)) {
                    currentValue = maxValue;
                }
                this.setText(Long.toString(currentValue));
            } else if (e.getCode() == KeyCode.DOWN) {
                long currentValue = getValue();
                currentValue--;
                if (!validateNumber(currentValue)) {
                    currentValue = minValue;
                }
                this.setText(Long.toString(currentValue));
            }
        });
    }

    @Override
    public final void replaceText(final int start, final int end, final String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public final void replaceSelection(final String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    /**
     * Validate whether the text is a number or not
     *
     * @param text The text that should be validated
     * @return True if the text is a number, otherwise false
     */
    private boolean validate(final String text) {
        if (text == null) return false;
        if (text.length() == 0) return true;
        if (!text.matches("[0-9]*")) return false;
        if (getText().length() == 0) return true;

        try {
            return validateNumber(Long.parseLong(getText()));
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Validate if a number validates against the minimum and maximum values
     *
     * @param number The number that should be validated
     * @return True if the number is valid, otherwise false
     */
    private boolean validateNumber(final long number) {
        if (number < minValue) return false;
        return number <= maxValue;
    }

    /**
     * Get the minimum value
     *
     * @return The minimum value
     */
    public final long getMinValue() {
        return minValue;
    }

    /**
     * Set the minimum value
     *
     * @param minValue The minimum value
     */
    public final void setMinValue(long minValue) {
        this.minValue = minValue;
    }

    /**
     * Get the maximum value
     *
     * @return The maximum value
     */
    public final long getMaxValue() {
        return maxValue;
    }

    /**
     * Set the maximum value
     *
     * @param maxValue The maximum value
     */
    public final void setMaxValue(final long maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Get the current value
     * @return The current value
     */
    public final long getValue() {
        try {
            return Long.parseLong(this.getText());
        } catch (NumberFormatException ex) {
            if (minValue == Long.MIN_VALUE) return 0;
            return minValue;
        }
    }
}
