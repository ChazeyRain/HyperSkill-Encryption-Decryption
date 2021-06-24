package encryptdecrypt.algorithms;

import java.util.Locale;

public class Shift extends Algorithm {

    @Override
    public String encrypt(String input, int key) {

        key = key % 27;

        char[] symbols = input.toCharArray();
        char[] encryptedSymbols = new char[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            if (Character.toString(symbols[i]).matches("[A-Za-z]")) {

                char newChar = (char) (symbols[i] + key);

                if((newChar > 90 && newChar < 97 + key) || newChar > 122) {
                    encryptedSymbols[i] = (char)(newChar - 26);
                } else {
                    encryptedSymbols[i] = newChar;
                }

            } else {
                encryptedSymbols[i] = symbols[i];
            }
        }

        return String.valueOf(encryptedSymbols);
    }

    @Override
    public String decrypt(String input, int key) {
        key = key % 27;

        char[] symbols = input.toCharArray();
        char[] encryptedSymbols = new char[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            if (Character.toString(symbols[i]).matches("[A-Za-z]")) {

                char newChar = (char) (symbols[i] - key);

                if((newChar > 90 - key && newChar < 97) || newChar < 65) {
                    encryptedSymbols[i] = (char)(newChar + 26);
                } else {
                    encryptedSymbols[i] = newChar;
                }

            } else {
                encryptedSymbols[i] = symbols[i];
            }
        }

        return String.valueOf(encryptedSymbols);
    }
}
