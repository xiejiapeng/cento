package linshen.greedy;

/*
这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，
其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，
并且必须在不晚于 lastDayi 的时候完成。

你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。

返回你最多可以修读的课程数目。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L630 {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));
        int last = 0;
        int cnt = 0;
        PriorityQueue<Integer> du = new PriorityQueue<>(Comparator.comparingInt(o -> -courses[o][0]));
        for (int i = 0; i < courses.length; i++){
            if(last + courses[i][0] <= courses[i][1]) {
                last += courses[i][0];
                cnt++;
                du.add(i);
            } else {
                if(!du.isEmpty()) {
                    int x = du.peek();
                    if(last - courses[x][0] + courses[i][0] < courses[i][1] && (courses[x][0] > courses[i][0])) {
                        du.poll();
                        du.add(i);
                        last = last - courses[x][0] + courses[i][0];
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L630().scheduleCourse(new int[][]{{7,17},{3,12},{10,20},{9,10},{5,20},{10,19},{4,18}}));
    }
}
