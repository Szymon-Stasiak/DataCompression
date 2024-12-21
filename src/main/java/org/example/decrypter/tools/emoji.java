package org.example.decrypter.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class emoji {

    // Example emoji Unicode code points stored as integers
    // Example emoji Unicode code points stored as integers
    private static final List<Integer> EMOJI_CODE_POINTS = List.of(
            128512, // 😀 Grinning Face
            128514, // 😂 Face with Tears of Joy
            128521, // 😉 Winking Face
            128525, // 😍 Smiling Face with Heart-Eyes
            128536, // 😘 Face Blowing a Kiss
            128545, // 😡 Pouting Face
            128546, // 😢 Crying Face
            129315 // 🤣 Rolling on the Floor Laughing
            );

    public static void main(String[] args) {
        // Create a file to store the output
        String outputFile = "C:\\Users\\stszy\\IdeaProjects\\DataCompression\\src\\main\\resources\\emojis.txt";

        try (BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
            for (int codePoint : EMOJI_CODE_POINTS) {
                // Convert code point to a character string
                writer.write(new String(Character.toChars(codePoint)));
                writer.newLine();
            }
            System.out.println("Emojis have been written to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
