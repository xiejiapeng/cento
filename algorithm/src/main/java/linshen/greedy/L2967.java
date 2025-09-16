package linshen.greedy;

/*
给你一个长度为 n 下标从 0 开始的整数数组 nums 。

你可以对 nums 执行特殊操作 任意次 （也可以 0 次）。每一次特殊操作中，你需要 按顺序 执行以下步骤：

从范围 [0, n - 1] 里选择一个下标 i 和一个 正 整数 x 。
将 |nums[i] - x| 添加到总代价里。
将 nums[i] 变为 x 。
如果一个正整数正着读和反着读都相同，那么我们称这个数是 回文数 。
比方说，121 ，2552 和 65756 都是回文数，但是 24 ，46 ，235 都不是回文数。

如果一个数组中的所有元素都等于一个整数 y ，且 y 是一个小于 10^9 的 回文数 ，那么我们称这个数组是一个 等数数组 。

请你返回一个整数，表示执行任意次特殊操作后使 nums 成为 等数数组 的 最小 总代价。
        15578a5b1
            - a > 7 => 155797551
            - a < 7 => 1557875b1
        15579a5b1
提示：

1 <= n <= 105
1 <= nums[i] <= 109
 */

import java.util.Arrays;

public class L2967 {
    //todo hhh 这一题掌握得到整数x附近最大，最小的回文数的方法
    public long minimumCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int mid = nums[n/2];
        int large = larger(mid);
        int small = smaller(mid);
        long lc = cost(nums, large);
        long sc = cost(nums, small);
        return Math.min(lc, sc);
    }

    public long cost(int[] nums, int x) {
        long c = 0;
        for (int y : nums) {
            c += (Math.abs(y-x));
        }
        return c;
    }

    private int larger(int x) {
        String s = String.valueOf(x);
        int len = s.length();
        if(len % 2 == 0) {
            String t = s.substring(0, len / 2);
            String r = new StringBuilder(t).reverse().toString();
            String ne = t + r;
            if(Integer.parseInt(ne) >= x)return Integer.parseInt(ne);
            else {
                t = String.valueOf(Integer.parseInt(t) + 1);
                r = new StringBuilder(t).reverse().toString();
                ne = t + r;
                return Integer.parseInt(ne);
            }
        } else {
            String t = s.substring(0, len / 2);
            String r = new StringBuilder(t).reverse().toString();
            String ne = t + (s.charAt(len/2)) + r;
            if(Integer.parseInt(ne) >= x)return Integer.parseInt(ne);
            else {
                t = String.valueOf(Integer.parseInt(t + (s.charAt(len/2))) + 1);
                r = new StringBuilder(t.substring(0, t.length()-1)).reverse().toString();
                ne = t + r;
                return Integer.parseInt(ne);
            }
        }
    }

    private int smaller(int x) {
        String s = String.valueOf(x);
        int len = s.length();
        if (len % 2 == 0) {
            String t = s.substring(0, len / 2);
            String r = new StringBuilder(t).reverse().toString();
            String ne = t + r;
            if (Integer.parseInt(ne) <= x) return Integer.parseInt(ne);
            else {
                int tInt = Integer.parseInt(t) - 1;
                String tNew = String.valueOf(tInt);
                // 如果减完长度缩短了，比如 1000 -> 99...9
                if (tNew.equals("0") || tNew.length() < t.length()) {
                    // 返回全 9 的回文，长度比原数少 1
                    StringBuilder nine = new StringBuilder();
                    for (int i = 0; i < len - 1; i++) nine.append('9');
                    return Integer.parseInt(nine.toString());
                }
                r = new StringBuilder(tNew).reverse().toString();
                ne = tNew + r;
                return Integer.parseInt(ne);
            }
        } else {
            String t = s.substring(0, len / 2);
            String r = new StringBuilder(t).reverse().toString();
            String ne = t + (s.charAt(len / 2)) + r;
            if (Integer.parseInt(ne) <= x) return Integer.parseInt(ne);
            else {
                int midInt = Integer.parseInt(t + s.charAt(len / 2)) - 1;
                String midStr = String.valueOf(midInt);
                // 如果长度缩短，比如 100 -> 99
                if (midStr.equals("0") || midStr.length() < t.length() + 1) {
                    StringBuilder nine = new StringBuilder();
                    for (int i = 0; i < len - 1; i++) nine.append('9');
                    return Integer.parseInt(nine.toString());
                }
                String newT = midStr.substring(0, midStr.length() - 1);
                char newMid = midStr.charAt(midStr.length() - 1);
                r = new StringBuilder(newT).reverse().toString();
                ne = newT + newMid + r;
                return Integer.parseInt(ne);
            }
        }
    }


    public static void main(String[] args) {
        /*
        1001 => 101 + 2
        999 => 99 + 2
         */
        System.out.println(new L2967().minimumCost(new int[]{9,10,10}));
    }

}
