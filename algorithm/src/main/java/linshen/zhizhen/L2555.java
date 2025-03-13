package linshen.zhizhen;

/*
在 X轴 上有一些奖品。给你一个整数数组 prizePositions ，它按照 非递减 顺序排列，其中 prizePositions[i] 是第 i 件奖品的位置。数轴上一个位置可能会有多件奖品。再给你一个整数 k 。

你可以同时选择两个端点为整数的线段。每个线段的长度都必须是 k 。你可以获得位置在任一线段上的所有奖品（包括线段的两个端点）。注意，两个线段可能会有相交。

比方说 k = 2 ，你可以选择线段 [1, 3] 和 [2, 4] ，你可以获得满足 1 <= prizePositions[i] <= 3 或者 2 <= prizePositions[i] <= 4 的所有奖品 i 。
请你返回在选择两个最优线段的前提下，可以获得的 最多 奖品数目。
 */

import java.util.Comparator;
import java.util.PriorityQueue;

//todo h: not finished
public class L2555 {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] prize = new int[n];
        for (int right = n - 1, left = n - 1; right > -1; right--) {
            if(right + 1 < n && prizePositions[right] == prizePositions[right+1]) {
                prize[right] = prize[right+1];
            } else {
                while (left-1>-1&&(prizePositions[right]-prizePositions[left-1]+1<=k)) {
                    left=left-1;
                }
                prize[right] = right - left + 1;
            }
        }
        int[] canmax = new int[n];
        for (int i = 0; i < n; i++){
            if(i == 0)canmax[i] = prize[i];
            else {
                canmax[i] = Math.max(canmax[i-1], prize[i]);
            }
        }
        int max = 0;
        for (int left = 0, right = 0; right < n; right++) {
            if(right+1 < n && prizePositions[right] == prizePositions[right+1])continue;
            else {
                while (left-1>-1&&(prizePositions[right]-prizePositions[left-1]+1<=k)) {
                    left=left-1;
                }

            }
            int cm = canmax[left-1];
            max = Math.max(max, cm + (right - left + 1));
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L2555().maximizeWin(new int[]{1,1,2,2,3,3,5}, 2));
    }
}
