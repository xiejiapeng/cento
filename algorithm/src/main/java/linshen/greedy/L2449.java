package linshen.greedy;

/*
给你两个正整数数组 nums 和 target ，两个数组长度相等。

在一次操作中，你可以选择两个 不同 的下标 i 和 j ，其中 0 <= i, j < nums.length ，并且：

令 nums[i] = nums[i] + 2 且
令 nums[j] = nums[j] - 2 。
如果两个数组中每个元素出现的频率相等，我们称两个数组是 相似 的。

请你返回将 nums 变得与 target 相似的最少操作次数。测试数据保证 nums 一定能变得与 target 相似。
 */

import java.util.Arrays;

public class L2449 {
    /*
    1 2 5
    1 3 4
     */
    //todo hhhhh 没有能解决，cp，注意奇偶分离
    public long makeSimilar(int[] nums, int[] target) {
        f(nums);
        f(target);
        long ans = 0L;
        for (int i = 0; i < nums.length; ++i)
            ans += Math.abs(nums[i] - target[i]);
        return ans / 4;
    }

    private void f(int[] a) {
        // 由于元素都是正数，把奇数变成相反数，这样排序后奇偶就自动分开了
        for (int i = 0; i < a.length; ++i)
            if (a[i] % 2 != 0) a[i] = -a[i];
        Arrays.sort(a);
    }

}
