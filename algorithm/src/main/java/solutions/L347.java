package solutions;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class L347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freq = new HashMap<>();
        for(int x : nums) {
            freq.put(x, freq.getOrDefault(x,0) + 1);
        }
        int n = freq.size();
        int[][] f = new int[n][2];
        int i = 0;
        for(int x : freq.keySet()) {
            f[i++] = new int[]{x,freq.get(x)};
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1],o2[1]));
        for(int[] pair : f){
            queue.add(pair);
            if(queue.size() > k)queue.poll();
        }
        int[] ans = new int[queue.size()];
        int j = 0;
        while (!queue.isEmpty()){
            ans[j++] = queue.poll()[0];
        }
        return ans;
    }
}
