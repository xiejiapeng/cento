package linshen.dp;

/*
你整数 zero ，one ，low 和 high ，我们从空字符串开始构造一个字符串，每一步执行下面操作中的一种：

将 '0' 在字符串末尾添加 zero 次。
将 '1' 在字符串末尾添加 one 次。
以上操作可以执行任意次。

如果通过以上过程得到一个 长度 在 low 和 high 之间（包含上下边界）的字符串，那么这个字符串我们称为 好 字符串。

请你返回满足以上要求的 不同 好字符串数目。由于答案可能很大，请将结果对 109 + 7 取余 后返回。
1 <= low <= high <= 105
1 <= zero, one <= low
 */

public class L2466 {
    //todo h 区间类题目可以统一为从0到终点的模型，最后处理中间结果
    int mod = (int)(1e9+7);
    public int countGoodStrings(int low, int high, int zero, int one) {
        //number of strings with len = i
        long[] f = new long[100001];
        for (int i = 1; i <= 100000; i++){
            if(i >= zero) f[i] += i==zero?1:f[i-zero];
            if(i >= one) f[i] += i==one?1:f[i-one];
            f[i] %= mod;
        }
        long ans = 0;
        for (int i = low; i <= high; i++){
            ans += f[i];
            ans %= mod;
        }
        return (int)(ans % mod);
    }
}
