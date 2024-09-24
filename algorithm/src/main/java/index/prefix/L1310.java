package index.prefix;

/*
有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。

对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。

并返回一个包含给定查询 queries 所有结果的数组。
 */

public class L1310 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] sum = new int[n+1];
        for (int i = 0; i < n; i++){
            sum[i+1] = sum[i] ^ arr[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++){
            ans[i] = sum[queries[i][1] + 1] ^ sum[queries[i][0]];
        }
        return ans;
    }
}
