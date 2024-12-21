package org.example.decrypter;

import org.example.common.structures.CharChain;
import org.example.common.structures.Dictionary;
import org.example.decrypter.tools.TreeDecoder;
import org.example.logger.Log;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Decoder {


    private final Dictionary<String,CharChain> dictionary;


    public Decoder(String inputPathDecoded, String outputPathDecoded) {
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
            while ((character = autoClosableFr.read()) != -1) {
                byteReader.append((char) character);
                key.append((char) character);
                Log.info("Key: " + key);
                CharChain code = dictionary.getValue(key.toString());
                if (code != null) {
                    writer.write(code.toChars());
                    key= new StringBuilder();
                }
            }
            Log.info(byteReader.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        new Decoder("src/main/resources/encoded.txt", "src/main/resources/decoded.txt");
    }

}
