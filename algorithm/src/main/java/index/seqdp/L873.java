package index.seqdp;

/*
如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

n >= 3
对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。

（回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），
而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

3 <= arr.length <= 1000
1 <= arr[i] < arr[i + 1] <= 10^9
 */

import java.util.HashMap;
import java.util.Map;

public class L873 {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int max = 0;
        Map<Integer,Integer>[] f = new HashMap[n];
        for (int i = 0; i < n; i++) {
            f[i] = new HashMap<>();
            if(i < 2)continue;
            else if(i == 2) {
                if(arr[0] + arr[1] == arr[2]) {
                    f[i].put(arr[i-1], 3);
                    max = Math.max(max, 3);
                }
            } else {
                for (int j = i - 1; j > -1; j--) {
                    int target = arr[i] - arr[j];
                    if(f[j].containsKey(target)) {
                        int len = f[j].get(target);
                        f[i].put(arr[j], Math.max(f[i].getOrDefault(arr[j], 0), len+1));
                        max = Math.max(max, len+1);
                    }
                    for (int k = j - 1; k > -1; k--) {
                        if(arr[k] + arr[j] == arr[i]) {
                            f[i].put(arr[j], Math.max(f[i].getOrDefault(arr[j], 0), 3));
                            max = Math.max(max, 3);
                        }
                    }
                }
            }
        }
        return max;
    }
}
