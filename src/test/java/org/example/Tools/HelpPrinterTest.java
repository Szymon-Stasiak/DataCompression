package org.example.Tools;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class HelpPrinterTest {

    @Test
    public void testPrintHelp() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        HelpPrinter helpPrinter = new HelpPrinter();
        helpPrinter.printHelp();

        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(output.contains("Usage: java -jar HuffmanTreeApp.jar [options]"));
        assertTrue(output.contains("--in <path>    Path to the file to be encoded"));
        assertTrue(output.contains("--out <path>   Path to the file where the encoded file will be saved"));
    }
}
