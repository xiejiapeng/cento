package linshen.zhizhen;

/*
给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 */

import java.util.Arrays;

public class L1358 {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] lastSee = new int[3];
        Arrays.fill(lastSee, -1);
        int ans = 0;
        for (int i = 0; i < s.length(); i++){
            lastSee[s.charAt(i) - 'a'] = i;
            if(Arrays.stream(lastSee).allMatch(p -> p != -1)) {
                int left = Arrays.stream(lastSee).min().getAsInt();
                ans += (left+1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L1358().numberOfSubstrings("aaacb"));
    }
}
