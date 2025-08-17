package linshen.structure.heap;

/*
给你一个整数数组 target 。一开始，你有一个数组 A ，它的所有元素均为 1 ，你可以执行以下操作：

令 x 为你数组里所有元素的和
选择满足 0 <= i < target.size 的任意下标 i ，并让 A 数组里下标为 i 处的值为 x 。
你可以重复该过程任意次
如果能从 A 开始构造出目标数组 target ，请你返回 True ，否则返回 False 。
1,1
2,1
3,1 - 2,3
8,5 => 3,5 => 3,2 => 1,2 => 1,1
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L1354 {

    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> all = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        //todo hhh 注意这里用long；原来用int结果超时了，后面推断是溢出导致多次循环；思路本身没有问题
        long sum = 0;
        for (int x : target) {
            all.add(x);
            sum += x;
        }
        while (true) {
            int t = all.poll();
            if(t == 1)return true;
            long remain = sum - t;
            if(remain == 1)return true;
            if(t <= remain || remain == 0 || t % remain == 0)return false;
            else {
                all.add((int)(t % remain));
                sum -= (t - t % remain);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(new L1354().isPossible(new int[]{2}));
    }
}
