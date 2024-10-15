package index.seqdp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
给你一个整数数组 nums ，你可以对它进行一些操作。
1 2 3 4 5

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，
你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 104
 */
public class L740 {
    public int deleteAndEarn(int[] nums) {
        Map<Integer,Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        int n = cnt.size();
        int[] ns = new int[n];
        int t = 0;
        for (int x : cnt.keySet()) {
            ns[t++] = x;
        }
        Arrays.sort(ns);
        //f[i][0]: [0,i] & select i, f[i][1]: [0,i] & skip i
        int[][] f = new int[n][2];
        for (int i = 0; i < n; i++) {
            if(i == 0) {
                f[i][0] = ns[i] * cnt.get(ns[i]);
                f[i][1] = 0;
            } else {
                if(ns[i-1] == ns[i] - 1) {
                    f[i][0] = ns[i] * cnt.get(ns[i]) + (i-2>=0?Math.max(f[i-2][0], f[i-2][1]):0);
                    f[i][1] = Math.max(f[i-1][0], f[i-1][1]);
                } else {
                    f[i][0] = ns[i] * cnt.get(ns[i]) + Math.max(f[i-1][0], f[i-1][1]);
                    f[i][1] = Math.max(f[i-1][0], f[i-1][1]);
                }
            }
        }
        return Math.max(f[n-1][0], f[n-1][1]);
    }
}
