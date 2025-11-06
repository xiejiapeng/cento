package linshen.slide;

/*
给你一个由正整数组成的数组 nums 。
如果数组中的某个子数组满足下述条件，则称之为 完全子数组 ：

子数组中 不同 元素的数目等于整个数组不同元素的数目。
返回数组中 完全子数组 的数目。

子数组 是数组中的一个连续非空序列。

提示：

1 <= nums.length <= 1000
1 <= nums[i] <= 2000
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L2799 {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> all = new HashSet<>();
        for (int x : nums) {
            all.add(x);
        }
        int a = all.size();
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int left = 0, right = 0; right < n; right++){
            cnt.put(nums[right], cnt.getOrDefault(nums[right], 0) + 1);
            while (cnt.keySet().size() >= a) {
                //注意while条件为> 或者 >= ，但是可以移动left，因为left的个数大于1
                if(cnt.keySet().size() > a || cnt.get(nums[left]) > 1) {
                    cnt.put(nums[left], cnt.get(nums[left]) - 1);
                    if(cnt.get(nums[left]) == 0)cnt.remove(nums[left]);
                    left++;
                } else {
                    //记得退出循环的条件，如果没有更新，一定要手动退出，防止死循环
                    break;
                }

            }
            if(cnt.keySet().size() == a) {
                ans += (left + 1);
            }
        }
        return ans;
    }
}
