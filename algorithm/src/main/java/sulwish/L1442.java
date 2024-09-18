package sulwish;

/*
给你一个整数数组 arr 。

现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。

a 和 b 定义如下：

a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
注意：^ 表示 按位异或 操作。

请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。


 */

import java.util.*;

public class L1442 {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] sum = new int[n+1];
        Map<Integer, List<Integer>> see = new HashMap<>();
        see.put(0, new ArrayList<>());
        see.get(0).add(0);

        int ans = 0;
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i-1] ^ arr[i-1];
            if(see.containsKey(sum[i])) {
                List<Integer> keys = see.get(sum[i]);
                for (int x : keys) {
                    ans += count(x, i-1);
                }
            }

            see.putIfAbsent(sum[i], new ArrayList<>());
            see.get(sum[i]).add(i);
        }
        System.out.println(Arrays.toString(sum));
        System.out.println(see);
        return ans;
    }

    public int count(int a, int b) {
        return b - a;
    }

    public static void main(String[] args) {
        System.out.println(new L1442().countTriplets(new int[]{2,3,1,6,7}));
    }
}
