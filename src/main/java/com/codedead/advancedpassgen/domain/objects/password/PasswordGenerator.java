package com.codedead.advancedpassgen.domain.objects.password;

import com.codedead.advancedpassgen.domain.interfaces.IPasswordGeneratedEvent;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class PasswordGenerator implements Runnable {

    private String characterSet;
    private List<String> wordList;
    private int count;
    private int minLength;
    private int maxLength;
    private boolean allowDuplicates;
    private boolean toBase64;

    private final SecureRandom secureRandom;
    private final IPasswordGeneratedEvent passwordGeneratedEvent;

    /**
     * Initialize a new PasswordGenerator
     *
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final IPasswordGeneratedEvent passwordGeneratedEvent) {
        setCharacterSet("");

        secureRandom = new SecureRandom();
        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param seed                   The seed for the SecureRandom object
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final byte[] seed, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        secureRandom = new SecureRandom();

        setCharacterSet("");
        setSecureRandomSeed(seed);

        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param characterSet           The character set that can be used to generate passwords
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final String characterSet, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        setCharacterSet(characterSet);

        secureRandom = new SecureRandom();
        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param characterSet           The character set that can be used to generate passwords
     * @param wordList               The List of String objects that represent words
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final String characterSet, final List<String> wordList, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        setCharacterSet(characterSet);
        setWordList(wordList);

        secureRandom = new SecureRandom();
        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param characterSet           The character set that can be used to generate passwords
     * @param length                 The length of a password that should be generated
     * @param count                  The amount of passwords that need to be generated
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final String characterSet, final int length, final int count, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        setCharacterSet(characterSet);
        setMinLength(length);
        setMaxLength(length);
        setCount(count);

        secureRandom = new SecureRandom();
        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param characterSet           The character set that can be used to generate passwords
     * @param wordList               The List of String objects that represent words
     * @param length                 The length of a password that should be generated
     * @param count                  The amount of passwords that need to be generated
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final String characterSet, final List<String> wordList, final int length, final int count, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        setCharacterSet(characterSet);
        setWordList(wordList);
        setMinLength(length);
        setMaxLength(length);
        setCount(count);

        secureRandom = new SecureRandom();
        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param characterSet           The character set that can be used to generate passwords
     * @param count                  The amount of passwords that need to be generated
     * @param minLength              The minimum length of a password that should be generated
     * @param maxLength              The maximum length of a password that should be generated
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final String characterSet, final int count, final int minLength, final int maxLength, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        setCharacterSet(characterSet);
        setCount(count);
        setMinLength(minLength);
        setMaxLength(maxLength);

        secureRandom = new SecureRandom();
        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param characterSet           The character set that can be used to generate passwords
     * @param wordList               The List of String objects that represent words
     * @param count                  The amount of passwords that need to be generated
     * @param minLength              The minimum length of a password that should be generated
     * @param maxLength              The maximum length of a password that should be generated
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final String characterSet, final List<String> wordList, final int count, final int minLength, final int maxLength, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        setCharacterSet(characterSet);
        setWordList(wordList);
        setCount(count);
        setMinLength(minLength);
        setMaxLength(maxLength);

        secureRandom = new SecureRandom();
        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param characterSet           The character set that can be used to generate passwords
     * @param count                  The amount of passwords that need to be generated
     * @param minLength              The minimum length of a password that should be generated
     * @param maxLength              The maximum length of a password that should be generated
     * @param seed                   The seed for the SecureRandom object
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final String characterSet, final int count, final int minLength, final int maxLength, final byte[] seed, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        secureRandom = new SecureRandom();

        setCharacterSet(characterSet);
        setCount(count);
        setMinLength(minLength);
        setMaxLength(maxLength);
        setSecureRandomSeed(seed);

        this.passwordGeneratedEvent = passwordGeneratedEvent;
    }

    /**
     * Initialize a new PasswordGenerator
     *
     * @param characterSet           The character set that can be used to generate passwords
     * @param wordList               The List of String objects that represent words
     * @param count                  The amount of passwords that need to be generated
     * @param minLength              The minimum length of a password that should be generated
     * @param maxLength              The maximum length of a password that should be generated
     * @param seed                   The seed for the SecureRandom object
     * @param passwordGeneratedEvent The delegate that will be called after the List of Password objects have been generated
     */
    public PasswordGenerator(final String characterSet, final List<String> wordList, final int count, final int minLength, final int maxLength, final byte[] seed, final IPasswordGeneratedEvent passwordGeneratedEvent) {
        secureRandom = new SecureRandom();

        setCharacterSet(characterSet);
        setWordList(wordList);
        setCount(count);
        setMinLength(minLength);
        setMaxLength(maxLength);
        setSecureRandomSeed(seed);

        this.passwordGeneratedEvent = passwordGeneratedEvent;
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
        if (characterSet == null) throw new NullPointerException("Character set cannot be null!");

        this.characterSet = characterSet;
    }

    /**
     * Get the List of String objects that represent words
     *
     * @return The List of String objects that represent words
     */
    public final List<String> getWordList() {
        return wordList;
    }

    /**
     * Set the List of String objects that represent words
     *
     * @param wordList The List of String objects that represent words
     */
    public final void setWordList(final List<String> wordList) {
        this.wordList = wordList;
    }

    /**
     * Get the amount of passwords that need to be generated
     *
     * @return The amount of passwords that need to be generatoed
     */
    public final int getCount() {
        return count;
    }

    /**
     * set the amount of passwords that need to be generated
     *
     * @param count The amount of passwords that need to be generated
     */
    public final void setCount(final int count) {
        if (count < 0) throw new IllegalArgumentException("Count cannot be smaller than zero!");

        this.count = count;
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
     * Get whether duplicate passwords are allowed
     *
     * @return True if duplicate passwords are allowed, otherwise false
     */
    public final boolean isAllowDuplicates() {
        return allowDuplicates;
    }

    /**
     * Set whether duplicate passwords are allowed
     *
     * @param allowDuplicates True if duplicate passwords are allowed, otherwise false
     */
    public final void setAllowDuplicates(final boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
    }

    /**
     * Get whether a password should be converted to base64
     *
     * @return True if a password should be converted to base64, otherwise false
     */
    public final boolean isToBase64() {
        return toBase64;
    }

    /**
     * Set whether a password should be converted to base64
     *
     * @param toBase64 True if a password should be converted to base64, otherwise false
     */
    public final void setToBase64(final boolean toBase64) {
        this.toBase64 = toBase64;
    }

    /**
     * Set the seed of the SecureRandom object
     *
     * @param seed The seed of the SecureRandom object
     */
    public final void setSecureRandomSeed(final byte[] seed) {
        if (seed == null) throw new NullPointerException("Seed cannot be null!");
        if (seed.length == 0) throw new IllegalArgumentException("Seed cannot be empty!");

        secureRandom.setSeed(seed);
    }

    @Override
    public final void run() {
        if (getCharacterSet() == null) throw new IllegalArgumentException("Character set cannot be null!");
        if (getCharacterSet().length() == 0) throw new IllegalArgumentException("Character set cannot be empty!");

        final List<Password> passwordList = new ArrayList<>();
        final char[] charSet = getCharacterSet().toCharArray();

        for (int i = 0; i < getCount(); i++) {
            boolean allowedToContinue = false;
            while (!allowedToContinue) {
                final ThreadLocalRandom thrRandom = ThreadLocalRandom.current();
                final int characterLength = thrRandom.nextInt(getMinLength(), getMaxLength() + 1);

                int currentLength = 0;
                final StringBuilder newPassword = new StringBuilder();
                while (currentLength != characterLength) {
                    // Set to whether words should be generated
                    boolean getRandomCharacter = getWordList() == null || getWordList().isEmpty();

                    // If a word can be generated
                    if (!getRandomCharacter) {
                        // Generate a secure random boolean to determine if a word should be picked over a character
                        final boolean addWord = secureRandom.nextBoolean();

                        if (addWord) {
                            String word = wordList.get(secureRandom.nextInt(wordList.size()));
                            int newLength = currentLength + word.length();

                            if (newLength <= characterLength) {
                                newPassword.append(word);
                                currentLength = newLength;
                            } else {
                                // Pick a substring of the chosen word that is small enough to fit the character length
                                while (newLength > characterLength) {
                                    word = word.substring(0, word.length() - 1);
                                    newLength = word.length();
                                }
                            }
                        } else {
                            getRandomCharacter = true;
                        }
                    }

                    if (getRandomCharacter) {
                        newPassword.append(charSet[secureRandom.nextInt(charSet.length)]);
                        currentLength++;
                    }
                }

                String actualPassword = newPassword.toString();
                if (isToBase64()) {
                    actualPassword = Base64.getEncoder().encodeToString(actualPassword.getBytes());
                }

                allowedToContinue = true;
                if (!isAllowDuplicates()) {
                    for (final Password password : passwordList) {
                        if (password.getValue().equals(actualPassword)) {
                            allowedToContinue = false;
                            break;
                        }
                    }
                }

                if (allowedToContinue) {
                    passwordList.add(new Password(actualPassword));
                }
            }
        }

        if (passwordGeneratedEvent != null) {
            passwordGeneratedEvent.passwordsGenerated(passwordList);
        }
    }
}
