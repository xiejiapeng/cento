package index.binary;

/*
给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。


 */

import java.util.Arrays;

public class L611 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            if(nums[i] == 0)continue;
            int k = i + 2;
            for (int j = i + 1; j < nums.length; j++){
                while (k < nums.length && nums[k] < (nums[i] + nums[j])) {
                    k++;
                }
                if(k == nums.length || nums[k] >= (nums[i] + nums[j])){
                    ans += Math.max(0,(k - j - 1));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L611().triangleNumber(new int[]{7,0,0,0}));
    }
}
