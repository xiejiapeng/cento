package linshen.greedy;

/*
给你一个长度为 n 的整数数组 pizzas，其中 pizzas[i] 表示第 i 个披萨的重量。
每天你会吃 恰好 4 个披萨。由于你的新陈代谢能力惊人，当你吃重量为 W、X、Y 和 Z 的披萨
（其中 W <= X <= Y <= Z）时，你只会增加 1 个披萨的重量！体重增加规则如下：

在 奇数天（按 1 开始计数）你会增加 Z 的重量。
在 偶数天，你会增加 Y 的重量。
请你设计吃掉 所有 披萨的最优方案，并计算你可以增加的 最大 总重量。

注意：保证 n 是 4 的倍数，并且每个披萨只吃一次。
 */

import java.util.Arrays;

public class L3457 {
    public long maxWeight(int[] pizzas) {
        Arrays.sort(pizzas);
        int n = pizzas.length;
        int bucket = n / 4;
        long all = 0;
        int cur = 0;
        int cur2 = 1;
        int cnt = 0;
        for (int i = n - 1; i > -1; i--){
            if(cur < bucket) {
                all += pizzas[i];
                cur += 2;
                cnt++;
            } else {
                all += pizzas[i-1];
                i -= 1;
                cur2++;
                cnt++;
            }
            if(cnt == bucket)break;
        }
        return all;

    }
}
