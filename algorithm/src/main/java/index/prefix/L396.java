package index.prefix;

/*
给定一个长度为 n 的整数数组 nums 。

假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：

F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
返回 F(0), F(1), ..., F(n-1)中的最大值 。

n-k,...,n-1,0,1,...,n-k-1
f(k)=f(k-1)+(sum-nums[n-k)-(n-1)*nums[n-k]
=f(k-1)+sum-nums-n*nums+nums
=f(k-1)+sum-n*nums[n-k]

生成的测试用例让答案符合 32 位 整数。

n == nums.length
1 <= n <= 105
-100 <= nums[i] <= 100

0a0+...+(n-1)a(n-1)
0a(n-1)+1a0+(n-1)a(n-2)=f(0)+(a0+...+a(n-2))-(n-1)a(n-1)
=f(0)+a0+...+a(n-2)-na(n-1)+a(n-1)

 */

import java.util.Arrays;

public class L396 {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int s = 0;
        for (int i = 0; i < n; i++){
            s += i * nums[i];
        }
        int max = s;
        for (int k = 1; k < n; k++){
            s = s + sum - n * nums[n-k];
            max = Math.max(max, s);
        }
        return max;
    }
}
