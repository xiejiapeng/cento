package index.hashtable;

import java.util.PriorityQueue;

public class L846 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        PriorityQueue<Integer> all = new PriorityQueue<>();
        for (int x : hand) all.add(x);

        while (!all.isEmpty()) {
            int x = all.peek();
            for (int i = 0; i < groupSize; i++) {
                int y = x + i;
                if (!all.contains(y)) return false;
                else {
                    all.remove(y);
                }
            }
        }
        return true;
    }
}
