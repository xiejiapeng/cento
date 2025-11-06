package linshen.slide;

import java.util.Arrays;

/*
给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，
然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。

如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。

todo 需要给定限制条件，才好利用单调性转化为滑动窗口
 */
public class L1658 {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int max = -1;
        int cur = 0;
        int target = sum - x;
        //todo 不是<=0
        if(target < 0)return -1;
        for (int left = 0, right = 0; right < n; right++){
            cur += nums[right];
            while (cur > target) {
                cur -= nums[left];
                left++;
            }
            if(cur == target) {
                max = Math.max(max, right - left + 1);
            }
        }
        return max == -1 ? -1 : n - max;
    }

    public static void main(String[] args) {
        System.out.println(new L1658().minOperations(new int[]{8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309}, 134365));
    }
}
