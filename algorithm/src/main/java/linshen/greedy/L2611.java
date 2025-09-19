package linshen.greedy;

/*
有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。

下标为 i 处的奶酪被吃掉的得分为：

如果第一只老鼠吃掉，则得分为 reward1[i] 。
如果第二只老鼠吃掉，则得分为 reward2[i] 。
给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。

请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。
 */

import java.util.PriorityQueue;

public class L2611 {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        PriorityQueue<Integer> diff = new PriorityQueue<>();
        int total = 0;
        for (int i = 0; i < n; i++){
            total += reward2[i];
            diff.add(reward2[i] - reward1[i]);
        }
        while (k-->0) {
            int x = diff.poll();
            total -= x;
        }
        return total;
    }
}
