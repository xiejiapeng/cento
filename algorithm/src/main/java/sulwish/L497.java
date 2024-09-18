package sulwish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class L497 {
    class Solution {
        Random r = new Random();
        int[][] all;
        int[][] intervals;

        public Solution(int[][] rects) {
            Arrays.sort(rects, Comparator.comparingInt(o -> o[0]));
            this.all = rects;
            this.intervals = new int[rects.length][2];
            for (int i = 0; i < all.length; i++){
                intervals[i] = new int[]{all[i][0], all[i][2]};
            }
        }

        public int[] pick() {
            int id = getId(intervals);
            int x = get(all[id][0],all[id][2]);
            int y = get(all[id][1], all[id][3]);
            return new int[]{x,y};
        }

        private int getId(int[][] intervals) {
            long sum = Arrays.stream(intervals)
                    .map(e -> (e[1] - e[0]))
                    .mapToLong(i -> i)
                    .sum();
            long rs = (long)(r.nextDouble() * sum);
            int s = 0;
            for (int i = 0; i < intervals.length; i++){
                s += (intervals[i][1] - intervals[i][0]);
                if(s >= rs)return i;
            }
            return intervals.length - 1;
        }

        private int get(int a, int b){
            return a + (int)(r.nextDouble() * (b - a));
        }
    }
}
