package linshen.greedy;

import java.util.List;

/*
给你一个长度为 n 的整数数组 nums 。

你的目标是从下标 0 出发，到达下标 n - 1 处。每次你只能移动到 更大 的下标处。

从下标 i 跳到下标 j 的得分为 (j - i) * nums[i] 。

请你返回你到达最后一个下标处能得到的 最大总得分 。
3 5 2 5 4* 1 6
3 5 2 5 4 1* 6
3 5 2* 5 4 1 6
 */

public class L3282 {
    public long findMaximumScore(List<Integer> nums) {
        long s = 0;
        int cur = nums.get(0);
        for (int i = 0; i < nums.size() - 1; i++){
            int x = nums.get(i);
            if(x > cur)cur = x;
            s += cur;
        }
        return s;
    }
}
