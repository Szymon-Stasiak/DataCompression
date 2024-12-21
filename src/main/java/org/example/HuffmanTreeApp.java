package org.example;

import org.example.common.tools.HelpPrinter;
// import org.example.decrypter.Decoder;
import org.example.encrypter.Encoder;
import org.example.exceptions.FilePathsAreTheSameException;
import org.example.exceptions.SequencesCantBeLessThanZeroException;
import org.example.logger.Log;

public class HuffmanTreeApp {

    private final HelpPrinter helpPrinter;

    public HuffmanTreeApp(HelpPrinter helpPrinter) {
        this.helpPrinter = helpPrinter;
    }

    public static void main(String[] args) {
        HelpPrinter helpPrinter = new HelpPrinter();
        HuffmanTreeApp app = new HuffmanTreeApp(helpPrinter);
        app.run(args);
    }

    public void run(String[] args) {
        String inputPath = "src/main/resources/dane.txt";
        String inputPathDecoded = "src/main/resources/encoded.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String outputPathDecoded = "src/main/resources/decoded.txt";
        int lengthOfSequence = 1;
        Log.info("Application started");

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--help") || args[i].equals("--h") || args[i].equals("-help") || args[i].equals("-h")) {
                helpPrinter.printHelp();
                return;
            } else if (args[i].equals("--d") && i + 1 < args.length && args[i + 1].charAt(0) != '-') {

                // new Decoder( inputPathDecoded, outputPathDecoded);
                return;
            } else {
                if (args[i].equals("--in") && i + 1 < args.length && args[i + 1].charAt(0) != '-') {
                    inputPath = args[++i];
                } else if (args[i].equals("--out") && i + 1 < args.length && args[i + 1].charAt(0) != '-') {
                    outputPath = args[++i];
                } else if (args[i].equals("--length") && i + 1 < args.length && args[i + 1].charAt(0) != '-') {
                    lengthOfSequence = Integer.parseInt(args[++i]);
                }
            }
        }
        if (inputPath.equals(outputPath)) {
            throw new FilePathsAreTheSameException(
                    "Input and output paths are the same. You have to choose different paths.");
        }
        System.out.println("Length of sequence: " + lengthOfSequence);
        if (lengthOfSequence < 1) {
            throw new SequencesCantBeLessThanZeroException("Length of sequence has to be greater than 0.");
        }
        new Encoder(inputPath, outputPath, lengthOfSequence);
    }
}
