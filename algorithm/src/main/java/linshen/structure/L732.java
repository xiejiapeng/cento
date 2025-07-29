package linshen.structure;

/*
当 k 个日程存在一些非空交集时（即, k 个日程包含了一些相同时间），就会产生 k 次预订。

给你一些日程安排 [startTime, endTime) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。

实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。

MyCalendarThree() 初始化对象。
int book(int startTime, int endTime) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。

提示：

0 <= startTime < endTime <= 109
每个测试用例，调用 book 函数最多不超过 400次
 */

import java.util.Map;
import java.util.TreeMap;
//todo hh 注意是左闭右开区间，所以差分时不是用endTime+1，而是endTime

public class L732 {
    class MyCalendarThree {
        TreeMap<Integer, Integer> diff = new TreeMap<>();

        public MyCalendarThree() {
        }

        public int book(int startTime, int endTime) {
            diff.put(startTime, diff.getOrDefault(startTime, 0) + 1);
            diff.put(endTime, diff.getOrDefault(endTime, 0) - 1);

            int x = 0, max = 0;
            for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
                x += entry.getValue();
                max = Math.max(max, x);
            }
            return max;
        }
    }
}
