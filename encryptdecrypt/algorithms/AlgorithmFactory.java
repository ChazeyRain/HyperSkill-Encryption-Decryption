package encryptdecrypt.algorithms;

public class AlgorithmFactory {

    public Algorithm create(String algorithm) {
        switch (algorithm) {
            case "unicode":
                return new Unicode();
            case "shift":
            default:
                return new Shift();
        }
    }
}
