package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.Scanner;
import org.example.Exceptions.FilePathsAreTheSameException;
import org.example.Tools.HelpPrinter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HuffmanTreeAppTest {

    @BeforeAll
    static void setUp() {}

    @Test
    void should_properlySetPaths() {
        String inputPath = "src/test/java/utils/ocochodzi.txt";
        String outputPath = "src/main/resources/encodedTest.txt";
        String[] args = {"--in", inputPath, "--out", outputPath};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
        new File(outputPath).delete();
    }

    @Test
    void should_properlySetPaths_WhenNoArgs() {
        String inputPath = "src/main/resources/dane.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String[] args = {};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
    }

    @Test
    void should_properlySetPaths_WhenInArg() {
        String inputPath = "src/test/java/utils/ocochodzi.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String[] args = {"--in", inputPath};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
    }

    @Test
    void should_propelrySetPaths_WhenOutArg() {
        String inputPath = "src/main/resources/dane.txt";
        String outputPath = "src/test/java/utils/output.txt";
        String[] args = {"--out", outputPath};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
        new File(outputPath).delete();
    }

    @Test
    void should_properlySetPathsWhenIsLackOfArgs1() {
        String inputPath = "src/main/resources/dane.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String[] args = {"--in", "--out"};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
        new File(outputPath).delete();
    }

    @Test
    void should_properlySetPathsWhenIsLackOfArgs2() {
        String inputPath = "src/main/resources/dane.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String[] args = {"--in"};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
        new File(outputPath).delete();
    }

    @Test
    void should_properlySetPathsWhenIsLackOfArgs3() {
        String inputPath = "src/main/resources/dane.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String[] args = {"--out"};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
        new File(outputPath).delete();
    }

    @Test
    void should_properlySetPathsWhenIsLackOfArgs4() {
        String inputPath = "src/main/resources/dane.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String[] args = {"--in", inputPath, "--out"};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
        new File(outputPath).delete();
    }

    @Test
    void should_properlySetPathsWhenIsLackOfArgs5() {
        String inputPath = "src/main/resources/dane.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String[] args = {"--out", outputPath, "--in"};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
        new File(outputPath).delete();
    }

    @Test
    void should_properlySetPathsWhenIsLackOfArgs6() {
        String inputPath = "src/main/resources/dane.txt";
        String outputPath = "src/main/resources/encoded.txt";
        String[] args = {"--out", "--in"};
        HuffmanTreeApp.main(args);
        assertTrue(new java.io.File(inputPath).exists());
        assertTrue(new java.io.File(outputPath).exists());
        new File(outputPath).delete();
    }

    @Test
    public void testPrintHelp1Called() {
        HelpPrinter mockPrinter = mock(HelpPrinter.class);

        HuffmanTreeApp app = new HuffmanTreeApp(mockPrinter);

        String[] args = {"--help"};
        app.run(args);

        verify(mockPrinter, times(1)).printHelp();
    }

    @Test
    public void testPrintHelp2Called() {
        HelpPrinter mockPrinter = mock(HelpPrinter.class);

        HuffmanTreeApp app = new HuffmanTreeApp(mockPrinter);

        String[] args = {"-help"};
        app.run(args);

        verify(mockPrinter, times(1)).printHelp();
    }

    @Test
    public void testPrintHCalled() {
        HelpPrinter mockPrinter = mock(HelpPrinter.class);

        HuffmanTreeApp app = new HuffmanTreeApp(mockPrinter);

        String[] args = {"--h"};
        app.run(args);

        verify(mockPrinter, times(1)).printHelp();
    }

    @Test
    public void testPrintH2Called() {
        HelpPrinter mockPrinter = mock(HelpPrinter.class);

        HuffmanTreeApp app = new HuffmanTreeApp(mockPrinter);

        String[] args = {"-h"};
        app.run(args);

        verify(mockPrinter, times(1)).printHelp();
    }

    @Test
    public void should_throwFilePathsAreTheSameException() {
        HelpPrinter mockPrinter = mock(HelpPrinter.class);

        HuffmanTreeApp app = new HuffmanTreeApp(mockPrinter);

        String[] args = {"--in", "src/main/resources/dane.txt", "--out", "src/main/resources/dane.txt"};
        assertThrows(FilePathsAreTheSameException.class, () -> app.run(args));
    }

//    @Test
//    public void should_correctCodeOCoChodzi() {
//        String inputPath = "src/test/java/utils/ocochodzi.txt";
//        String outputPath = "src/test/java/utils/encodedTest.txt";
//        String[] args = {"--in", inputPath, "--out", outputPath};
//        HuffmanTreeApp.main(args);
//        assertTrue(new java.io.File(inputPath).exists());
//        assertTrue(new java.io.File(outputPath).exists());
//        String expectedOutput = "D8=";
//        String output;
//        try {
//            File file = new File(outputPath);
//            Scanner scanner = new Scanner(file);
//            output = scanner.nextLine();
//            scanner.close();
//            assertEquals(expectedOutput, output);
//        } catch (java.io.FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        new File(outputPath).delete();
//    }
}
