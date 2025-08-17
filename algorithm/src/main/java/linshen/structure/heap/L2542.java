package linshen.structure.heap;

/*
给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两者长度都是 n ，再给你一个正整数 k 。
你必须从 nums1 中选一个长度为 k 的 子序列 对应的下标。

对于选择的下标 i0 ，i1 ，...， ik - 1 ，你的 分数 定义如下：

nums1 中下标对应元素求和，乘以 nums2 中下标对应元素的 最小值 。
用公式表示： (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]) 。
请你返回 最大 可能的分数。

一个数组的 子序列 下标是集合 {0, 1, ..., n-1} 中删除若干元素得到的剩余集合，也可以不删除任何元素。
提示：

n == nums1.length == nums2.length
1 <= n <= 105
0 <= nums1[i], nums2[j] <= 105
1 <= k <= n
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L2542 {
    //todo hhh 贪心构造类的题目，准确率总是不高，要考虑清楚各种场景和状态变化
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> nums2[b]-nums2[a]);
        for (int i = 0; i < n; i++){
            queue.add(i);
        }
        long max = -1;
        long sum = 0;
        PriorityQueue<Integer> select = new PriorityQueue<>(Comparator.comparingInt(o -> nums1[o]));
        while (!queue.isEmpty()) {
            int x = queue.poll();
            while (select.size() > k-1) {
                int y = select.poll();
                sum -= nums1[y];
            }
            select.add(x);
            sum += nums1[x];

            if(select.size() == k){
                max = Math.max(max, sum * nums2[x]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L2542().maxScore(new int[]{2,1,14,12}, new int[]{11,7,13,6}, 3));
    }
}
