package linshen.greedy;

/*
给你一个二维数组 queries，其中 queries[i] 形式为 [l, r]。每个 queries[i] 表示了一个元素范围从 l 到 r
（包括 l 和 r ）的整数数组 nums 。
[0-3] 1 * 4 -- 2
[4,15] 2 * 12 -- 3
[16,63] 3 * 48
[64,255] 4 * 192
在一次操作中，你可以：

选择一个查询数组中的两个整数 a 和 b。
将它们替换为 floor(a / 4) 和 floor(b / 4)。
你的任务是确定对于每个查询，将数组中的所有元素都变为零的 最少 操作次数。返回所有查询结果的总和。

提示：

1 <= queries.length <= 105
queries[i].length == 2
queries[i] == [l, r]
1 <= l < r <= 109
2 3 4 5 6(8)
0 3 1 5 6(6)
0 0 1 1 6(4)
0 0 0 1 1(2)
0 0 0 0 0(0)
 */

public class L3495 {
    //todo h 看不出贪心样子，就是纯代码题
    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] query : queries) {
            long l = query[0];
            long r = query[1];
            long lx = end(l);
            long rx = end(r);
            long cnt = 0;
            if(lx == rx) {
                cnt = (r - l + 1) * lx;
            } else {
                long t = (long)(Math.pow(4, lx));
                long s = (long)(Math.pow(4, rx));
                for (long i = lx + 1; i < rx; i++) {
                    long x = (long)(Math.pow(4, i));
                    cnt += (i * (x - x / 4));
                }
                cnt += lx * (t - l);
                cnt += rx * (r - s / 4 + 1);
            }
            if(cnt % 2 == 0){
                ans += cnt / 2;
            } else {
                ans += (cnt / 2 + 1);
            }

        }
        return ans;
    }

    private long end(long x) {
        long u = 4;
        int c = 1;
        while (u <= x) {
            u *= 4;
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new L3495().minOperations(new int[][]{{1,2},{2,4}}));
    }
}
