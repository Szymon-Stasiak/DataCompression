package org.example.common.tools;

import org.example.common.structures.CharChain;
import org.example.common.structures.WordNode;
import org.example.encrypter.structures.HuffmanTree;
import org.example.encrypter.structures.HuffmanTreeNode;
import org.example.encrypter.tools.BinaryConverter;
import org.example.service.TreeNode;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class treeTranslator<K extends Comparable<K>> {

    public int encryptTreeAndReturnSize(HuffmanTreeNode root, FileOutputStream fw, int bytesOfTheBiggestChar, StringBuilder byteCode) {
        int size = 2;
        byteCode.append(BinaryConverter.convertByteSizeToBinValue(bytesOfTheBiggestChar));
        BFSIterator<HuffmanTreeNode> iterator = new BFSIterator<>(root);
        while (iterator.hasNext()) {
            HuffmanTreeNode node = iterator.next();
            if (node.getWordNode().getCode() == null) {
                byteCode.append("0");
                size++;
            }else{
                byteCode.append("1");
                size++;
                CharChain charChain = new CharChain(node.getWordNode().getKey().toString());
              //  for(char c : charChain.getChain()){



            }



        }
        return size;
    }

    public static String speaceNeededToWriteCharacter(int bytesOfTheBiggestChar, char c){
        if(bytesOfTheBiggestChar == 1) {
            return "";
        }else {
            //check how may bytes are needed to write the character
            int bitsNeeded = 0;


        }

        return "";
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\stszy\\IdeaProjects\\DataCompression\\src\\main\\resources\\dane.txt";

        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] bytes = inputStream.readAllBytes();
            CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            CharBuffer charBuffer = decoder.decode(byteBuffer);

            for (int i = 0; i < charBuffer.length(); i++) {
                int codePoint = Character.codePointAt(charBuffer, i);
                System.out.printf("Znak: %c, Punkt kodowy: U+%04X%n", codePoint, codePoint);
                if (Character.charCount(codePoint) == 2) {
                    i++; // Przeskocz drugi znak pary zastępczej
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

