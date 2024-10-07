package index.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class L1705 {
    public static void main(String[] args) {
        System.out.println(new L1705().eatenApples(new int[]{9, 2}, new int[]{3, 5}));
    }

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        if (apples.length <= 0) return 0;
        else {
            int day = 0;
            int cnt = 0;
            while (day < days.length || !q.isEmpty()) {
                if (day < apples.length && apples[day] > 0) {
                    q.add(new int[]{apples[day], day + days[day]});
                }

                while (!q.isEmpty() && q.peek()[1] <= day) q.poll();
                if (!q.isEmpty()) {
                    int[] x = q.poll();
                    x[0] -= 1;
                    cnt++;
                    if (x[0] > 0) q.add(x);
                }
                day++;
            }
            return cnt;
        }
    }
}
