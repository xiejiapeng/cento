package sulwish;

/*
对于一个整数数组 nums，逆序对是一对满足 0 <= i < j < nums.length 且 nums[i] > nums[j]的整数对 [i, j] 。

给你两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个 逆序对 的不同的数组的个数。由于答案可能很大，只需要返回对 109 + 7 取余的结果。
 */

public class L629 {
    int mod = (int) (1e9 + 7);

    public static void main(String[] args) {
        System.out.println(new L629().kInversePairs(17, 16));
    }

    public int kInversePairs(int n, int k) {
        int[][] ans = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {

                if (j == 0) ans[i][j] = 1;
                else {
                    if (j > i * (i - 1) / 2) ans[i][j] = 0;
                    else {
                        for (int t = 0; t < i; t++) {
                            if (i - t - 1 <= j) {

                                ans[i][j] += ans[i - 1][j - (i - t - 1)];
                                ans[i][j] %= mod;
                            }

                        }
                    }
                }
//                System.out.println("ans["+i+"]["+j+"]="+ans[i][j]);
            }
        }
        return ans[n][k];

    }
}
