package com.codedead.advancedpassgen.domain.controller.password;

import com.codedead.advancedpassgen.domain.interfaces.IPasswordGeneratedEvent;
import com.codedead.advancedpassgen.domain.objects.password.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PasswordController {

    private final PasswordGenerator passwordGenerator;
    private final Logger logger;

    /**
     * Initialize a new PasswordController
     *
     * @param passwordGeneratedEvent The delegate that will be called after a List of Password objects have been generated
     */
    public PasswordController(IPasswordGeneratedEvent passwordGeneratedEvent) {
        passwordGenerator = new PasswordGenerator(passwordGeneratedEvent);
        logger = LoggerFactory.getLogger(PasswordController.class);
    }

    /**
     * Generate a List of Password objects
     *
     * @param characterSet    The character set that can be used to generate passwords
     * @param count           The amount of Password objects that need to be generated
     * @param minLength       The minimum length of a password
     * @param maxLength       The maximum length of a password
     * @param allowDuplicates Sets whether duplicate passwords are allowed or not
     * @param toBase64        Sets whether a password should be converted to its base64 value
     * @param seed            The seed of the password
     */
    public void generatePasswords(final String characterSet, final int count, final int minLength, final int maxLength, final boolean allowDuplicates, final boolean toBase64, final byte[] seed) {
        logger.info("Generating passwords");

        passwordGenerator.setCharacterSet(characterSet);
        passwordGenerator.setCount(count);
        passwordGenerator.setMinLength(minLength);
        passwordGenerator.setMaxLength(maxLength);
        passwordGenerator.setAllowDuplicates(allowDuplicates);
        passwordGenerator.setToBase64(toBase64);

        if (seed != null) {
            passwordGenerator.setSecureRandomSeed(seed);
        }

        final Thread generatorThread = new Thread(passwordGenerator);
        generatorThread.start();
    }
}
