package sulwish;

/*
给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。

生成的测试用例保证结果符合 32-bit 整数范围。
 */

import java.util.Arrays;
import java.util.Stack;

public class L795 {
    public static void main(String[] args) {
        System.out.println(new L795().numSubarrayBoundedMax(new int[]{3, 3}, 2, 3));
    }

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
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

        System.out.println(Arrays.toString(l));
        System.out.println(Arrays.toString(r));

        int ans = 0;
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
            System.out.println("cur is " + i + ", ll=" + ll + ",rr=" + rr);
            if (nums[i] >= left && nums[i] <= right) ans += ll * rr;

        }
        return ans;
    }
}
