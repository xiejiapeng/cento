package index.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L954 {
    public static void main(String[] args) {
        System.out.println(new L954().canReorderDoubled(new int[]{2, 4, 0, 0, 8, 1}));
    }

    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> all = new HashMap<>();
        for (int x : arr) {
            if (all.containsKey(x * 2)) {
                int y = x * 2;
                all.put(y, all.get(y) - 1);
                if (all.get(y) <= 0) {
                    all.remove(y);
                }
            } else if (x % 2 == 0 && all.containsKey(x / 2)) {
                int y = x / 2;
                all.put(y, all.get(y) - 1);
                if (all.get(y) <= 0) {
                    all.remove(y);
                }
            } else {
                all.put(x, all.getOrDefault(x, 0) + 1);
            }
        }
        return all.isEmpty();
    }
}
