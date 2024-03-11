package sulwish;

/*
给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 质数 组成，且其中所有整数互不相同。

对于每对满足 0 <= i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。

那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。

a b c d

最小 a/d
如果 a/c < b/d

o1[0]/o1[1] < o2[0]/o2[1]
=> o1[0] * o2[1] < o2[0] * o1[1]
 */

import java.util.*;

public class L786 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> all = new PriorityQueue<>((o1, o2) -> Integer.compare(arr[o1[0]] * arr[o2[1]], arr[o2[0]] * arr[o1[1]]));
        for (int i = 0; i < n; i++){
            all.add(new int[]{i,n-1});
        }
        while (k-- >= 0) {
            int[] x = all.poll();
            System.out.println(Arrays.toString(x));
            if(k == 0)return new int[]{arr[x[0]], arr[x[1]]};
            if(x[1] - 1 >= 0) all.add(new int[]{x[0], x[1] - 1});
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L786().kthSmallestPrimeFraction(new int[]{1,7,23,29,47}, 8)));
    }
}
