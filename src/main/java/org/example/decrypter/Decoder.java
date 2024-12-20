package org.example.decrypter;

import static org.example.common.tools.BinaryConverter.convertBinToInt;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.example.common.structures.CharChain;
import org.example.common.structures.Dictionary;
import org.example.decrypter.tools.TreeDecoder;
import org.example.logger.Log;
import org.example.service.Translator;

public class Decoder implements Translator {

    private Dictionary<String, CharChain> dictionary;
    private final String inputPathDecoded;
    private final String outputPathDecoded;

    public Decoder(String inputPathDecoded, String outputPathDecoded) {
        this.inputPathDecoded = inputPathDecoded;
        this.outputPathDecoded = outputPathDecoded;
    }

    @Override
    public void translate() {
        StringBuilder byteReader = new StringBuilder();
        try {
            InputStreamReader fr = new InputStreamReader(new FileInputStream(inputPathDecoded), "UTF-8");
            dictionary = TreeDecoder.generateDictionary(byteReader, fr);
            decodeMessage(byteReader, fr, outputPathDecoded);
            Log.info("Decoding finished successfully!");

        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void decodeMessage(StringBuilder byteReader, InputStreamReader fr, String outputPathDecoded) {
        try (InputStreamReader autoClosableFr = fr;
             FileOutputStream fos = new FileOutputStream(outputPathDecoded);
             OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            int character;
            StringBuilder key = new StringBuilder();
            int additionalZeroes = additionalZeroes(byteReader, autoClosableFr);
            Log.info("Additional zeroes: " + additionalZeroes);
            for (int i = 0; i < additionalZeroes; i++) {
                autoClosableFr.read();
            }
            while ((character = autoClosableFr.read()) != -1) {
                byteReader.append((char) character);
                key.append((char) character);
                CharChain code = dictionary.getValue(key.toString());
                if (code != null) {
                    for (int c : code.getChain()) {
                        if (c != 0) {
                            Log.info("Inr : " + c);
                            writer.write(new String(Character.toChars(c)));
                        }

                    }
                    key = new StringBuilder();
                }
            }
            Log.info(byteReader.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int additionalZeroes(StringBuilder byteReader, InputStreamReader fr) throws IOException {
        while (byteReader.length() < 3) {
            int current = fr.read();
            byteReader.append((char) current);
        }
        return convertBinToInt(byteReader.substring(0, 3));
    }

    public static void main(String[] args) {
        new Decoder("src/main/resources/encoded.txt", "src/main/resources/decoded.txt").translate();
    }
}
