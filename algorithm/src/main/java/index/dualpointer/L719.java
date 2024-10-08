package index.dualpointer;

/*
数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。

n == nums.length
2 <= n <= 104
0 <= nums[i] <= 106
1 <= k <= n * (n - 1) / 2

给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L719 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> nums[o[1]] - nums[o[0]]));
        int n = nums.length;
        for (int i = 0; i < n; i++){
            if(i+1<n){
                queue.add(new int[]{i,i+1});
            }

        }
        int dist = 0;
        while (k-- > 0) {
            int[] x = queue.poll();
            dist = nums[x[1]] - nums[x[0]];
            if(x[1] + 1 < n){
                queue.add(new int[]{x[0], x[1] + 1});
            }
        }
        return dist;
    }
}
