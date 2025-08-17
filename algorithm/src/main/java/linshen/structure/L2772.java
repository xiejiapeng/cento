package linshen.structure;

/*
给你一个下标从 0 开始的整数数组 nums 和一个正整数 k 。

你可以对数组执行下述操作 任意次 ：

从数组中选出长度为 k 的 任一 子数组，并将子数组中每个元素都 减去 1 。
如果你可以使数组中的所有元素都等于 0 ，返回 true ；否则，返回 false 。

子数组 是数组中的一个非空连续元素序列。
提示：

1 <= k <= nums.length <= 105
0 <= nums[i] <= 106
 */

public class L2772 {
    public static void main(String[] args) {
        System.out.println(new L2772().checkArray(new int[]{2, 2, 3, 1, 1, 0}, 3));
    }

    //todo h 记住这种滚动法
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        int x = 0;
        for (int i = 0; i < n; i++) {
            x += diff[i];
            nums[i] += x;
            if (nums[i] < 0) {
                return false;
            } else if (nums[i] == 0) {
                continue;
            } else {
                if (n - i < k) return false;
                diff[i] -= nums[i];
                x -= nums[i];
                diff[i + k] += nums[i];
            }
        }
        return true;
    }
}
