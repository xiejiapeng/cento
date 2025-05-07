package linshen.dp;

/*
给你两个 正 整数 n 和 x 。

请你返回将 n 表示成一些 互不相同 正整数的 x 次幂之和的方案数。换句话说，
你需要返回互不相同整数 [n1, n2, ..., nk] 的集合数目，满足 n = n1x + n2x + ... + nkx 。

由于答案可能非常大，请你将它对 109 + 7 取余后返回。

比方说，n = 160 且 x = 3 ，一个表示 n 的方法是 n = 23 + 33 + 53 。

提示：

1 <= n <= 300
1 <= x <= 5
 */

public class L2787 {
    int mod = (int)(1e9 + 7);
    public int numberOfWays(int n, int x) {
        long[][] dp = new long[n+1][n+1];
        long max = 0;
        /*
            [1,2,...,i]
            j
         */
        //todo h 不要太追求一开始就定义好边界条件，多用if没有问题
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                //choose i
                if((int)(Math.pow(i, x)) <= j){
                    if((int)(Math.pow(i, x)) == j)dp[i][j] += 1;
                    else dp[i][j] += dp[i-1][j-(int)(Math.pow(i, x))];
                }
                //skip i
                if(i-1!=0)dp[i][j] += dp[i-1][j];
                if(j == n){
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return (int)(max % mod);
    }

    public static void main(String[] args) {
        System.out.println(new L2787().numberOfWays(10, 2));
    }
}
