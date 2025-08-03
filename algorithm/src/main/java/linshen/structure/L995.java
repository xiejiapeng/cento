package linshen.structure;

/*
给定一个二进制数组 nums 和一个整数 k 。

k位翻转 就是从 nums 中选择一个长度为 k 的 子数组 ，同时把子数组中的每一个 0 都改成 1 ，把子数组中的每一个 1 都改成 0 。

返回数组中不存在 0 所需的最小 k位翻转 次数。如果不可能，则返回 -1 。

子数组 是数组的 连续 部分。

1 翻转偶数次
0 翻转奇数次
 */

public class L995 {
    public static void main(String[] args) {
        System.out.println(new L995().minKBitFlips(new int[]{0, 1, 1}, 3));
    }

    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] diff = new int[n * 2 + 2];
        int x = 0;
        int c = 0;
        for (int i = 0; i < n; i++) {
            x += diff[i];
            if ((nums[i] == 1 && x % 2 == 0) || (nums[i] == 0 && x % 2 == 1)) continue;
            else {
                if (n - i < k) return -1;
                c++;
                x++;
                diff[i] += 1;
                diff[i + k] -= 1;
            }
        }
        return c;
    }
}
