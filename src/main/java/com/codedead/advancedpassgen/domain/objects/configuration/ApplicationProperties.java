package com.codedead.advancedpassgen.domain.objects.configuration;

public final class ApplicationProperties {

    private ApplicationVersion applicationVersion;
    private boolean autoUpdate;
    private String locale;
    private boolean keepWindowSize;
    private boolean saveOptions;
    private boolean displayPasswordStrength;
    private double mainWindowWidth;
    private double mainWindowHeight;
    private double aboutWindowWidth;
    private double aboutWindowHeight;
    private double settingsWindowWidth;
    private double settingsWindowHeight;
    private String defaultCharacterSet;
    private boolean exportLength;
    private boolean exportStrength;

    /**
     * Initialize a new ApplicationProperties
     */
    public ApplicationProperties() {
        // Default constructor
    }

    /**
     * Get the ApplicationVersion
     *
     * @return The ApplicationVersion
     */
    public final ApplicationVersion getApplicationVersion() {
        return applicationVersion;
    }

    /**
     * Set the ApplicationVersion
     *
     * @param applicationVersion The ApplicationVersion
     */
    public final void setApplicationVersion(final ApplicationVersion applicationVersion) {
        if (applicationVersion == null) throw new NullPointerException("ApplicationVersion cannot be null!");

        this.applicationVersion = applicationVersion;
    }

    /**
     * Get whether automatic updates are enabled
     *
     * @return True if automatic updates are enabled, otherwise false
     */
    public final boolean isAutoUpdate() {
        return autoUpdate;
    }

    /**
     * Set whether automatic updates are enabled
     *
     * @param autoUpdate True if automatic updates are enabled, otherwise false
     */
    public final void setAutoUpdate(final boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    /**
     * Get the locale tag
     *
     * @return The locale tag
     */
    public final String getLocale() {
        return locale;
    }

    /**
     * Set the locale tag
     *
     * @param locale The locale tag
     */
    public final void setLocale(final String locale) {
        if (locale == null) throw new NullPointerException("Locale cannot be null!");
        if (locale.trim().length() == 0) throw new IllegalArgumentException("Locale cannot be empty!");

        this.locale = locale;
    }

    /**
     * Get whether the window size should be retained
     *
     * @return True if the window size should be retained
     */
    public final boolean isKeepWindowSize() {
        return keepWindowSize;
    }

    /**
     * Set whether the window size should be retained
     *
     * @param keepWindowSize True if the window size should be retained, otherwise false
     */
    public final void setKeepWindowSize(final boolean keepWindowSize) {
        this.keepWindowSize = keepWindowSize;
    }

    /**
     * Get whether user options should be retained
     *
     * @return True if user options should be retained, otherwise false
     */
    public final boolean isSaveOptions() {
        return saveOptions;
    }

    /**
     * Set whether user options should be retained
     *
     * @param saveOptions True if user options should be retained, otherwise false
     */
    public final void setSaveOptions(final boolean saveOptions) {
        this.saveOptions = saveOptions;
    }

    /**
     * Get whether the password strength should be displayed
     *
     * @return True if the password strength should be displayed, otherwise false
     */
    public final boolean isDisplayPasswordStrength() {
        return displayPasswordStrength;
    }

    /**
     * Set whether the password strength should be displayed
     *
     * @param displayPasswordStrength True if the password strength should be displayed, otherwise false
     */
    public final void setDisplayPasswordStrength(final boolean displayPasswordStrength) {
        this.displayPasswordStrength = displayPasswordStrength;
    }

    /**
     * Get the width of the main window
     *
     * @return The width of the main window
     */
    public final double getMainWindowWidth() {
        return mainWindowWidth;
    }

    /**
     * Set the width of the main window
     *
     * @param mainWindowWidth The width of the main window
     */
    public final void setMainWindowWidth(final double mainWindowWidth) {
        if (mainWindowWidth < 0) throw new IllegalArgumentException("MainWindow width cannot be smaller than zero!");

        this.mainWindowWidth = mainWindowWidth;
    }

    /**
     * Get the height of the main window
     *
     * @return The height of the main window
     */
    public final double getMainWindowHeight() {
        return mainWindowHeight;
    }

    /**
     * Set the height of the main window
     *
     * @param mainWindowHeight The height of the main window
     */
    public final void setMainWindowHeight(final double mainWindowHeight) {
        if (mainWindowHeight < 0) throw new IllegalArgumentException("MainWindow height cannot be smaller than zero!");

        this.mainWindowHeight = mainWindowHeight;
    }

    /**
     * Get the height of the about window
     *
     * @return The height of the about window
     */
    public final double getAboutWindowWidth() {
        return aboutWindowWidth;
    }

    /**
     * Set the height of the about window
     *
     * @param aboutWindowWidth The height of the about window
     */
    public final void setAboutWindowWidth(final double aboutWindowWidth) {
        if (aboutWindowWidth < 0) throw new IllegalArgumentException("AboutWindow width cannot be smaller than zero!");

        this.aboutWindowWidth = aboutWindowWidth;
    }

    /**
     * Get the height of the about window
     *
     * @return The height of the about window
     */
    public double getAboutWindowHeight() {
        return aboutWindowHeight;
    }

    /**
     * Set the height of the about window
     *
     * @param aboutWindowHeight The height of the about window
     */
    public final void setAboutWindowHeight(final double aboutWindowHeight) {
        if (aboutWindowHeight < 0)
            throw new IllegalArgumentException("AboutWindow height cannot be smaller than zero!");

        this.aboutWindowHeight = aboutWindowHeight;
    }

    /**
     * Get the width of the settings window
     *
     * @return The width of the settings window
     */
    public final double getSettingsWindowWidth() {
        return settingsWindowWidth;
    }

    /**
     * Set the width of the settings window
     *
     * @param settingsWindowWidth The width of the settings window
     */
    public final void setSettingsWindowWidth(final double settingsWindowWidth) {
        if (settingsWindowWidth < 0)
            throw new IllegalArgumentException("SettingsWindow width cannot be smaller than zero!");

        this.settingsWindowWidth = settingsWindowWidth;
    }

    /**
     * Get the height of the settings window
     *
     * @return The height of the settings window
     */
    public final double getSettingsWindowHeight() {
        return settingsWindowHeight;
    }

    /**
     * Set the height of the settings window
     *
     * @param settingsWindowHeight The height of the settings window
     */
    public final void setSettingsWindowHeight(final double settingsWindowHeight) {
        if (settingsWindowHeight < 0)
            throw new IllegalArgumentException("SettingsWindow height cannot be smaller than zero!");

        this.settingsWindowHeight = settingsWindowHeight;
    }

    /**
     * Get the default character set
     *
     * @return The default character set
     */
    public final String getDefaultCharacterSet() {
        return defaultCharacterSet;
    }

    /**
     * Set the default character set
     *
     * @param defaultCharacterSet The default character set
     */
    public final void setDefaultCharacterSet(final String defaultCharacterSet) {
        this.defaultCharacterSet = defaultCharacterSet;
    }

    /**
     * Get whether the length field should be exported
     *
     * @return True if the length field should be exported, otherwise false
     */
    public boolean isExportLength() {
        return exportLength;
    }

    /**
     * Set whether the length field should be exported
     *
     * @param exportLength True if the length field should be exported, otherwise false
     */
    public void setExportLength(final boolean exportLength) {
        this.exportLength = exportLength;
    }

    /**
     * Get whether the strength field should be exported
     *
     * @return True if the strength field should be exported, otherwise false
     */
    public final boolean isExportStrength() {
        return exportStrength;
    }

    /**
     * Set whether the strength field should be exported
     *
     * @param exportStrength True if the strength field should be exported, otherwise false
     */
    public final void setExportStrength(final boolean exportStrength) {
        this.exportStrength = exportStrength;
    }

    /**
     * Get the language index from the current locale
     *
     * @return The language index from the current locale
     */
    public final int getLanguageIndex() {
        if (getLocale() == null) throw new NullPointerException("Locale cannot be null!");

        return switch (getLocale()) {
            case "nl-BE" -> 0;
            case "fr-FR" -> 2;
            default -> 1;
        };
    }
}
