package linshen.dp;

/*
如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

n >= 3
对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回 0 。

（回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素
（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

提示：
3 <= arr.length <= 1000
1 <= arr[i] < arr[i + 1] <= 10^9
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L873 {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++){
            dp[i] = new HashMap<>();
        }
        for (int i = n - 2; i > -1; i--) {
            for (int j = i + 1; j < n; j++){
                int target = arr[i] + arr[j];
                if(dp[j].containsKey(target)) {
                    int x = dp[j].get(target);
                    dp[i].put(arr[j], 1 + x);
                } else {
                    dp[i].put(arr[j], 2);
                }
            }
        }
        return Arrays.stream(dp).map(d -> d.values().stream().filter(x -> x > 2).mapToInt(i -> i).max().orElse(0))
                .mapToInt(i -> i).max().orElse(0);
    }

    public static void main(String[] args) {
        System.out.println(new L873().lenLongestFibSubseq(new int[]{1,2,5,6,11,13,15,17,28}));
    }
}
