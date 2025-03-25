package linshen.binary;

/*
给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组，使得这 k 个子数组各自和的最大值 最小。

返回分割后最小的和的最大值。

子数组 是数组中连续的部份。

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= k <= min(50, nums.length)
 */

import java.util.Arrays;

public class L410 {
    //todo h：典型题，记住吧。虽然里面涉及到最大最小，但是二分的条件还是依赖于最小，最大只是影响check函数
    public int splitArray(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        int m = sum / k;
        //sum is not the right bound
        return findMax(nums, m, sum, k);
    }

    private int findMax(int[] nums, int left, int right, int k) {
        if(left == right)return left;
        else if(left == right - 1) {
            if(check(nums, k, left))return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(check(nums, k, mid))return findMax(nums, left, mid, k);
            else return findMax(nums, mid + 1, right, k);
        }
    }

    private boolean check(int[] nums, int k, int s) {
        int cnt = 0;
        int cur = 0;
        for (int num : nums) {
            if(num > s)return false;
            if (cur + num > s) {
                cur = 0;
                cnt++;
            }
            cur += num;
            if (cnt > k) return false;
        }
        if(cur > 0)cnt++;
        return cnt <= k;
    }
}
