package linshen.structure.heap;

import java.util.*;

/*
给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。

你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。
提示：

m == mat.length
n == mat.length[i]
1 <= m, n <= 40
1 <= k <= min(200, n ^ m)
1 <= mat[i][j] <= 5000
mat[i] 是一个非递减数组

 */
public class L1439 {
    public static void main(String[] args) {
        System.out.println(new L1439().kthSmallest(new int[][]{{1, 3, 11}, {2, 4, 6}}, 9));
    }

    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[] x = new int[m + 1];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            x[i] = 0;
            sum += mat[i][0];
        }
        x[m] = sum;
        int ans = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[m]));
        Set<String> see = new HashSet<>();
        //todo hh set对数组无效，需要转化为string
        see.add(Arrays.toString(x));
        queue.add(x);
        while (k > 0) {
            int[] t = queue.poll();
            System.out.println(Arrays.toString(t));
            ans = t[m];
            for (int i = 0; i < m; i++) {
                int[] u = t.clone();
                int s = t[i];
                if (s + 1 < n) {
                    u[i] = s + 1;
                    u[m] = t[m] + (mat[i][u[i]] - mat[i][t[i]]);
                    if (!see.contains(Arrays.toString(u))) {
                        queue.add(u);
                        see.add(Arrays.toString(u));
                    }
                }

            }
            k--;
        }
        return ans;
    }
}
