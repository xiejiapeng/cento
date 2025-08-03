package linshen.structure;

/*
给你一个字符串 s，请你对 s 的子串进行检测。
每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[right]，
并从中选择 最多 k 项替换成任何小写英文字母。

如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。

返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。

注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，如果 s[left..right] = "aaa" 且 k = 2，
我们只能替换其中的两个字母。（另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的）
提示：

1 <= s.length, queries.length <= 10^5
0 <= queries[i][0] <= queries[i][1] < s.length
0 <= queries[i][2] <= s.length
s 中只有小写英文字母
 */

import java.util.ArrayList;
import java.util.List;

public class L1177 {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] sum = new int[n+1][26];
        for (int i = 1; i <= n; i++){
            //todo h 记住要对j进行遍历，否则结果不对
            for (int j = 0; j < 26; j++){
                sum[i][s.charAt(i-1)-'a'] += sum[i-1][s.charAt(i-1)-'a'] + 1;
                sum[i][j] += sum[i-1][j] + (s.charAt(i-1)-'a'==j?1:0);
            }
        }
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++){
            int left = queries[i][0];
            int right = queries[i][1];
            int k = queries[i][2];
            int cnt = 0;
            for (int j = 0; j < 26; j++){
                int t = sum[right+1][j]-sum[left][j];
                if(t % 2 != 0)cnt++;
            }
            System.out.println(cnt);
            result.add(cnt / 2 <= k);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new L1177().canMakePaliQueries("abcda", new int[][]{{3,3,0}}));
    }
}
