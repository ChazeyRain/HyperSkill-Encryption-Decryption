package encryptdecrypt.algorithms;

public abstract class Algorithm {

    public String start(String input, int key, boolean mode) {
        if (mode) {
            return encrypt(input, key);
        } else {
            return decrypt(input, key);
        }
    }

    public abstract String encrypt(String input, int key);

    public abstract String decrypt(String input, int key);

}
