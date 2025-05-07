package linshen.dp;
/*
给你一个下标从 0 开始的二维整数数组 questions ，其中 questions[i] = [pointsi, brainpoweri] 。

这个数组表示一场考试里的一系列题目，你需要 按顺序 （也就是从问题 0 开始依次解决），针对每个问题选择 解决 或者 跳过 操作。解决问题 i 将让你 获得 pointsi 的分数，但是你将 无法 解决接下来的 brainpoweri 个问题（即只能跳过接下来的 brainpoweri 个问题）。如果你跳过问题 i ，你可以对下一个问题决定使用哪种操作。

请你返回这场考试里你能获得的 最高 分数。
 */

public class L2140 {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] f = new long[n];
        f[n-1] = questions[n-1][0];
        for (int i = n - 2; i > -1; i--){
            //choose i
            long a = questions[i][0] + (i+questions[i][1]+1 < n ? f[i+questions[i][1]+1] : 0);
            long b = f[i+1];
            f[i] = Math.max(a, b);
        }
        return f[0];
    }
}
