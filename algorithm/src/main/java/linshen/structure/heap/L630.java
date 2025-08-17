package linshen.structure.heap;

/*
这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会
持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。

你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。

返回你最多可以修读的课程数目。

提示:

1 <= courses.length <= 104
1 <= durationi, lastDayi <= 104
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L630 {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1])); // 按截止时间排
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a); // 最大堆，存duration
        int total = 0;

        for (int[] c : courses) {
            int duration = c[0], lastDay = c[1];
            total += duration;
            queue.add(duration);

            //todo hhhhh 注意思考，为什么减掉一次就能满足lastDay的要求
            if (total > lastDay) { // 超时了
                total -= queue.poll(); // 去掉最耗时的
            }
        }
        return queue.size();
    }
}
