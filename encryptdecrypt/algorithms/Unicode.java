package encryptdecrypt.algorithms;

public class Unicode extends Algorithm {


    @Override
    public String encrypt(String input, int key) {
        char[] symbols = input.toCharArray();
        char[] encryptedSymbols = new char[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            encryptedSymbols[i] = (char) (symbols[i] + key);
        }

        return String.valueOf(encryptedSymbols);
    }

    @Override
    public String decrypt(String input, int key) {
        return encrypt(input, -key);
    }
}
