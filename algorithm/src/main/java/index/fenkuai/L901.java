package index.fenkuai;

/*
设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。

当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。

例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。

实现 StockSpanner 类：

StockSpanner() 初始化类对象。
int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
 */

import java.util.Stack;

public class L901 {
    class StockSpanner {
        Stack<int[]> stack;
        int day = 0;

        public StockSpanner() {
            stack = new Stack<>();

        }

        public int next(int price) {
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                stack.pop();
            }
            stack.add(new int[]{price, day});
            int ans;
            if (stack.isEmpty()) {
                ans = day + 1;
            } else {
                ans = day - stack.peek()[1];
            }
            day++;
            return ans;
        }
    }
}
