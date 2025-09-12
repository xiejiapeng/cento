package linshen.greedy;

/*
给你一个正整数 p 。你有一个下标从 1 开始的数组 nums ，这个数组包含范围 [1, 2^p - 1]
 内所有整数的二进制形式（两端都 包含）。你可以进行以下操作 任意 次：

从 nums 中选择两个元素 x 和 y 。
选择 x 中的一位与 y 对应位置的位交换。对应位置指的是两个整数 相同位置 的二进制位。
比方说，如果 x = 1101 且 y = 0011 ，交换右边数起第 2 位后，我们得到 x = 1111 和 y = 0001 。

请你算出进行以上操作 任意次 以后，nums 能得到的 最小非零 乘积。将乘积对 109 + 7 取余 后返回。

注意：答案应为取余 之前 的最小值。
1 2 3 4 5 6 7 8 9 10 11 12  13 14 15

1111
0001

0100
0010

t -> (2^t-1)*(2^t)/2
1 <= p <= 60
 */

public class L1969 {
    //todo 要求最小，不是最大，越小的越需要缩小
    class Solution {
        public int minNonZeroProduct(int p) {
            if (p == 1) {
                return 1;
            }
            long mod = 1000000007;
            long x = fastPow(2, p, mod) - 1;
            long y = (long) 1 << (p - 1);
            return (int) (fastPow(x - 1, y - 1, mod) * x % mod);
        }

        public long fastPow(long x, long n, long mod) {
            long res = 1;
            for (; n != 0; n >>= 1) {
                if ((n & 1) != 0) {
                    res = res * x % mod;
                }
                x = x * x % mod;
            }
            return res;
        }
    }

}
