package sulwish;
/*
给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。

返回 nums 中 所有 子数组范围的 和 。

子数组是数组中一个连续 非空 的元素序列。
 */

import java.util.Stack;

public class L2104 {
    public long subArrayRanges(int[] nums) {
        long a = max(nums);
        long b = min(nums);
        System.out.println("a=" + a + ",b=" + b);
        return a - b;
    }

    public long min(int[] nums) {
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
        return ans;

    }

    public long max(int[] nums) {
        int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];

        Stack<Integer> ls = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!ls.isEmpty() && nums[ls.peek()] < nums[i]) {
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
            while (!rs.isEmpty() && nums[rs.peek()] <= nums[i]) {
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
        return ans;

    }
}
