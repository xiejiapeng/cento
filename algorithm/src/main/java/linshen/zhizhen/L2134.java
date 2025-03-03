package linshen.zhizhen;

/*
交换 定义为选中一个数组中的两个 互不相同 的位置并交换二者的值。

环形 数组是一个数组，可以认为 第一个 元素和 最后一个 元素 相邻 。

给你一个 二进制环形 数组 nums ，返回在 任意位置 将数组中的所有 1 聚集在一起需要的最少交换次数。


 */

//todo 记住为什么可以转化为固定的滑动窗口
public class L2134 {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] == 1)k++;
        }
        int max = 0;
        int sum = 0;
        for (int right = 0; right - k + 1 < n; right++) {
            int left = right - k + 1;
            sum += (nums[right % n] == 1 ? 1 : 0);
            if(left - 1 > -1) {
                sum -= (nums[left - 1] == 1 ? 1 : 0);
            }
            if(left >= 0) {
                max = Math.max(max, sum);
            }
        }
        return k - max;
    }
}
