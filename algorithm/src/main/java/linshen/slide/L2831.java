package linshen.slide;

import java.util.*;

/*
给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。

如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。注意，空数组是 等值子数组 。

从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。

子数组 是数组中一个连续且可能为空的元素序列。

todo h：本题需要关注！！！限定重复元素为x，再枚举x。不用担心时间复杂度，在枚举x时，只遍历x出现过的位置，
这样总的时间复杂度并不会增高！！！
 */

public class L2831 {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        Map<Integer,int[]> cnt = new HashMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> -o[1]));
        int max = 0;
        for (int left = 0, right = 0; right < n; right++){
            int rn = nums.get(right);
            if(cnt.containsKey(rn)) {
                int[] rc = cnt.get(rn);
                queue.remove(rc);
                rc[1] += 1;
                queue.add(rc);
            } else {
                int[] x = new int[]{rn, 1};
                cnt.put(rn, x);
                queue.add(x);
            }
            while (!meet(queue, right - left + 1, k)) {
                int ln = nums.get(left);
                int[] lc = cnt.get(ln);
                queue.remove(lc);
                lc[1]--;
                if(lc[1] > 0) {
                    queue.add(lc);
                }
                left++;
            }

            max = Math.max(max, queue.peek()[1]);

        }
        return max;
    }

    private boolean meet(PriorityQueue<int[]> queue, int all, int k) {
        int[] max = queue.peek();
        return all - max[1] <= k;
    }

    public static void main(String[] args) {
        System.out.println(new L2831().longestEqualSubarray(Arrays.asList(1,3,2,3,1,3), 3));
    }
}
