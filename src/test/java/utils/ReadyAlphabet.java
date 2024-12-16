// package utils;
//
// import org.example.Structures.Alphabet;
//
// public class ReadyAlphabet {
//    public static int[] RandomValues = {
//        34, 7, 12, 56, 78, 90, 43, 21, 67, 89, 45, 32, 15, 60, 73, 28, 92, 10, 49, 31, 83, 25, 66, 88, 39, 54
//    };
//
//    public static Alphabet getAlphabet() {
//        Alphabet alphabet = new Alphabet();
//        for (int i = 0; i < 128; i++) {
//            for (int j = 0; j < RandomValues[i % RandomValues.length]; j++) {
//                alphabet.add((char) i);
//            }
//        }
//        return alphabet;
//    }
// }
