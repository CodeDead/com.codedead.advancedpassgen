package com.codedead.advancedpassgen.domain.utils;

import com.codedead.advancedpassgen.domain.interfaces.IRunnableHelper;

import java.awt.*;
import java.net.URI;

public class RunnableSiteOpener implements Runnable {

    private final String url;
    private final IRunnableHelper iRunnableHelper;

    /**
     * Initialize a new RunnableSiteOpener
     *
     * @param url             The URL that should be opened
     * @param iRunnableHelper The IRunnableHelper that can be used to delegate messages
     */
    public RunnableSiteOpener(final String url, final IRunnableHelper iRunnableHelper) {
        if (url == null) throw new NullPointerException("URL cannot be null!");
        if (url.isEmpty()) throw new IllegalArgumentException("URL cannot be empty!");

        this.url = url;
        this.iRunnableHelper = iRunnableHelper;
    }

    @Override
    public void run() {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            iRunnableHelper.exceptionOccurred(ex);
        }
    }
}
