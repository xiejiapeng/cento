package linshen.binary;

/*
给你一个下标从 0 开始的数组 nums ，它含有 n 个非负整数。

每一步操作中，你需要：

选择一个满足 1 <= i < n 的整数 i ，且 nums[i] > 0 。
将 nums[i] 减 1 。
将 nums[i - 1] 加 1 。
你可以对数组执行 任意 次上述操作，请你返回可以得到的 nums 数组中 最大值 最小 为多少。
 */

import java.util.Arrays;

public class L2439 {
    public int minimizeArrayValue(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        return findLeast(nums, min, max);
    }
    private int findLeast(int[] nums, int left, int right) {
        if(left == right)return left;
        else if(left == right - 1) {
            if(check(nums, left))return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(check(nums, mid))return findLeast(nums, left, mid);
            else return findLeast(nums, mid + 1, right);
        }
    }

    //todo 虽然卡long很无聊，但是还是注意一下
    private boolean check(int[] tmp, int target) {
        int n = tmp.length;
        long[] nums = new long[n];
        for (int i = 0; i < n; i++){
            nums[i] = tmp[i];
        }
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > target) {
                long t = nums[i-1] + (nums[i] - target);
                nums[i] = target;
                nums[i-1] = t;
            }
        }
        return nums[0] <= target;
    }

    public static void main(String[] args) {
        System.out.println(new L2439().minimizeArrayValue(new int[]{3,7,1,6}));
    }
}
