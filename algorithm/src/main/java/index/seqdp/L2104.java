package index.seqdp;

/*
给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。

返回 nums 中 所有 子数组范围的 和 。

子数组是数组中一个连续 非空 的元素序列。
 */

import java.util.Arrays;
import java.util.Stack;

public class L2104 {
    public static void main(String[] args) {
        System.out.println(new L2104().subArrayRanges(new int[]{1, 3, 3}));
    }

    public long subArrayRanges(int[] x) {
        int n = x.length;
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (long) x[i];
        }
        int[] left1 = new int[n]; //left[i]表示i左表第一个比其大的下标，可能为-1
        int[] right1 = new int[n];//right[i]表示i右表第一个比其大的下标，可能为n

        Arrays.fill(left1, -1);
        Arrays.fill(right1, n);

        Stack<Integer> ls1 = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!ls1.isEmpty() && nums[ls1.peek()] <= nums[i]) {
                ls1.pop();
            }
            if (!ls1.isEmpty()) {
                left1[i] = ls1.peek();
            }
            ls1.add(i);
        }

        Stack<Integer> rs1 = new Stack<>();
        for (int i = n - 1; i > -1; i--) {
            while (!rs1.isEmpty() && nums[rs1.peek()] < nums[i]) {
                rs1.pop();
            }
            if (!rs1.isEmpty()) {
                right1[i] = rs1.peek();
            }
            rs1.add(i);
        }

//        System.out.println(Arrays.toString(left1));
//        System.out.println(Arrays.toString(right1));


        int[] left2 = new int[n]; //left[i]表示i左表第一个比其小的下标，可能为-1
        int[] right2 = new int[n];//right[i]表示i右表第一个比其小的下标，可能为n

        Arrays.fill(left2, -1);
        Arrays.fill(right2, n);

        Stack<Integer> ls2 = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!ls2.isEmpty() && nums[ls2.peek()] > nums[i]) {
                ls2.pop();
            }
            if (!ls2.isEmpty()) {
                left2[i] = ls2.peek();
            }
            ls2.add(i);
        }

        Stack<Integer> rs2 = new Stack<>();
        for (int i = n - 1; i > -1; i--) {
            while (!rs2.isEmpty() && nums[rs2.peek()] >= nums[i]) {
                rs2.pop();
            }
            if (!rs2.isEmpty()) {
                right2[i] = rs2.peek();
            }
            rs2.add(i);
        }

//        System.out.println(Arrays.toString(left2));
//        System.out.println(Arrays.toString(right2));

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int left = left1[i];
            int right = right1[i];
            long len;

            len = (long) (i - left) * (long) (right - i);

            ans += len * (long) nums[i];
        }

        System.out.println(ans);

        for (int i = 0; i < n; i++) {
            int left = left2[i];
            int right = right2[i];
            long len;
            len = (long) (right - i) * (long) (i - left);
            ans -= len * (long) nums[i];
        }

        System.out.println(ans);

        return (int) (ans);


    }
}
