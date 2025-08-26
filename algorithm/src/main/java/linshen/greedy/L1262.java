package linshen.greedy;

/*
给你一个整数数组 nums，请你找出并返回能被三整除的元素 最大和。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L1262 {
    public int maxSumDivThree(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        List<Integer> m1 = new ArrayList<>();
        List<Integer> m2 = new ArrayList<>();
        for (int num : nums) {
            if (num % 3 == 1 && m1.size() < 2) m1.add(num);
            if (num % 3 == 2 && m2.size() < 2) m2.add(num);
        }
        int rdx = sum % 3;
        if(rdx == 0)return sum;
        else if(rdx == 1) {
            int ans = Integer.MIN_VALUE;
            if(!m1.isEmpty()) {
                ans = Math.max(ans, sum - m1.get(0));
            }
            if(m2.size() == 2) {
                ans = Math.max(ans, sum - m2.get(0) - m2.get(1));
            }
            return ans;
        } else {
            int ans = Integer.MIN_VALUE;
            if(m1.size() == 2) {
                ans = Math.max(ans, sum - m1.get(0) - m1.get(1));
            }
            if(!m2.isEmpty()) {
                ans = Math.max(ans, sum - m2.get(0));
            }
            return ans;
        }
    }
}
