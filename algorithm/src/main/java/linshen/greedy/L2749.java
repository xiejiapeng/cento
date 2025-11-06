package linshen.greedy;

/*
给你两个整数：num1 和 num2 。

在一步操作中，你需要从范围 [0, 60] 中选出一个整数 i ，并从 num1 减去 2^i + num2 。

请你计算，要想使 num1 等于 0 需要执行的最少操作数，并以整数形式返回。

如果无法使 num1 等于 0 ，返回 -1 。

提示：

1 <= num1 <= 109
-109 <= num2 <= 109

设 x 二进制表示中 1 的个数为 f(x)。要使当操作次数 k 成立，必须满足以下条件：

k≤x，这是 k 的上限。当 k>x 时，即使是 k 个 2
0
  也满足不了。
k≥f(x)，这是 k 的下限。我们至少需要 f(x) 个 2
i
  才能组成 x。当然可以多于 f(x) 个 2
i
 ，因为两个 2
i−1
  可以组成 2
i
 。
 */
public class L2749 {
    public int makeTheIntegerZero(int num1, int num2) {
        int k = 1;
        while (true) {
            long x = num1 - (long) num2 * k;
            if (x < k) {
                return -1;
            }
            //todo hhh 注意对一个数字进行2分解的时候，能够分解成功的充分必要条件为 bitcount <= k <= x
            if (k >= Long.bitCount(x)) {
                return k;
            }
            k++;
        }
    }
}
