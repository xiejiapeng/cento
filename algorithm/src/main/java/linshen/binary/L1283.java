package linshen.binary;

/*
给你一个整数数组 nums 和一个正整数 threshold  ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。

请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。

每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。

1 <= nums.length <= 5 * 10^4
1 <= nums[i] <= 10^6
nums.length <= threshold <= 10^6

题目保证一定有解。
 */

import java.util.Arrays;

public class L1283 {
    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        //[1,max]
        return findSmallest(1, max, threshold, nums);
    }

    private int findSmallest(int left, int right, int threshold, int[] nums) {
        if(left == right) return left;
        else if(left == right - 1) {
            if(check(nums, left, threshold))return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(check(nums, mid, threshold))return findSmallest(left, mid, threshold, nums);
            else return findSmallest(mid + 1, right, threshold, nums);
        }
    }

    private boolean check(int[] nums, int t, int threshold) {
        int sum = 0;
        for (int x : nums) {
            if(x % t == 0)sum += (x / t);
            else sum += (x/t + 1);
        }
        return sum <= threshold;
    }

    public static void main(String[] args) {
        System.out.println(new L1283().smallestDivisor(new int[]{1,2,5,9}, 6));
    }
}
