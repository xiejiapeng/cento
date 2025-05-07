package linshen.dp;

/*
一条街道上共有 n * 2 个 地块 ，街道的两侧各有 n 个地块。每一边的地块都按从 1 到 n 编号。
每个地块上都可以放置一所房子。

现要求街道同一侧不能存在两所房子相邻的情况，请你计算并返回放置房屋的方式数目。
由于答案可能很大，需要对 109 + 7 取余后再返回。

注意，如果一所房子放置在这条街某一侧上的第 i 个地块，不影响在另一侧的第 i 个地块放置房子。
 */

public class L2320 {
    int mod = (int)(1e9+7);
    public int countHousePlacements(int n) {
        long[] f = new long[n];
        f[n-1] = 2;
        for (int i = n - 2; i > -1; i--) {
            //put at i
            f[i] += (i+2 >= n ? 1 : f[i+2]);
            //skip i
            f[i] += f[i+1];
            f[i] %= mod;
        }
        return (int)(f[0] * f[0] % mod);
    }
}
