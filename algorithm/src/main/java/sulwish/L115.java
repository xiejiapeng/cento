package sulwish;

/*
给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。
 */

public class L115 {
    int mod = (int)(1e9+7);
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        long[][] dp = new long[n+1][m+1];
        for (int i = 0; i <= n; i++){
            dp[i][m] = 1;
        }
        for (int i = n - 1; i > -1; i--){
            for (int j = m - 1; j > -1; j--){
                if(s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i+1][j+1];
                    dp[i][j] %= mod;
                }
                dp[i][j] += dp[i+1][j];
                dp[i][j] %= mod;
            }
        }
        return (int)(dp[0][0] % mod);
    }

    public static void main(String[] args) {
        System.out.println(new L115().numDistinct("aa","a"));
    }
}
