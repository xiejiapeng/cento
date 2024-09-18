package sulwish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
answer[i] % answer[j] == 0 ，或
answer[j] % answer[i] == 0
如果存在多个有效解子集，返回其中任何一个均可。
 */

public class L368 {
    public static void main(String[] args) {
        System.out.println(new L368().largestDivisibleSubset(new int[]{3, 4, 16, 8}));
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        List<Integer>[] ans = new List[n];

        for (int i = 0; i < n; i++) {
            ans[i] = new ArrayList<>();

        }
        ans[0].add(nums[0]);
        int maxAns = 1;
        List<Integer> aans = ans[0];

        for (int i = 1; i < n; i++) {
            ans[i].add(nums[i]);
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && 1 + ans[j].size() > max) {
                    max = ans[j].size();
                    ans[i] = new ArrayList<>();
                    ans[i].addAll(ans[j]);
                    ans[i].add(nums[i]);
                }
            }

            if (ans[i].size() > maxAns) {
                maxAns = ans[i].size();
                aans = ans[i];
            }
        }

        return aans;

    }
}
