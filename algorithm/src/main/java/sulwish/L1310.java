package sulwish;

/*
有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。

对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。

并返回一个包含给定查询 queries 所有结果的数组。
x ^ 0 = x
x ^ x = 0
 */

public class L1310 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xor = new int[n+1];
        for (int i = 1; i <= n; i++){
            xor[i] = xor[i-1] ^ arr[i-1];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++){
            int left = queries[i][0];
            int right = queries[i][1];
            ans[i] = xor[right+1] ^ xor[left];
        }
        return ans;
    }
}
