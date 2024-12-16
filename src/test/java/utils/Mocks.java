// package utils;
//
// import static org.mockito.Mockito.*;
//
// import java.util.concurrent.atomic.AtomicInteger;
// import java.util.concurrent.atomic.AtomicReference;
// import org.example.Structures.Alphabet;
// import org.example.Structures.Basics.WordNode;
// import org.example.Structures.Basics.HuffmanTreeNode;
//
// public class Mocks {
//
//    public static WordNode pairMock(char character, int counter) {
//        WordNode mock = mock(WordNode.class);
//
//        AtomicInteger counterState = new AtomicInteger(counter);
//        AtomicReference<String> code = new AtomicReference<>();
//
//        when(mock.getCharacter()).thenReturn(character);
//        when(mock.getCounter()).thenReturn(counterState.get());
//        doAnswer(invocation -> {
//                    code.set(invocation.getArgument(0));
//                    return null;
//                })
//                .when(mock)
//                .setCode(anyString());
//        when(mock.getCode()).thenAnswer(invocation -> code.get());
//
//        when(mock.getCharacter()).thenReturn(character);
//        when(mock.getCounter()).thenReturn(counterState.get());
//        when(mock.getCode()).thenReturn(null);
//
//        doAnswer(invocation -> {
//                    counterState.incrementAndGet();
//                    return null;
//                })
//                .when(mock)
//                .increment();
//
//        return mock;
//    }
//
//    public static HuffmanTreeNode treeNodeMock(WordNode pair) {
//        HuffmanTreeNode mock = mock(HuffmanTreeNode.class);
//
//        when(mock.getPair()).thenReturn(pair);
//        int pairCounter = pair.getCounter();
//        when(mock.getValue()).thenReturn(pairCounter);
//
//        when(mock.compareTo(any(HuffmanTreeNode.class))).thenAnswer(invocation -> {
//            HuffmanTreeNode other = invocation.getArgument(0);
//            return Integer.compare(mock.getValue(), other.getValue());
//        });
//
//        return mock;
//    }
//
//    public static Alphabet alphabetPartMock() {
//        Alphabet mock = mock(Alphabet.class);
//        WordNode[] pairs = new WordNode[26];
//        for (int i = 0; i < 26; i++) {
//            pairs[i] = pairMock((char) ('a' + i), i + 1);
//        }
//
//        when(mock.getUniqueCharacters()).thenReturn(26);
//        when(mock.getPairAt(anyInt())).thenAnswer(invocation -> {
//            int index = invocation.getArgument(0);
//            return pairs[index];
//        });
//
//        when(mock.getPairAt(anyChar())).thenAnswer(invocation -> {
//            char character = invocation.getArgument(0);
//            return pairs[character - 'a'];
//        });
//
//        return mock;
//    }
// }
