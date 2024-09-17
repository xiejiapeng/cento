package sulwish;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。

k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：

0 <= i, j < nums.length
i != j
|nums[i] - nums[j]| == k
注意，|val| 表示 val 的绝对值。
 */
public class L532 {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        Set<List<Integer>> all = new HashSet<>();
        for (int left = -1, right = 0; right < nums.length; right++){
            while (left + 1 < right && nums[left + 1] <= nums[right] - k) {
                left++;
            }
            if(left >= 0 && nums[left] == nums[right] - k)all.add(Arrays.asList(nums[left],nums[right]));
        }
        return all.size();
    }

    public static void main(String[] args) {
        System.out.println(new L532().findPairs(new int[]{1,2,3,4,5}, 1));
    }
}
