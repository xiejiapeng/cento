package linshen.greedy;

/*
给你一个下标从 0 开始长度为 n 的整数数组 stations ，其中 stations[i] 表示第 i 座城市的供电站数目。

每个供电站可以在一定 范围 内给所有城市提供电力。换句话说，如果给定的范围是 r ，
在城市 i 处的供电站可以给所有满足 |i - j| <= r 且 0 <= i, j <= n - 1 的城市 j 供电。

|x| 表示 x 的 绝对值 。比方说，|7 - 5| = 2 ，|3 - 10| = 7 。
一座城市的 电量 是所有能给它供电的供电站数目。

政府批准了可以额外建造 k 座供电站，你需要决定这些供电站分别应该建在哪里，这些供电站与已经存在的供电站有相同的供电范围。

给你两个整数 r 和 k ，如果以最优策略建造额外的发电站，返回所有城市中，最小电量的最大值是多少。

这 k 座供电站可以建在多个城市。
 */

public class L2528 {
    //todo hhhhhh 一道超级题，minmax记住用二分+valid函数；然后包含了前缀和，差分，贪心等各种思路
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] sum = new long[n+1];
        long[] s = new long[n];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + stations[i-1];
        }
        long max = -1;
        for (int i = 0; i < n; i++){
            int l = Math.max(0, i - r);
            int ri = Math.min(n-1, i + r);
            s[i] = sum[ri+1] - sum[l];
            max = Math.max(max, s[i]);
        }
        return find(s, r, k, 0, max+k);
    }

    private long find(long[] stations, int r, int k, long left, long right) {
        if(left == right) return left;
        else if(left == right - 1) {
            if(valid(stations, r, k, right))return right;
            else return left;
        } else {
            long mid = (left + right) / 2;
            if(valid(stations, r, k, mid)) return find(stations, r, k, mid, right);
            else return find(stations, r, k, left, mid - 1);
        }
    }

    private boolean valid(long[] stations, int r, int k, long x) {
        int n = stations.length;
        long[] diff = new long[n+1];
        long t = 0;
        for(int i = 0; i < n; i++) {
            t += diff[i];
            long y = stations[i] + t;
            if(y < x) {
                long gap = x - y;
                if(k < gap)return false;
                else {
                    k -= gap;
                    int pos = Math.min(n - 1, i + r);
                    int start = Math.max(0, pos - r);
                    int end = Math.min(n, pos + r + 1);

                    diff[start] += gap;
                    t += gap;
                    diff[end] -= gap;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L2528().maxPower(new int[]{4,2}, 1, 1));
    }
}
