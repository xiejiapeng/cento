package linshen.structure;

/*
给你一个长度为 n 的整数数组 nums ，n 是 偶数 ，同时给你一个整数 k 。

你可以对数组进行一些操作。每次操作中，你可以将数组中 任一 元素替换为 0 到 k 之间的 任一 整数。

执行完所有操作以后，你需要确保最后得到的数组满足以下条件：

存在一个整数 X ，满足对于所有的 (0 <= i < n) 都有 abs(a[i] - a[n - i - 1]) = X 。
请你返回满足以上条件 最少 修改次数。

提示：

2 <= n == nums.length <= 105
n 是偶数。
0 <= nums[i] <= k <= 105
 */

public class L3224 {
    //todo hhhhh 未能解决的题目，记住吧
    public int minChanges(int[] nums, int k) {
        int[] cnt = new int[k + 1];
        int[] cnt2 = new int[k + 1];
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int p = nums[i];
            int q = nums[n - 1 - i];
            if (p > q) { // 保证 p <= q
                int tmp = p;
                p = q;
                q = tmp;
            }
            cnt[q - p]++;
            cnt2[Math.max(q, k - p)]++;
        }

        int ans = n;
        int sum2 = 0; // 统计有多少对 (p,q) 都要改
        for (int x = 0; x <= k; x++) {
            // 其他 n/2-cnt[x] 对 (p,q) 至少要改一个数，在此基础上，有额外的 sum2 对 (p,q) 还要再改一个数
            ans = Math.min(ans, n / 2 - cnt[x] + sum2);
            // 对于后面的更大的 x，当前的这 cnt2[x] 对 (p,q) 都要改
            sum2 += cnt2[x];
        }
        return ans;
    }


}
