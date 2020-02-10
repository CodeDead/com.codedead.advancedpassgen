package com.codedead.advancedpassgen.domain.utils;

import com.codedead.advancedpassgen.domain.interfaces.IRunnableHelper;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class RunnableFileOpener implements Runnable {

    private final File file;
    private final IRunnableHelper iRunnableHelper;

    /**
     * Initialize a new RunnableFileOpener
     *
     * @param file            The File that should be opened
     * @param iRunnableHelper The IRunnableHelper that can be used to delegate messages
     * @throws FileNotFoundException When the file could not be found
     */
    public RunnableFileOpener(final File file, final IRunnableHelper iRunnableHelper) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException(file.getAbsolutePath());

        this.file = file;
        this.iRunnableHelper = iRunnableHelper;
    }

    @Override
    public void run() {
        try {
            Desktop.getDesktop().open(file);
            if (iRunnableHelper != null) {
                iRunnableHelper.executed();
            }
        } catch (Exception ex) {
            if (iRunnableHelper != null) {
                iRunnableHelper.exceptionOccurred(ex);
            }
        }
    }
}
