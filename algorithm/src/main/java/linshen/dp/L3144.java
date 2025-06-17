package linshen.dp;

/*
给你一个字符串 s ，你需要将它分割成一个或者更多的 平衡 子字符串。比方说，s == "ababcc"
那么 ("abab", "c", "c") ，("ab", "abc", "c") 和 ("ababcc") 都是合法分割，
但是 ("a", "bab", "cc") ，("aba", "bc", "c") 和 ("ab", "abcc") 不是，不平衡的子字符串用粗体表示。

请你返回 s 最少 能分割成多少个平衡子字符串。

注意：一个 平衡 字符串指的是字符串中所有字符出现的次数都相同。
提示：

1 <= s.length <= 1000
s 只包含小写英文字母。
 */

public class L3144 {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        for (int i = n - 1; i > -1; i--) {
            dp[i] = (n - i);
            int[] c = new int[26];
            for (int j = i; j < n; j++) {
                c[s.charAt(j)-'a']++;
                if(valid(c))dp[i] = Math.min(dp[i], 1 + dp[j+1]);
            }
        }
        return dp[0];
    }

    boolean valid(int[] a) {
        int t = 0;
        for (int i = 0; i < a.length; i++){
            if(a[i] != 0) {
                if(t == 0)t = a[i];
                else if(t != a[i])return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L3144().minimumSubstringsInPartition("abaccddb"));
    }
}
