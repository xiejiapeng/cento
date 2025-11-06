package linshen.structure;

/*
给你一个整数数组 nums 和两个整数 k 和 p ，找出并返回满足要求的不同的子数组数，
要求子数组中最多 k 个可被 p 整除的元素。

如果满足下述条件之一，则认为数组 nums1 和 nums2 是 不同 数组：

两数组长度 不同 ，或者
存在 至少 一个下标 i 满足 nums1[i] != nums2[i] 。
子数组 定义为：数组中的连续元素组成的一个 非空 序列。
提示：

1 <= nums.length <= 200
1 <= nums[i], p <= 200
1 <= k <= nums.length
 */

import java.util.HashSet;
import java.util.Set;

public class L2261 {
    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length;
        int[] s = new int[n];
        for (int i = 0; i < n; i++){
            if(nums[i] % p == 0){
                s[i] = 1;
            } else {
                s[i] = 0;
            }
        }
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + s[i-1];
        }
        Set<String> a = new HashSet<>();
        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){
                if(sum[j+1]-sum[i] <= k){
                    StringBuilder sb = new StringBuilder();
                    for (int t = i; t <= j; t++){
                        sb.append("_");
                        sb.append(nums[t]);
                    }
                    a.add(sb.toString());
                }
            }
        }
        return a.size();
    }

    public static void main(String[] args) {
        System.out.println(new L2261().countDistinct(new int[]{2,3,3,2,2}, 2,2));
    }
}
