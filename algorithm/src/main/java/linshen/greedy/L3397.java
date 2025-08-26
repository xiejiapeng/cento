package linshen.greedy;

/*
给你一个整数数组 nums 和一个整数 k。

你可以对数组中的每个元素 最多 执行 一次 以下操作：

将一个在范围 [-k, k] 内的整数加到该元素上。
返回执行这些操作后，nums 中可能拥有的不同元素的 最大 数量。
 */


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class L3397 {
    //todo hh 在右侧进行松弛
    public int maxDistinctElements(int[] nums, int k) {
       int n = nums.length;
       Arrays.sort(nums);
       k *= 2;
       int rmax = Integer.MAX_VALUE;
       //[nums[i], nums[i] + k]
       for (int i = n - 1; i > -1; i--){
           if(rmax - 1 >= nums[i])nums[i] = Math.min(nums[i] + k, rmax - 1);
           rmax = nums[i];
       }
       Set<Integer> all = new HashSet<>();
       for (int x : nums) {
           all.add(x);
       }
       return all.size();
    }

    public static void main(String[] args) {
        System.out.println(new L3397().maxDistinctElements(new int[]{4,4,4,4}, 1));
    }
}
