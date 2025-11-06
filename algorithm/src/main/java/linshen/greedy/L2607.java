package linshen.greedy;

/*
给你一个下标从 0 开始的整数数组 arr 和一个整数 k 。数组 arr 是一个循环数组。换句话说，
数组中的最后一个元素的下一个元素是数组中的第一个元素，数组中第一个元素的前一个元素是数组中的最后一个元素。

你可以执行下述运算任意次：

选中 arr 中任意一个元素，并使其值加上 1 或减去 1 。
执行运算使每个长度为 k 的 子数组 的元素总和都相等，返回所需要的最少运算次数。

子数组 是数组的一个连续部分。
1 3 5 7 9 11 13 15

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class L2607 {
    //todo hhhhh 首先要观察到（为什么我没有注意到。。），子数组相同=>a[i]=a[i+k]（这种公用区间的题目，根据相邻两个区间，可以观察到首尾的关系），所以要按mod分组；
    //虽然按模分组还是会有循环数组的情况，但根据裴蜀定理，这个循环数组的循环周期是gcd(n,k)，根据这个周期，这个循环数组是有限个数的
    //这样会将所有元素进行分组，然后应用中位数法即可！！！
    class Solution {
        public long makeSubKSumEqual(int[] arr, int k) {
            int n = arr.length;
            k = gcd(k, n);
            long ans = 0;
            for (int i = 0; i < k; ++i) {
                List<Integer> b = new ArrayList<Integer>();
                for (int j = i; j < n; j += k)
                    b.add(arr[j]);
                Collections.sort(b);
                int mid = b.get(b.size() / 2);
                for (int x : b)
                    ans += Math.abs(x - mid);
            }
            return ans;
        }

        private int gcd(int a, int b) {
            while (a != 0) {
                int tmp = a;
                a = b % a;
                b = tmp;
            }
            return b;
        }
    }

}
