package com.codedead.advancedpassgen.domain.controller;

import com.codedead.advancedpassgen.domain.interfaces.IPasswordGeneratedEvent;
import com.codedead.advancedpassgen.domain.objects.password.PasswordGenerator;

public final class PasswordController {

    private final PasswordGenerator passwordGenerator;

    /**
     * Initialize a new PasswordController
     *
     * @param passwordGeneratedEvent The delegate that will be called after a List of Password objects have been generated
     */
    public PasswordController(IPasswordGeneratedEvent passwordGeneratedEvent) {
        passwordGenerator = new PasswordGenerator(passwordGeneratedEvent);
    }

    /**
     * Generate a List of Password objects
     *
     * @param characterSet    The character set that can be used to generate passwords
     * @param count           The amount of Password objects that need to be generated
     * @param minLength       The minimum length of a password
     * @param maxLength       The maximum length of a password
     * @param allowDuplicates Sets whether duplicate passwords are allowed or not
     * @param seed            The seed of the password
     */
    public void generatePasswords(final String characterSet, final int count, final int minLength, final int maxLength, final boolean allowDuplicates, final byte[] seed) {
        passwordGenerator.setCharacterSet(characterSet);
        passwordGenerator.setCount(count);
        passwordGenerator.setMinLength(minLength);
        passwordGenerator.setMaxLength(maxLength);
        passwordGenerator.setAllowDuplicates(allowDuplicates);

        if (seed != null) {
            passwordGenerator.setSecureRandomSeed(seed);
        }

        final Thread generatorThread = new Thread(passwordGenerator);
        generatorThread.start();
    }
}
