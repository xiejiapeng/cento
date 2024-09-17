package sulwish;

/*
给你一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L740 {
    public static void main(String[] args) {
        System.out.println(new L740().deleteAndEarn(new int[]{1, 1, 3, 3, 3, 4, 6, 8, 8, 10}));
    }

    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int n = nums.length;
        Map<Integer, Integer> index = new HashMap<>();
        int[] ans = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i == 3) {
                System.out.println("h");
            }
            //delete this
            int toDelete = nums[i] - 1;
            index.putIfAbsent(nums[i], i);
            int sum = nums[i] * (i - index.get(nums[i]) + 1);

            if (index.containsKey(toDelete)) {
                int id = index.get(toDelete);
                if (id - 1 >= 0) ans[i] = Math.max(ans[i], sum + ans[id - 1]);
                else ans[i] = Math.max(ans[i], sum);
            } else {
                if (index.get(nums[i]) - 1 >= 0) ans[i] = Math.max(ans[i], sum + ans[index.get(nums[i]) - 1]);
                else ans[i] = Math.max(ans[i], sum);
            }

            //keep this
            if (i - 1 >= 0) {
                ans[i] = Math.max(ans[i], ans[i - 1]);
            }

            max = Math.max(max, ans[i]);
        }

        System.out.println(Arrays.toString(ans));
        return max;

    }
}
