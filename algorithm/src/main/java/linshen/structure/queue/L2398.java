package linshen.structure.queue;

/*
你有 n 个机器人，给你两个下标从 0 开始的整数数组 chargeTimes 和 runningCosts ，两者长度都为 n 。
第 i 个机器人充电时间为 chargeTimes[i] 单位时间，花费 runningCosts[i] 单位时间运行。再给你一个整数 budget 。

运行 k 个机器人 总开销 是 max(chargeTimes) + k * sum(runningCosts) ，其中 max(chargeTimes)
是这 k 个机器人中最大充电时间，sum(runningCosts) 是这 k 个机器人的运行时间之和。

请你返回在 不超过 budget 的前提下，你 最多 可以运行的 连续 的机器人数目为多少。
提示：
chargeTimes.length == runningCosts.length == n
1 <= n <= 5 * 104
1 <= chargeTimes[i], runningCosts[i] <= 105
1 <= budget <= 10^15
 */

import java.util.LinkedList;

public class L2398 {
    //todo hhh 比较综合的一题；当发现想用区间dp统计max的时候，会超出复杂度，这时候考虑用单调栈
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        long sum = 0;
        int ans = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int left = 0, right = 0; right < n; right++){
            //todo
            //复习一下滑动窗口的套路，先定义left和right，在进入循环一开始就将right加入，更新状态；然后再往右移动left
            while (!queue.isEmpty() && chargeTimes[queue.getLast()] <= chargeTimes[right]) {
                queue.pollLast();
            }
            queue.addLast(right);
            int max = chargeTimes[queue.getFirst()];
            sum += runningCosts[right];
            while (left <= right && max + (right - left + 1) * sum > budget) {
                if(queue.getFirst() == left) {
                    queue.removeFirst();
                }
                sum -= runningCosts[left];
                if(!queue.isEmpty())max = chargeTimes[queue.getFirst()];
                left++;
            }
            if(left <= right)ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L2398().maximumRobots(new int[]{11}, new int[]{10}, 19));
    }
}
