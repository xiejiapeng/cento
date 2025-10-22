package linshen.backtrace;

/*
给你一个由正整数组成的数组 nums 和一个 正 整数 k 。

如果 nums 的子集中，任意两个整数的绝对差均不等于 k ，则认为该子数组是一个 美丽 子集。

返回数组 nums 中 非空 且 美丽 的子集数目。

nums 的子集定义为：可以经由 nums 删除某些元素（也可能不删除）得到的一个数组。
只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。
 提示：

1 <= nums.length <= 18
1 <= nums[i], k <= 1000
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class L2597 {
    int cnt = 0;
    public int beautifulSubsets(int[] nums, int k) {
        dfs(nums, k, 0, new HashMap<>());
        return cnt;
    }

    private void dfs(int[] nums, int k, int cur, Map<Integer, Integer> all) {
        if(cur == nums.length) {
            if(!all.isEmpty())cnt++;
        } else {
            //choose cur
            if(!all.containsKey(nums[cur] + k) && !all.containsKey(nums[cur] - k)) {
                all.put(nums[cur], all.getOrDefault(nums[cur], 0) + 1);
                dfs(nums, k, cur + 1, all);
                all.put(nums[cur], all.get(nums[cur]) - 1);
                if(all.get(nums[cur]) == 0)all.remove(nums[cur]);
            }

            //skip
            dfs(nums, k, cur + 1, all);
        }
    }
}
