package linshen.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
你一个字符串 s ，一个字符 互不相同 的字符串 chars 和一个长度与 chars 相同的整数数组 vals 。

子字符串的开销 是一个子字符串中所有字符对应价值之和。空字符串的开销是 0 。
请你返回字符串 s 的所有子字符串中的最大开销。
 */
public class L2606 {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        Map<Character, Integer> pos = new HashMap<>();
        for (int i = 0; i < chars.length(); i++){
            pos.put(chars.charAt(i), vals[i]);
        }
        int[] vs = new int[s.length()];
        for (int i = 0; i < s.length(); i++){
            if(pos.containsKey(s.charAt(i)))vs[i] = pos.get(s.charAt(i));
            else vs[i] = s.charAt(i) - 'a' + 1;
        }
        System.out.println(Arrays.toString(vs));
        return maxSubArray(vs);
    }
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        int min = 0;
        int ans = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++){
            ans = Math.max(ans, s[i] - min);
            min = Math.min(min, s[i]);
        }
        return ans;
    }
}
