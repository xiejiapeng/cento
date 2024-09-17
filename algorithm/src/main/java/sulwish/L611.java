package sulwish;

/*
给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 */

import java.util.Arrays;

public class L611 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int right = 2; right < nums.length; right++){
            int r = right - 1;
            for (int l = 0; l < right - 1; l++){
                if(nums[r] <= nums[right] - nums[l])continue;
                else {
                    if(r <= l){
                        ans += (right - l - 1);
                    } else {
                        while (r - 1 > l && nums[r-1] > nums[right] - nums[l])r--;
                        ans += right - r;
                    }
                }
            }

        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println(new L611().triangleNumber(new int[]{2,2,3,4}));
        System.out.println(new L611().triangleNumber(new int[]{2,2,3,4}));
    }
}
