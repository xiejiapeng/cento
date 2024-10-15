package index.seqdp;

/*
对于一个整数数组 nums，逆序对是一对满足 0 <= i < j < nums.length 且 nums[i] > nums[j]的整数对 [i, j] 。

给你两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个 逆序对 的不同的数组的个数。
由于答案可能很大，只需要返回对 109 + 7 取余的结果。

1 <= n <= 1000
0 <= k <= 1000
 */

public class L629 {
    int mod = (int)(1e9+7);
    public int kInversePairs(int n, int k) {
        long[][] f = new long[n+1][k+1];
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= k; j++){
                if(j == 0)f[i][j] = 1;
                else {
                    for (int t = 1; t <= i; t++) {
                        //todo use prefix sum algorithm
                        f[i][j] += j-i+t<0?0:f[i-1][j-(i-t)];
                        f[i][j] %= mod;
                    }
                }

            }
        }
        return (int)(f[n][k] % mod);
    }

    public static void main(String[] args) {
        //1 2 3, 1 3 2, 2 1 3, 2 3 1, 3 1 2, 3 2 1
        System.out.println(new L629().kInversePairs(45,67));
    }
}
