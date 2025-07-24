package linshen.structure;

/*
给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。

如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
提示：

1 <= matrix.length <= 100
1 <= matrix[0].length <= 100
-1000 <= matrix[i][j] <= 1000
-10^8 <= target <= 10^8
 */

import java.util.HashMap;
import java.util.Map;

public class L1074 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] s = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if(i == 0){
                    s[i][j] = matrix[i][j];
                } else {
                    s[i][j] = s[i-1][j] + matrix[i][j];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){
                int[] sum = new int[m+1];
                Map<Integer, Integer> see = new HashMap<>();
                see.put(0, 1);
                for (int k = 1; k <= m; k++){
                    int val;
                    if(i == 0){
                        val = s[j][k-1];
                    } else {
                        val = s[j][k-1] - s[i-1][k-1];
                    }
                    sum[k] = sum[k-1] + val;
                    ans += see.getOrDefault(sum[k] - target, 0);
                    see.put(sum[k], see.getOrDefault(sum[k], 0) + 1);
                }
            }
        }
        return ans;
    }
}
