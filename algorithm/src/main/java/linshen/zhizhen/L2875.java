package linshen.zhizhen;

/*
给你一个下标从 0 开始的数组 nums 和一个整数 target 。

下标从 0 开始的数组 infinite_nums 是通过无限地将 nums 的元素追加到自己之后生成的。

请你从 infinite_nums 中找出满足 元素和 等于 target 的 最短 子数组，并返回该子数组的长度。
如果不存在满足条件的子数组，返回 -1 。

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= target <= 109
 */

import java.util.Arrays;

public class L2875 {
    public int minSizeSubarray(int[] nums, int t) {
        long target = t;
        int n = nums.length;
        long sum = 0;
        //todo h:不要用stream计算和，如果和为long的话
        for (int x : nums) {
            sum += x;
        }
        long cnt = target / sum;
        target = target % sum;
        System.out.println("sum="+sum+",target="+target+",t="+(sum-target));
        //todo 记得乘以n
        if(target == 0)return (int)(cnt * n);
        long[] s = new long[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        int min = Integer.MAX_VALUE;
        for (int left = 0, right = 0; right <= n; right++){
            while (left + 1 <= right && s[right] - s[left+1] >= target) {
                left++;
            }
            if(s[right] - s[left] == target) {
                min = Math.min(min, right - left);
            }
        }
        int min2 = Integer.MAX_VALUE;
        System.out.println("sum="+sum+",target="+target+",t="+(sum-target));
        for (int left = 0, right = 0; right <= n; right++){
            while (left + 1 <= right && s[right] - s[left+1] >= sum - target) {
                left++;
            }
            if(right == n - 1) {
                System.out.println("right="+right+",left="+left+",sum[right]="+s[right]
                        +",sum[left]="+s[left]+",t="+(sum - target));
            }
            if(s[right] - s[left] == sum - target) {
                System.out.println("right="+right+",left="+left);
                min2 = Math.min(min2, n - (right - left));
            }
        }
        if(min == Integer.MAX_VALUE && min2 == Integer.MAX_VALUE)return -1;
        else return (int)(Math.min(min, min2) + cnt * n);
    }

    public static void main(String[] args) {
        System.out.println(new L2875().minSizeSubarray(new int[]{1,10000,10000,1}, 2));
    }
}
