package linshen.zhizhen;

/*
给你一个二进制字符串 s 。你可以按任意顺序执行以下两种操作任意次：

类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。
类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。
请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。

我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。


 */

public class L1888 {
    public int minFlips(String s) {
        int n = s.length();
        int ans = Integer.MAX_VALUE;
        char[] sa = "01".toCharArray();
        int cnt = 0;
        //todo h: 把这种移动当成环，如果实在不知道什么情况是最优的，就枚举。滑动窗口能帮忙优化并减少枚举过程中的开销
        for (int i = 0; i < n * 2; i++){
            if(s.charAt(i%n) == sa[i%2]) {
                cnt++;
            }
            if(i < n - 1)continue;
            ans = Math.min(ans, Math.min(cnt, n - cnt));
            if(s.charAt((i-n+1) % n) == sa[(i-n+1)%2]) {
                cnt--;
            }
        }
        return ans;
    }
}
