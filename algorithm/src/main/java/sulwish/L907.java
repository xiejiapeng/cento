package sulwish;

/*
给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。

由于答案可能很大，因此 返回答案模 10^9 + 7 。
 */

import java.util.Stack;

public class L907 {
    int mod = (int) (1e9 + 7);

    public int sumSubarrayMins(int[] nums) {
        int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];

        Stack<Integer> ls = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!ls.isEmpty() && nums[ls.peek()] > nums[i]) {
                ls.pop();
            }
            if (ls.isEmpty()) {
                l[i] = -1;
            } else {
                l[i] = ls.peek();
            }
            ls.add(i);
        }

        Stack<Integer> rs = new Stack<>();
        for (int i = n - 1; i > -1; i--) {
            while (!rs.isEmpty() && nums[rs.peek()] >= nums[i]) {
                rs.pop();
            }
            if (rs.isEmpty()) {
                r[i] = -1;
            } else {
                r[i] = rs.peek();
            }
            rs.add(i);
        }

//        System.out.println(Arrays.toString(l));
//        System.out.println(Arrays.toString(r));

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int ll = 0;
            int rr = 0;
            if (l[i] == -1) {
                ll = i + 1;
            } else {
                ll = i - l[i];
            }

            if (r[i] == -1) {
                rr = n - i;
            } else {
                rr = r[i] - i;
            }
//            System.out.println("cur is " + i + ", ll="+ll+",rr="+rr);
            ans += ((long) nums[i]) * (ll * rr);

        }
        return (int) (ans % mod);

    }
}
