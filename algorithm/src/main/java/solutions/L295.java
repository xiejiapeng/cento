package solutions;

import java.util.Comparator;
import java.util.PriorityQueue;

public class L295 {
    class MedianFinder {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianFinder() {
            //大顶堆
            this.left = new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1,o2));
            //小顶堆
            this.right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(left.isEmpty())left.add(num);
            else if(right.isEmpty()){
                int x = left.poll();
                left.add(Math.min(num, x));
                right.add(Math.max(num, x));
            } else {
                int lv = left.peek();
                int rv = right.peek();
                if(num >= rv){
                    right.add(num);
                    if(right.size() > left.size()){
                        left.add(right.poll());
                    }
                } else {
                    left.add(num);
                    if(left.size() == right.size() + 2){
                        right.add(left.poll());
                    }
                }
            }
        }

        public double findMedian() {
            if(left.size() == right.size()) {
                int x = left.peek();
                int y = right.peek();
                return (x + y) / 2.0;
            } else {
                return (double) left.peek();
            }
        }
    }
}
