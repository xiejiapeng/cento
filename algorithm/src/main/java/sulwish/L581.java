package sulwish;

/*
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。
 */


import java.util.Arrays;
import java.util.TreeMap;

public class L581 {
    public int findUnsortedSubarray(int[] nums) {
        int[] x = nums.clone();
        Arrays.sort(x);
        int left = -1;
        int right = nums.length;
        for (int i = 0; i < nums.length; i++){
            if(nums[i] != x[i]){
                left = i;
                break;
            }
        }

        for (int i = nums.length - 1; i > -1; i--){
            if(nums[i] != x[i]){
                right = i;
                break;
            }
        }

        if(left == -1 || right == -1)return 0;
        else return right - left + 1;
    }
}
