package linshen.greedy;

/*
给你一个长度为 n 的整数数组 rolls 和一个整数 k 。你扔一个 k 面的骰子 n 次，
骰子的每个面分别是 1 到 k ，其中第 i 次扔得到的数字是 rolls[i] 。

请你返回 无法 从 rolls 中得到的 最短 骰子子序列的长度。

扔一个 k 面的骰子 len 次得到的是一个长度为 len 的 骰子子序列 。

注意 ，子序列只需要保持在原数组中的顺序，不需要连续。
提示：
n == rolls.length
1 <= n <= 105
1 <= rolls[i] <= k <= 105
 */

import java.util.*;

public class L2350 {
    //todo hhhhh 序列的一个特点是有序，第一个元素确定后，后面的元素只能在它的后面选；这种连贯效应会带来不同的选择策略
    public int shortestSequence(int[] rolls, int k) {
        int n = rolls.length;
        //keep finding the element with the earliest first occurrence
        Map<Integer, Stack<Integer>> first = new HashMap<>();
        for (int i = 1; i <= k; i++){
            first.put(i, new Stack<>());
        }
        for (int i = n - 1; i > -1; i--){
            first.get(rolls[i]).add(i);
        }
        int last = -1;
        int len = 0;
        while (last != n) {
            int max = -1;
            for (Stack<Integer> s : first.values()) {
                if(s.isEmpty())return len+1;
                if(s.peek() > max){
                    max = s.peek();
                }
            }
            for (Stack<Integer> s : first.values()) {
                while (!s.isEmpty() && s.peek() <= max){
                    s.pop();
                }
            }
            last = max;
            len++;
        }
        return len+1;
    }

    public static void main(String[] args) {
        System.out.println(new L2350().shortestSequence(new int[]{1,1,3,2,2,2,3,3}, 4));
    }
}
