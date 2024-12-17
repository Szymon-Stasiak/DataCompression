package org.example.common.tools;

import org.example.encrypter.structures.HuffmanTree;
import org.example.encrypter.structures.HuffmanTreeNode;
import org.example.encrypter.tools.BinaryConverter;
import org.example.service.TreeNode;

import java.io.FileOutputStream;

public class treeTranslator {

    public static  int encryptTreeAndReturnSize(HuffmanTreeNode root, FileOutputStream fw , int bytesOfTheBiggestChar, StringBuilder byteCode ){
        int size=0;
        byteCode.append(BinaryConverter.convertByteSizeToBinValue(bytesOfTheBiggestChar));
        BFSIterator<HuffmanTreeNode> iterator = new BFSIterator<>(root);
        while (iterator.hasNext()){
            HuffmanTreeNode node = iterator.next();
           // if(node.g)
        }



        return size;
    }

}
