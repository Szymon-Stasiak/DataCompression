package org.example.decrypter;

import java.io.FileInputStream;
import java.io.IOException;


//to future use
public class BinReader {
    public static void main(String[] args) {
        String filePath = "src\\main\\resources\\encoded.txt";

        try (FileInputStream fis = new FileInputStream(filePath)) {
            int byteValue;
            while ((byteValue = fis.read()) != -1) {
                String bits = String.format("%8s", Integer.toBinaryString(byteValue & 0xFF))
                        .replace(' ', '0');
                System.out.print(bits);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
