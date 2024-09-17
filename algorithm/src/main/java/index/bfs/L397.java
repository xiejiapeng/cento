package index.bfs;

/*
给定一个正整数 n ，你可以做如下操作：

如果 n 是偶数，则用 n / 2替换 n 。
如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
返回 n 变为 1 所需的 最小替换次数 。
 */

import java.util.LinkedList;
import java.util.Queue;

public class L397 {
    public int integerReplacement(int m) {
        long n = (long) m;
        Queue<Long> queue = new LinkedList<>();
        queue.add(n);
        int round = 0;
        boolean find = false;

        while (!queue.isEmpty() && !find) {
            int size = queue.size();
            while (size-- > 0) {
                long x = queue.poll();
                if (x == 1) {
                    find = true;
                    break;
                }
                if (x % 2 == 0) {
                    queue.add(x / 2);
                } else {
                    queue.add(x + 1);
                    queue.add(x - 1);
                }
            }
            if (find) break;
            round++;
        }
        return round;
    }
}
