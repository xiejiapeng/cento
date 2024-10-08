package index.dualpointer;

/*
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。

1 2 3 4 5 6 7 8 9
1 2 3 7 5 8 4 6 9

2 3 1 4 5 6 7 8 9

1 <= nums.length <= 104
-105 <= nums[i] <= 105
 */

public class L581 {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int small = -1;
        for (int i = 0; i < n; i++){
            if(i-1 > -1 && nums[i] < nums[i-1]) {
                if(small == -1 || nums[i] < nums[small])small = i;
            }
        }
        int large = n;
        for (int i = n - 1; i > -1; i--){
            if(i+1<n && nums[i] > nums[i+1]) {
                if(large == n || nums[i] > nums[large])large = i;
            }
        }

        if(small == -1)return 0;
        int left = -1;
        int right = n;
        for (int i = 0; i < n; i++){
            if(nums[i] > nums[small]) {
                left = i;
                break;
            }
        }
        for (int i = n - 1; i > -1; i--){
            if(nums[i] < nums[large]){
                right = i;
                break;
            }
        }
        return right - left + 1;
    }

    public static void main(String[] args) {
        System.out.println(new L581().findUnsortedSubarray(new int[]{1,2,3,4}));
    }
}
