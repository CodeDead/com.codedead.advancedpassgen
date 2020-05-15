package com.codedead.advancedpassgen.domain.objects;

public final class ApplicationVersion {

    private int majorVersion;
    private int minorVersion;
    private int buildVersion;
    private int revisionVersion;

    /**
     * Initialize a new ApplicationVersion
     */
    public ApplicationVersion() {
        // Default constructor
    }

    /**
     * Initialize a new ApplicationVersion
     *
     * @param majorVersion The major version of the application
     */
    public ApplicationVersion(final int majorVersion) {
        setMajorVersion(majorVersion);
    }

    /**
     * Initialize a new ApplicationVersion
     *
     * @param majorVersion The major version of the application
     * @param minorVersion The minor version of the application
     */
    public ApplicationVersion(final int majorVersion, final int minorVersion) {
        setMajorVersion(majorVersion);
        setMinorVersion(minorVersion);
    }

    /**
     * Initialize a new ApplicationVersion
     *
     * @param majorVersion The major version of the application
     * @param minorVersion The minor version of the application
     * @param buildVersion The build version of the application
     */
    public ApplicationVersion(final int majorVersion, final int minorVersion, final int buildVersion) {
        setMajorVersion(majorVersion);
        setMinorVersion(minorVersion);
        setBuildVersion(buildVersion);
    }

    /**
     * Initialize a new ApplicationVersion
     *
     * @param majorVersion    The major version of the application
     * @param minorVersion    The minor version of the application
     * @param buildVersion    The build version of the application
     * @param revisionVersion The revision version of the application
     */
    public ApplicationVersion(final int majorVersion, final int minorVersion, final int buildVersion, final int revisionVersion) {
        setMajorVersion(majorVersion);
        setMinorVersion(minorVersion);
        setBuildVersion(buildVersion);
        setRevisionVersion(revisionVersion);
    }

    /**
     * Get the major version
     *
     * @return The major version
     */
    public final int getMajorVersion() {
        return majorVersion;
    }

    /**
     * Set the major version
     *
     * @param majorVersion The major version
     */
    public final void setMajorVersion(final int majorVersion) {
        if (majorVersion < 0) throw new IllegalArgumentException("Major version cannot be smaller than zero!");
        this.majorVersion = majorVersion;
    }

    /**
     * Get the minor version
     *
     * @return The minor version
     */
    public final int getMinorVersion() {
        return minorVersion;
    }

    /**
     * Set the minor version
     *
     * @param minorVersion The minor version
     */
    public final void setMinorVersion(final int minorVersion) {
        if (minorVersion < 0) throw new IllegalArgumentException("Minor version cannot be smaller than zero!");

        this.minorVersion = minorVersion;
    }

    /**
     * Get the build version
     *
     * @return The build version
     */
    public final int getBuildVersion() {
        return buildVersion;
    }

    /**
     * Set the build version
     *
     * @param buildVersion The build version
     */
    public final void setBuildVersion(final int buildVersion) {
        if (buildVersion < 0) throw new IllegalArgumentException("Build version cannot be smaller than zero!");

        this.buildVersion = buildVersion;
    }

    /**
     * Get the revision version
     *
     * @return The revision version
     */
    public final int getRevisionVersion() {
        return revisionVersion;
    }

    /**
     * Set the revision version
     *
     * @param revisionVersion The revision version
     */
    public final void setRevisionVersion(final int revisionVersion) {
        if (revisionVersion < 0) throw new IllegalArgumentException("Revision version cannot be smaller than zero!");

        this.revisionVersion = revisionVersion;
    }

    @Override
    public String toString() {
        return majorVersion + "." + minorVersion + "." + buildVersion + "." + revisionVersion;
    }
}
