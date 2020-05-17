package com.codedead.advancedpassgen.domain.objects.password;

public final class Password {

    private String value;
    private int strength;

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
     * @param strength The strength of the Password
     */
    public Password(final String value, final int strength) {
        setValue(value);
        setStrength(strength);
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
     * Get the strength
     *
     * @return The strength
     */
    public final int getStrength() {
        return strength;
    }

    /**
     * Set the strength
     *
     * @param strength The strength
     */
    public final void setStrength(final int strength) {
        if (strength < 0) throw new IllegalArgumentException("Strength cannot be smaller than zero!");
        if (strength > 10) throw new IllegalArgumentException("Strength cannot be larger than 10!");

        this.strength = strength;
    }
}
