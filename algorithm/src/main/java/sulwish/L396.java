package sulwish;

/*
给定一个长度为 n 的整数数组 nums 。

假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：

F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
返回 F(0), F(1), ..., F(n-1)中的最大值 。

f(0)=
0*a0+1*a1+...+(n-2)a[n-2]+(n-1)a[n-1]

f(1)=
0*a[n-1]+1*a[0]+...+(n-1)a[n-2]

f(1)-f(0)=a0+...+a[n-2]+a[n-1] - n*a[n-1]


生成的测试用例让答案符合 32 位 整数。
 */

import java.util.Arrays;

public class L396 {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int f = 0;
        for (int i = 0; i < n; i++){
            f += i * nums[i];
        }
        int ans = f;
        for (int i = 1; i < n; i++){
            int cur = f + sum - n * nums[n-i];
            ans = Math.max(cur, ans);
            f = cur;
        }
        return ans;
    }
}
