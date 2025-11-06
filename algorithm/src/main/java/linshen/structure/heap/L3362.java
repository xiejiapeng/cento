package linshen.structure.heap;

/*
给你一个长度为 n 的整数数组 nums 和一个二维数组 queries ，其中 queries[i] = [li, ri] 。

每一个 queries[i] 表示对于 nums 的以下操作：

将 nums 中下标在范围 [li, ri] 之间的每一个元素 最多 减少 1 。
坐标范围内每一个元素减少的值相互 独立 。
零数组 指的是一个数组里所有元素都等于 0 。

请你返回 最多 可以从 queries 中删除多少个元素，使得 queries 中剩下的元素仍然能将 nums 变为一个 零数组 。
如果无法将 nums 变为一个 零数组 ，返回 -1 。
提示：

1 <= nums.length <= 105
0 <= nums[i] <= 105
1 <= queries.length <= 105
queries[i].length == 2
0 <= li <= ri < nums.length
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L3362 {
    //todo hhhhh 中等题分数之最！有一个套路，在做这种覆盖相关的贪心的时候，遍历的不是query，而是原数组；需要提前将query进行某种排序；比如871，需要被完成的课程可以类比于
    //这里需要被覆盖的数组；当然，有点牵强，还是记住这道题比较好吧
    public int maxRemoval(int[] nums, int[][] queries) {
        PriorityQueue<int[]> idle = new PriorityQueue<>(Comparator.comparingInt(o -> -o[1]));
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < queries.length; i++){
            busy.add(queries[i]);
        }
        int n = nums.length;
        int[] diff = new int[n+1];
        int s = 0;
        for (int i = 0; i < nums.length; i++){
            s += diff[i];
            while (!busy.isEmpty() && busy.peek()[0] <= i) {
                idle.add(busy.poll());
            }
            int acc = s;
            while (acc < nums[i]) {
                if(idle.isEmpty()) {
                    return -1;
                } else {
                    int[] id = idle.poll();
                    if(id[1] >= i){
                        diff[id[0]]++;
                        s++;
                        diff[id[1]+1]--;
                        acc += 1;
                    }

                }
            }
        }
        return busy.size() + idle.size();
    }

    public static void main(String[] args) {
        System.out.println(new L3362().maxRemoval(new int[]{0,0,3}, new int[][]{{0,2},{1,1},{0,0},{0,0}}));
    }
}
