package index.fenkuai;

/*
实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。

当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。

日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。

实现 MyCalendar 类：

MyCalendar() 初始化日历对象。
boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 */

import java.util.Stack;

public class L729 {
    public static void main(String[] args) {
        MyCalendar m = new MyCalendar();
        System.out.println(m.book(47, 50));
        System.out.println(m.book(33, 41));
        System.out.println(m.book(39, 45));
        System.out.println(m.book(33, 42));
    }

    static class MyCalendar {
        Stack<int[]> stack = new Stack<>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            Stack<int[]> other = new Stack<>();
            if (stack.isEmpty()) {
                stack.add(new int[]{start, end});
                return true;
            } else {
                while (!stack.isEmpty() && stack.peek()[0] >= start) {
                    other.add(stack.pop());
                }

                if (stack.isEmpty() || stack.peek()[1] <= start) {
                    if (other.isEmpty() || other.peek()[0] >= end) {
                        stack.add(new int[]{start, end});
                        while (!other.isEmpty()) {
                            stack.add(other.pop());
                        }
                        return true;
                    }
                }
            }
            while (!other.isEmpty()) {
                stack.add(other.pop());
            }
            return false;
        }
    }
}
