package sulwish;
/*
这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。

给定三个整数 n、k 和 target，请返回投掷骰子的所有可能得到的结果（共有 kn 种方式），使得骰子面朝上的数字总和等于 target。

由于答案可能很大，你需要对 109 + 7 取模。
 */

public class L1155 {
    int mod = (int) (1e9 + 7);

    public int numRollsToTarget(int n, int k, int target) {
        long[][] f = new long[n][target + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {

                if (i == 0) {
                    if (j >= 1 && j <= k) {
                        f[i][j] = 1;
                    } else {
                        f[i][j] = 0;
                    }
                } else if (j == 0) {
                    f[i][j] = 0;
                } else {
                    for (int t = 1; t <= k; t++) {
                        if (j >= t) {
                            f[i][j] += f[i - 1][j - t];
                        }
                    }
                }
                f[i][j] %= mod;

            }
        }

        return (int) (f[n - 1][target] % mod);
    }
}
