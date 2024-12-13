package utils;

import java.util.ArrayList;

public class TestUtils {

    public static boolean checkMinHeap(ArrayList<Comparable> heap) {
        for (int i = 0; i < heap.size(); i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < heap.size() && heap.get(i).compareTo(heap.get(left)) > 0) {
                return false;
            }
            if (right < heap.size() && heap.get(i).compareTo(heap.get(right)) > 0) {
                return false;
            }
        }
        return true;
    }
}
