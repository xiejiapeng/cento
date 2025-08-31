package linshen.greedy;

import java.util.HashSet;
import java.util.Set;

/*
给你一个下标从 0 开始的字符串 hamsters ，其中 hamsters[i] 要么是：

'H' 表示有一个仓鼠在下标 i ，或者
'.' 表示下标 i 是空的。
你将要在空的位置上添加一定数量的食物桶来喂养仓鼠。如果仓鼠的左边或右边至少有一个食物桶，就可以喂食它。
更正式地说，如果你在位置 i - 1 或者 i + 1 放置一个食物桶，就可以喂养位置为 i 处的仓鼠。

在 空的位置 放置食物桶以喂养所有仓鼠的前提下，请你返回需要的 最少 食物桶数。如果无解请返回 -1 。
 */
public class L2086 {
    public int minimumBuckets(String hamsters) {
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < hamsters.length(); i++) {
            if (hamsters.charAt(i) == 'H') {
                if (ans.contains(i - 1)) continue;
                else {
                    if (i + 1 < hamsters.length() && hamsters.charAt(i + 1) == '.') ans.add(i + 1);
                    else if (i - 1 > -1 && hamsters.charAt(i - 1) == '.') ans.add(i - 1);
                    else return -1;
                }
            }
        }
        return ans.size();
    }
}
