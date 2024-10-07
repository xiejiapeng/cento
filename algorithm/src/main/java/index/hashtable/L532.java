package index.hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class L532 {
    public int findPairs(int[] nums, int k) {
        Set<Integer> see = new HashSet<>();
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i - 1 > -1 && nums[i] == nums[i - 1]) continue;
            if (k == 0 && i + 1 < n && nums[i] == nums[i + 1]) ans++;

            int target = nums[i] - k;
            if (see.contains(target)) ans++;
            see.add(nums[i]);
        }
        return ans;
    }
}
