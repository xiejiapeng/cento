package sulwish;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L873 {
    public static void main(String[] args) {
        System.out.println(new L873().lenLongestFibSubseq(new int[]{1, 3, 5}));
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Set<Integer> exist = new HashSet<>();
        Map<Integer, Integer>[] ans = new Map[n];
        int max = -1;
        for (int i = 0; i < n; i++) {
            ans[i] = new HashMap<>();
            int cur = arr[i];
            for (int j = 0; j < i; j++) {
                Map<Integer, Integer> aj = ans[j];
                int last = arr[j];
                if (!(exist.contains(cur - last) && (cur - last < last))) continue;
                else {
                    if (aj.containsKey(cur - last)) {
                        if (ans[i].containsKey(last)) {
                            ans[i].put(last, Math.max(ans[i].get(last), aj.get(cur - last) + 1));
                        } else {
                            ans[i].put(last, aj.get(cur - last) + 1);
                        }
                    } else {
                        ans[i].put(last, 3);
                    }
                }

                max = Math.max(max, ans[i].getOrDefault(last, 0));
            }
            exist.add(cur);
        }
        return max;
    }
}
