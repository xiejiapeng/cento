package index.prefix;

import java.util.ArrayList;
import java.util.List;

/*
你和朋友们准备去野炊。给你一个下标从 0 开始的整数数组 security ，其中 security[i] 是第 i 天的建议出行指数。
日子从 0 开始编号。同时给你一个整数 time 。

如果第 i 天满足以下所有条件，我们称它为一个适合野炊的日子：

第 i 天前和后都分别至少有 time 天。
第 i 天前连续 time 天建议出行指数都是非递增的。
第 i 天后连续 time 天建议出行指数都是非递减的。
更正式的，第 i 天是一个适合野炊的日子当且仅当：security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].

请你返回一个数组，包含 所有 适合野炊的日子（下标从 0 开始）。返回的日子可以 任意 顺序排列。

1 <= security.length <= 105
0 <= security[i], time <= 105
 */

public class L2100 {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++){
            if(security[i] <= security[i-1]){
                left[i] = left[i-1] + 1;
            } else {
                left[i] = 1;
            }
        }
        right[n-1] = 1;
        for (int i = n - 2; i > -1; i--){
            if(security[i] <= security[i+1]) {
                right[i] = right[i+1] + 1;
            } else {
                right[i] = 1;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if(left[i] >= time + 1 && right[i] >= time+1) {
                ans.add(i);
            }
        }
        return ans;
    }
}
