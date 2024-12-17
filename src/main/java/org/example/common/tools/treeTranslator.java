package org.example.common.tools;

import org.example.encrypter.tools.BinaryConverter;
import org.example.service.TreeNode;

import java.io.FileOutputStream;

public class treeTranslator {

    public static int encryptTreeAndReturnSize(TreeNode<?> root, FileOutputStream fw , int bytesOfTheBiggestChar, StringBuilder byteCode ){
        int size=0;
        byteCode.append(BinaryConverter.convertByteSizeToBinValue(bytesOfTheBiggestChar));



        return size;
    }

}
