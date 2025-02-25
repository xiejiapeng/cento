package sulwish;

import java.util.*;

public class L15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            if(i-1>0&&nums[i] == nums[i-1])continue; //todo 注意不能重复
            int target = 0-nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                if(nums[left] + nums[right] == target) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    int x = nums[left];
                    while(nums[left] == x && left < right) {
                        left++; //todo 注意不能重复，下标不能超过
                    }
                } else if(nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L15().threeSum(new int[]{0,0,0,0}));
    }
}
