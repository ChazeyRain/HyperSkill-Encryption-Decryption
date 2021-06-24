package encryptdecrypt;

import encryptdecrypt.algorithms.Algorithm;
import encryptdecrypt.algorithms.AlgorithmFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] params = new String[6]; // 0 - data  / 1 - alg / 2 - mode / 3 - key / 4 - in / 5 - out

        for (int i = 0; i < args.length; i = i + 2) {
            switch (args[i]) {
                case "-data":
                    params[0] = args[i + 1];
                    break;
                case "-alg":
                    params[1] = args[i + 1];
                    break;
                case "-mode":
                    params[2] = args[i + 1];
                    break;
                case "-key":
                    params[3] = args[i + 1];
                    break;
                case "-in":
                    params[4] = args[i + 1];
                    break;
                case "-out":
                    params[5] = args[i + 1];
                    break;
            }
        }

        try {
            start(params);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void start(String[] params) throws Exception, IOException {

        AlgorithmFactory factory = new AlgorithmFactory();
        String text;
        boolean mode;
        int key;

        if (params[0] != null) {
            text = params[0];
        } else {
            text = loadFromFile(params[4]);
        }

        if ("enc".equals(params[2])) {
            mode = true;
        } else if ("dec".equals(params[2])) {
            mode = false;
        } else {
            throw new IllegalArgumentException("Error: use only dec or enc modes " + params[2]);
        }

        try {
            key = Integer.parseInt(params[3]);
        } catch (Exception e) {
            key = 0;
        }

        Algorithm alg = factory.create(params[1]);

        String transformedText = alg.start(text, key, mode);

        if (params[5] == null) {
            System.out.println(transformedText);
        } else {
            outputInFile(transformedText, params[5]);
        }

    }

    private static String loadFromFile(String path) throws FileNotFoundException{
        File file = new File(path);

        StringBuilder text = new StringBuilder();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error: file can not be founded");
        }

        return text.toString();
    }

    private static void outputInFile(String text, String path) {
        File file = new File(path);

        try {
            file.createNewFile();

            FileWriter writer = new FileWriter(path);
            writer.write(text);
            writer.close();

        } catch (IOException e) {
            //do nothing
        }
    }

}