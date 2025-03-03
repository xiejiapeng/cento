package linshen.zhizhen;

/*
几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。

每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。

你的点数就是你拿到手中的所有卡牌的点数之和。

给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 */
public class L1423 {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        k = n - k;
        int allSum = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < n; right++) {
            allSum += cardPoints[right];
            sum += cardPoints[right];
            int left = right - k + 1;
            if(left - 1 >= 0) {
                sum -= cardPoints[left-1];
            }
            if(left >= 0) {
                min = Math.min(min, sum);
            }
        }
        return allSum - min;
    }
}
