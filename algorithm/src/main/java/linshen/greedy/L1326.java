package linshen.greedy;

/*
在 x 轴上有一个一维的花园。花园长度为 n，从点 0 开始，到点 n 结束。

花园里总共有 n + 1 个水龙头，分别位于 [0, 1, ..., n] 。

给你一个整数 n 和一个长度为 n + 1 的整数数组 ranges ，其中 ranges[i] （下标从 0 开始）表示：如果打开点 i 处的水龙头，可以灌溉的区域为 [i -  ranges[i], i + ranges[i]] 。

请你返回可以灌溉整个花园的 最少水龙头数目 。如果花园始终存在无法灌溉到的地方，请你返回 -1 。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L1326 {
    public int minTaps(int n, int[] ranges) {
        int[][] all = new int[n+1][2];
        for (int i = 0; i <= n; i++){
            int l = Math.max(0, i - ranges[i]);
            int r = Math.min(n, i + ranges[i]);
            all[i] = new int[]{l,r};
        }
        Arrays.sort(all, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> -o[1]));
        int j = 0;
        int last = -1;
        int cnt = 0;
        for (int i = 0; i <= n; i++){
            if(i > last) {
                //todo h 如果是第一个，左边只需要<=覆盖即可，否则一定要用<才算覆盖
                while (j <= n && ((i==0&&all[j][0] <= i) || (i!=0&&all[j][0] < i))) {
                    q.add(all[j]);
                    j++;
                }
                boolean find = false;
                while (!q.isEmpty()) {
                    int[] x = q.poll();
                    if(x[1] < i)continue;
                    else {
                        cnt++;
                        find = true;
                        last = x[1];
                        break;
                    }
                }
                if(!find)return -1;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L1326().minTaps(3, new int[]{0,0,0,0}));
    }
}
