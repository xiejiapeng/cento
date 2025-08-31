package linshen.greedy;

/*
给你一个整数数组 nums 。

一个正整数 x 的任何一个 严格小于 x 的 正 因子都被称为 x 的 真因数 。比方说 2 是 4 的 真因数，但 6 不是 6 的 真因数。

你可以对 nums 的任何数字做任意次 操作 ，一次 操作 中，你可以选择 nums 中的任意一个元素，将它除以它的 最大真因数 。

你的目标是将数组变为 非递减 的，请你返回达成这一目标需要的 最少操作 次数。

如果 无法 将数组变成非递减的，请你返回 -1 。

提示：

1 <= nums.length <= 105
1 <= nums[i] <= 106
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L3226 {
    static int N = 1000005;
    static int[] lpf = new int[N];
    static {
        for (int i = 2; i < N; i++){
            if(lpf[i] == 0) {
                for (int j = i; j < N; j += i) {
                    if(lpf[j] == 0)lpf[j] = i;
                }
            }
        }
    }
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = n - 2; i > -1; i--) {
            if(nums[i] > nums[i+1]) {
                int t = lpf[nums[i]];
                if(t > nums[i+1])return -1;
                else nums[i] = t;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L3226().minOperations(new int[]{7,7,6}));
    }
}
