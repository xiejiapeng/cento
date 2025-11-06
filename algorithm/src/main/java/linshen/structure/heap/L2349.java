package linshen.structure.heap;

/*
设计一个数字容器系统，可以实现以下功能：

在系统中给定下标处 插入 或者 替换 一个数字。
返回 系统中给定数字的最小下标。
请你实现一个 NumberContainers 类：

NumberContainers() 初始化数字容器系统。
void change(int index, int number) 在下标 index 处填入 number 。如果该下标 index 处已经有数字了，那么用 number 替换该数字。
int find(int number) 返回给定数字 number 在系统中的最小下标。如果系统中没有 number ，那么返回 -1 。
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class L2349 {

    //todo h 在queue中删除数据的方式；思考，这里的queue一定需要为priority queue吗？java的嵌套集合真是用到吐了
    //如果是priority queue，可以不用delCount结构，而是构造一个删除堆，要删除哪个就放入堆里；在pop的时候如果同时存在于两个堆中则代表删除

    public static class NumberContainers {
        Map<Integer, PriorityQueue<Integer>> num2Pos;
        Map<Integer, Map<Integer, Integer>> delCount;
        Map<Integer, Integer> cur;

        public NumberContainers() {
            num2Pos = new HashMap<>();
            delCount = new HashMap<>();
            cur = new HashMap<>();
        }

        public void change(int index, int number) {
            if(cur.containsKey(index)) {
                if(cur.get(index) == number)return;
                int former = cur.get(index);
                delCount.putIfAbsent(former, new HashMap<>());
                Map<Integer, Integer> del = delCount.get(former);
                del.put(index, del.getOrDefault(index, 0) + 1);

                cur.put(index, number);
                num2Pos.putIfAbsent(number, new PriorityQueue<>());
                Map<Integer, Integer> del2 = delCount.getOrDefault(number, new HashMap<>());
                if(del2.getOrDefault(index, 0) > 0) {
                    del2.put(index, del2.get(index) -1);
                } else {
                    num2Pos.get(number).add(index);
                }
            } else {
                cur.put(index, number);
                num2Pos.putIfAbsent(number, new PriorityQueue<>());
                Map<Integer, Integer> del2 = delCount.getOrDefault(number, new HashMap<>());
                if(del2.getOrDefault(index, 0) > 0) {
                    del2.put(index, del2.get(index) -1);
                } else {
                    num2Pos.get(number).add(index);
                }
            }
        }

        public int find(int number) {
            if(!num2Pos.containsKey(number))return -1;
            PriorityQueue<Integer> x = num2Pos.get(number);
            while (!x.isEmpty() && delCount.containsKey(number) && delCount.get(number).getOrDefault(x.peek(), 0) > 0) {
                int u = x.peek();
                delCount.get(number).put(u, delCount.get(number).get(u)-1);
                x.poll();
            }
            if(!num2Pos.get(number).isEmpty())return num2Pos.get(number).peek();
            else return -1;
        }
    }

    public static void main(String[] args) {
        NumberContainers l = new NumberContainers();
        System.out.println(l.find(10));
        l.change(2, 10);
        l.change(1, 10);
        l.change(3, 10);
        l.change(5, 10);
        System.out.println(l.find(10));
        l.change(1, 20);
        System.out.println(l.find(10));
    }
}
