package linshen.backtrace;

/*
给定一个大小为 n x m 的长方形，返回贴满矩形所需的整数边正方形的最小数量。
1 <= n, m <= 13
 */

public class L1240 {
    int ans = Integer.MAX_VALUE;
    public int tilingRectangle(int n, int m) {
        dfs(n, m, new int[m], 0, 0);
        return ans;
    }

    private void dfs(int n, int m, int[] h, int cur, int cnt) {
        if(cnt > ans)return;
        if(cur == h.length) {
            ans = Math.min(ans, cnt);
        } else {
            int diff = n - h[cur];
            int first = -1;
            for (int i = 1; i <= diff; i++){
                for (int j = cur; j < cur + i; j++) {
                    h[j] += i;
                    if(first == -1 && h[j] != n) {
                        first = j;
                    }
                }
                dfs(n, m, h, first, cnt + 1);
                for (int j = cur; j < cur + i; j++){
                    h[j] -= i;
                }
            }
        }
    }
}
