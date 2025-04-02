package linshen.monoticstack;

/*
给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
由于答案可能很大，因此 返回答案模 10^9 + 7 。

todo 对于相同的数，假设左边的更小，能有效避免重复计算的问题
 */

import java.util.Arrays;
import java.util.Stack;

public class L907 {
    int mod = (int)(1e9+7);
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long[] left = new long[n];
        long[] right = new long[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i])stack.pop();
            if(stack.isEmpty())left[i] = -1;
            else left[i] = stack.peek();
            stack.add(i);
        }
        stack = new Stack<>();
        for (int i = n - 1; i > -1; i--){
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i])stack.pop();
            if(stack.isEmpty())right[i] = -1;
            else right[i] = stack.peek();
            stack.add(i);
        }
//        System.out.println(Arrays.toString(left));
//        System.out.println(Arrays.toString(right));
        long ans = 0;
        for (int i = 0; i < n; i++){
            long l = left[i];
            long r = right[i];
            if(l != -1 && r != -1) {
                ans += (i-l) * (r-i) * arr[i];
            } else if(r != -1) {
                ans += (i+1) * (r-i) * arr[i];
            } else if(l != -1) {
                ans += (i-l) * (n-i) * arr[i];
            } else {
                ans += (long) (i + 1) * (n-i) * arr[i];
            }
            ans %= mod;
        }
        return (int)(ans % mod);
    }

    public static void main(String[] args) {
        /*
        1,6,2,1
        [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]
        [3],
        [1],[3,1],[1,2],[3,1,2],[1,2,4],[3,1,2,4]
        [2],[2,4],
        [4],
         */
        System.out.println(new L907().sumSubarrayMins(new int[]{3,1,2,4}));
    }
}
