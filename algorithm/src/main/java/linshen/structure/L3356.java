package linshen.structure;

/*
给你一个长度为 n 的整数数组 nums 和一个二维数组 queries，其中 queries[i] = [li, ri, vali]。

每个 queries[i] 表示在 nums 上执行以下操作：

将 nums 中 [li, ri] 范围内的每个下标对应元素的值 最多 减少 vali。
每个下标的减少的数值可以独立选择。
零数组 是指所有元素都等于 0 的数组。

返回 k 可以取到的 最小非负 值，使得在 顺序 处理前 k 个查询后，nums 变成 零数组。如果不存在这样的 k，则返回 -1。

提示：

1 <= nums.length <= 105
0 <= nums[i] <= 5 * 105
1 <= queries.length <= 105
queries[i].length == 3
0 <= li <= ri < nums.length
1 <= vali <= 5
todo hh 二分与差分的经典结合
 */

public class L3356 {
    public static void main(String[] args) {
        System.out.println(new L3356().minZeroArray(new int[]{7, 6, 8}, new int[][]{{0, 0, 2}, {0, 1, 5}, {2, 2, 5}, {0, 2, 4}}));
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        boolean zero = true;
        for (int x : nums) {
            if (x != 0) zero = false;
        }
        if (zero) return 0;
        int x = find(nums, queries, 0, queries.length - 1);
        if (x == -1) return -1;
        else return 1 + x;
    }

    private int find(int[] nums, int[][] queries, int left, int right) {
        if (left == right) {
            if (valid(nums, queries, left)) return left;
            else return -1;
        } else if (left == right - 1) {
            if (valid(nums, queries, left)) return left;
            else if (valid(nums, queries, right)) return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if (valid(nums, queries, mid)) {
                return find(nums, queries, left, mid);
            } else {
                return find(nums, queries, mid + 1, right);
            }
        }
    }

    private boolean valid(int[] nums, int[][] queries, int t) {
        int n = nums.length;
        int[] diff = new int[n + 2];
        for (int i = 0; i <= t; i++) {
            int[] q = queries[i];
            int l = q[0];
            int r = q[1];
            int v = q[2];
            diff[l] += v;
            diff[r + 1] -= v;
        }
        int x = 0;
        for (int i = 0; i < n; i++) {
            x += diff[i];
            if (x < nums[i]) return false;
        }
        return true;
    }
}
