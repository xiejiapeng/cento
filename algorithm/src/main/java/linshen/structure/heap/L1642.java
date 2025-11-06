package linshen.structure.heap;

/*
给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。

你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。

当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：

如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块
如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L1642 {
    public static void main(String[] args) {
        System.out.println(new L1642().furthestBuilding(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2));
    }

    //todo h 比较有意思的一题
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> -1 * o));
        int i = 0;
        for (; i < heights.length - 1; i++) {
            if (heights[i + 1] <= heights[i]) continue;
            else {
                int diff = heights[i + 1] - heights[i];
                if (bricks >= diff) {
                    queue.add(diff);
                    bricks -= diff;
                } else {
                    bricks -= diff;
                    queue.add(diff);
                    while (bricks < 0) {
                        if (ladders > 0) {
                            int t = queue.poll();
                            ladders -= 1;
                            bricks += t;
                        } else {
                            return i;
                        }
                    }
                }
            }
        }
        return i;
    }
}
