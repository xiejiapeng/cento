package linshen.greedy;

/*
给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。

一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。

请你返回乘积为正数的最长子数组长度。
提示：

1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
 */

public class L1567 {
    public int getMaxLen(int[] nums) {
        int first = -1;
        int last = -1;
        int nc = 0;
        int l = -1;
        int max = 0;
        //[l, i-1]
        //todo h 遇到截停类的题目，一定注意数组结束时还需要进行一次判断
        for (int i = 0; i <= nums.length; i++){
            if(i == nums.length || nums[i] == 0) {
                int r = i - 1;
                if(l != -1) {
                    if(nc % 2 == 0)max = Math.max(r - l + 1, max);
                    else {
                        max = Math.max(max
                                , Math.max(
                                        Math.max(first - l, r - first),
                                        Math.max(r - last, last - l)
                                )
                        );
                    }
                    l = -1;
                    first = -1;
                    last = -1;
                    nc = 0;
                }
            } else {
                if(l == -1)l = i;
                if(nums[i] < 0) {
                    nc++;
                    if(first == -1) {
                        first = i;
                        last = i;
                    } else {
                        last = i;
                    }
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L1567().getMaxLen(new int[]{9,10,1,0,19,20,-28,30,-12,20,11,-8,7,21,-26}));
    }
}
