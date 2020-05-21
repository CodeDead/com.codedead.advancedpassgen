package com.codedead.advancedpassgen.domain.objects.password;

import java.util.regex.Pattern;

public final class Password {

    private String value;

    /**
     * Initialize a new Password
     */
    public Password() {
        // Default constructor
    }

    /**
     * Initialize a new Password
     *
     * @param value    The value of the Password
     */
    public Password(final String value) {
        setValue(value);
    }

    /**
     * Get the value
     *
     * @return The value
     */
    public final String getValue() {
        return value;
    }

    /**
     * Set the value
     *
     * @param value The value
     */
    public final void setValue(final String value) {
        if (value == null) throw new NullPointerException("Value cannot be null!");

        this.value = value;
    }

    /**
     * Get the strength of the password
     *
     * @return The strength of the password
     */
    public final int getStrength() {
        int score = 1;

        if (getValue() == null || getValue().length() == 0) return 0;
        if (getValue().length() < 2) return 0;
        if (getValue().length() < 4) return 1;
        if (getValue().length() >= 8) score++;
        if (getValue().length() >= 10) score++;
        if (getValue().length() >= 14) score++;
        if (Pattern.matches("\\d", getValue())) score++;
        if (Pattern.matches("[a-z]", getValue()) && Pattern.matches("[A-Z]", getValue())) score++;
        if (Pattern.matches("[:,µ; <>+!@#$%^&*?_~-£()\\[\\]⟨⟩]", getValue())) score++;

        return score;
    }
}
