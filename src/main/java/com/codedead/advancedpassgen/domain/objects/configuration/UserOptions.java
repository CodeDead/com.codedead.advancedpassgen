package com.codedead.advancedpassgen.domain.objects.configuration;

public final class UserOptions {

    private int length;
    private int amount;
    private boolean smallLetters;
    private boolean capitalLetters;
    private boolean specialCharacters;
    private boolean numbers;
    private boolean spaces;
    private boolean brackets;
    private boolean allowDuplicates;
    private boolean advancedOptions;
    private boolean toBase64;
    private int minLength;
    private int maxLength;
    private String characterSet;

    /**
     * Initialize a new UserOptions
     */
    public UserOptions() {
        // Default constructor
    }

    /**
     * Get the length of a password that should be generated
     *
     * @return The length of a password that should be generated
     */
    public final int getLength() {
        return length;
    }

    /**
     * Set the length of a password that should be generated
     *
     * @param length The length of a password that should be generated
     */
    public final void setLength(final int length) {
        if (length < 0) throw new IllegalArgumentException("Length cannot be smaller than zero!");

        this.length = length;
    }

    /**
     * Get the amount of passwords that should be generated
     *
     * @return The amount of passwords that should be generated
     */
    public final int getAmount() {
        return amount;
    }

    /**
     * Set the amount of passwords that should be generated
     *
     * @param amount The amount of passwords that should be generated
     */
    public final void setAmount(final int amount) {
        this.amount = amount;
    }

    /**
     * Get whether small letters should be generated
     *
     * @return True if small letters should be generated, otherwise false
     */
    public final boolean isSmallLetters() {
        return smallLetters;
    }

    /**
     * Set whether small letters should be generated
     *
     * @param smallLetters True if small letters should be generated, otherwise false
     */
    public final void setSmallLetters(final boolean smallLetters) {
        this.smallLetters = smallLetters;
    }

    /**
     * Get whether capital letter should be generated
     *
     * @return True if capital letters should be generated, otherwise false
     */
    public final boolean isCapitalLetters() {
        return capitalLetters;
    }

    /**
     * Set whether capital letters should be generated
     *
     * @param capitalLetters True if capital letters should be generated, otherwise false
     */
    public final void setCapitalLetters(final boolean capitalLetters) {
        this.capitalLetters = capitalLetters;
    }

    /**
     * Get whether special characters should be generated
     *
     * @return True if special characters should be generated, otherwise false
     */
    public final boolean isSpecialCharacters() {
        return specialCharacters;
    }

    /**
     * Set whether special characters should be generated
     *
     * @param specialCharacters True if special characters should be generated,  otherwise false
     */
    public final void setSpecialCharacters(final boolean specialCharacters) {
        this.specialCharacters = specialCharacters;
    }

    /**
     * Get whether numbers should be generated
     *
     * @return True if numbers should be generated, otherwise false
     */
    public final boolean isNumbers() {
        return numbers;
    }

    /**
     * Set whether numbers should be generated
     *
     * @param numbers True if numbers should be generated, otherwise false
     */
    public final void setNumbers(final boolean numbers) {
        this.numbers = numbers;
    }

    /**
     * Get whether spaces should be generated
     *
     * @return True if spaces should be generated, otherwise false
     */
    public final boolean isSpaces() {
        return spaces;
    }

    /**
     * Set whether spaces should be generated
     *
     * @param spaces True if spaces should be generated, otherwise false
     */
    public final void setSpaces(final boolean spaces) {
        this.spaces = spaces;
    }

    /**
     * Get whether brackets should be generated
     *
     * @return True if brackets should be generated, otherwise false
     */
    public final boolean isBrackets() {
        return brackets;
    }

    /**
     * Set whether brackets should be generated or not
     *
     * @param brackets True if brackets should be generated, otherwise false
     */
    public final void setBrackets(final boolean brackets) {
        this.brackets = brackets;
    }

    /**
     * Get whether duplicate entries are allowed
     *
     * @return True if duplicate entries are allowed, otherwise false
     */
    public final boolean isAllowDuplicates() {
        return allowDuplicates;
    }

    /**
     * Set whether duplicate entries are allowed
     *
     * @param allowDuplicates True if duplicate entries are allowed, otherwise false
     */
    public final void setAllowDuplicates(final boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
    }

    /**
     * Get whether the advanced options are used
     *
     * @return True if the advanced options are used, otherwise false
     */
    public final boolean isAdvancedOptions() {
        return advancedOptions;
    }

    /**
     * Set whether advanced options are used
     *
     * @param advancedOptions True if advanced options are used, otherwise false
     */
    public final void setAdvancedOptions(final boolean advancedOptions) {
        this.advancedOptions = advancedOptions;
    }

    /**
     * Get whether a password should be encoded to its base64 value
     *
     * @return True if a password should be encoded to its base64 value, otherwise false
     */
    public final boolean isToBase64() {
        return toBase64;
    }

    /**
     * Set whether a password should be encoded to its base64 value
     *
     * @param toBase64 True if a password should be encoded to its base64 value, otherwise false
     */
    public final void setToBase64(final boolean toBase64) {
        this.toBase64 = toBase64;
    }

    /**
     * Get the minimum length of a password
     *
     * @return The minimum length of a password
     */
    public final int getMinLength() {
        return minLength;
    }

    /**
     * Set the minimum length of a password
     *
     * @param minLength The minimum length of a password
     */
    public final void setMinLength(final int minLength) {
        if (minLength < 0) throw new IllegalArgumentException("Minimum length cannot be smaller than zero!");
        this.minLength = minLength;
    }

    /**
     * Get the maximum length of a password
     *
     * @return The maximum length of a password
     */
    public final int getMaxLength() {
        return maxLength;
    }

    /**
     * Set the maximum length of a password
     *
     * @param maxLength The maximum length of a password
     */
    public final void setMaxLength(final int maxLength) {
        if (maxLength < 0) throw new IllegalArgumentException("Maximum length cannot be smaller than zero!");

        this.maxLength = maxLength;
    }

    /**
     * Get the character set
     *
     * @return The character set
     */
    public final String getCharacterSet() {
        return characterSet;
    }

    /**
     * Set the character set
     *
     * @param characterSet The character set
     */
    public final void setCharacterSet(final String characterSet) {
        this.characterSet = characterSet;
    }
}
