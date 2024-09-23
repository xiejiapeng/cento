package index.binary;

import java.util.*;

/*
给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。

定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。

请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */

public class L373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> nums1[o[0]] + nums2[o[1]]));
        for(int i = 0; i < nums2.length; i++){
            queue.add(new int[]{0,i});
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty() && k-->0) {
            int[] x = queue.poll();
            ans.add(Arrays.asList(nums1[x[0]], nums2[x[1]]));
            if(x[0] + 1 < nums1.length) {
                queue.add(new int[]{x[0]+1, x[1]});
            }
        }
        return ans;
    }
}
