package linshen.slide;

/*
元素的 频数 是该元素在一个数组中出现的次数。

给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。

执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 */

import java.util.Arrays;

public class L1838 {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        System.out.println(n);
        Arrays.sort(nums);
        long sum = 0;
        int max = 1;
        for (int left = 0, right = 0; right < n; right++){
            sum += nums[right];
            //todo 注意这种溢出错误
            while ((right-left+1) * (long)nums[right] - sum > k) {
                sum -= nums[left];
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L1838().maxFrequency(new int[]{1,1,1}, 3));
    }

}
