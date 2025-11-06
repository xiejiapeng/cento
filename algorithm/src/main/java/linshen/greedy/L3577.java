package linshen.greedy;

/*
给你一个长度为 n 的数组 complexity。

在房间里有 n 台 上锁的 计算机，这些计算机的编号为 0 到 n - 1，每台计算机都有一个 唯一 的密码。编号为 i 的计算机的密码复杂度为 complexity[i]。

编号为 0 的计算机密码已经 解锁 ，并作为根节点。其他所有计算机必须通过它或其他已经解锁的计算机来解锁，具体规则如下：

可以使用编号为 j 的计算机的密码解锁编号为 i 的计算机，其中 j 是任何小于 i 的整数，且满足 complexity[j] < complexity[i]（即 j < i 并且 complexity[j] < complexity[i]）。
要解锁编号为 i 的计算机，你需要事先解锁一个编号为 j 的计算机，满足 j < i 并且 complexity[j] < complexity[i]。
求共有多少种 [0, 1, 2, ..., (n - 1)] 的排列方式，能够表示从编号为 0 的计算机（唯一初始解锁的计算机）开始解锁所有计算机的有效顺序。

由于答案可能很大，返回结果需要对 109 + 7 取余数。

注意：编号为 0 的计算机的密码已解锁，而 不是 排列中第一个位置的计算机密码已解锁。

排列 是一个数组中所有元素的重新排列。
0#***#***##****
 */

public class L3577 {
    /*
    todo hhh
    一开始就想了0#***#***##****这种一般情形，发现无法解决；还是要从特殊情况出发，才会发现#
    不可能只有这几个，而应该是所有！！！其实最终是个全排列
    这种问排列数的，目前除了用dfs和dp，还没有发现固定的套路，一般都是从找规律，答案对应的是一种很独特的情况，不可能
    通过枚举的方式全部梳理出来
     */
    int mod = (int)(1e9+7);
    public int countPermutations(int[] complexity) {
        int x = complexity[0];
        long t = 1;
        for (int i = 1; i < complexity.length; i++){
            if(complexity[i] <= x)return 0;
            else {
                t *= i;
                t %= mod;
            }
        }
        return (int)(t%mod);
    }
}
