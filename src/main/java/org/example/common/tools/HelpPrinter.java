package org.example.common.tools;


//todo update
public class HelpPrinter {
    public void printHelp() {
        System.out.println("Usage: java -jar HuffmanTreeApp.jar [options]");
        System.out.println("Options:");
        System.out.println("  --in <path>    Path to the file to be encoded");
        System.out.println("  --out <path>   Path to the file where the encoded file will be saved");
        System.out.println("  --length <n>   Length of the sequence to be encoded");
    }
}
