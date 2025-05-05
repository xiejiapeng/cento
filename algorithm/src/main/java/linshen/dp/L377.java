package linshen.dp;

/*
给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
请你从 nums 中找出并返回总和为 target 的元素组合的个数。
1 <= nums.length <= 200
1 <= nums[i] <= 1000
nums 中的所有元素 互不相同
1 <= target <= 1000
cn+1,3
 */

import java.util.Arrays;
//todo high

public class L377 {
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        int[] f = new int[target+1];
        for (int i = 0; i <= target; i++){
            if(i == 0)f[i] = 1;
            else{
                for (int x : nums){
                    if(x <= i)f[i] += f[i-x];
                }
            }
        }
        System.out.println(Arrays.toString(f));
        return f[target];
    }


    public static void main(String[] args) {
        /*
        4
        1 1 1 1
        1 3
        3 1

        3
        1 1 1
        3

        2
        1 1

        1
        1
         */
        System.out.println(new L377().combinationSum4(new int[]{1,3}, 4));
    }
}
