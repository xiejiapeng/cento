package index.dualpointer;

/*
给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。

k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：

0 <= i, j < nums.length
i != j
|nums[i] - nums[j]| == k
注意，|val| 表示 val 的绝对值。
 */

import java.util.Arrays;

public class L532 {
    public int findPairs(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, j = 0; i < n; i++){
            if(i+1<n&&nums[i] == nums[i+1])continue;
            else{
                while (j < i && nums[j] < nums[i] - k){
                    j++;
                }
                if(j != i && nums[j] == nums[i] - k)ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L532().findPairs(new int[]{1,3,1,5,4}, 0));
    }
}
