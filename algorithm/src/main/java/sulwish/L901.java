package sulwish;

/*
设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。

当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。

例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。

实现 StockSpanner 类：

StockSpanner() 初始化类对象。
int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L901 {
    public static void main(String[] args) {
        StockSpanner s = new L901().new StockSpanner();
        System.out.println(s.next(100));
        System.out.println(s.next(80));
    }

    class StockSpanner {
        Stack<Integer> stack;
        Map<Integer, Integer> all;
        int cur = -1;

        public StockSpanner() {
            stack = new Stack<>();
            all = new HashMap<>();
        }

        public int next(int price) {
            cur++;
            all.put(cur, price);
            while (!stack.isEmpty() && all.get(stack.peek()) <= price) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                stack.add(cur);
                return cur + 1;
            } else {
                int peek = stack.peek();
                stack.add(cur);
                return cur - peek;
            }
        }
    }
}
