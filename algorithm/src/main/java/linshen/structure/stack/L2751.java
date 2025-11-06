package linshen.structure.stack;
/*
现有 n 个机器人，编号从 1 开始，每个机器人包含在路线上的位置、健康度和移动方向。

给你下标从 0 开始的两个整数数组 positions、healths 和一个字符串 directions（directions[i] 为 'L' 表示 向左 或 'R' 表示 向右）。 positions 中的所有整数 互不相同 。

所有机器人以 相同速度 同时 沿给定方向在路线上移动。如果两个机器人移动到相同位置，则会发生 碰撞 。

如果两个机器人发生碰撞，则将 健康度较低 的机器人从路线中 移除 ，并且另一个机器人的健康度 减少 1 。幸存下来的机器人将会继续沿着与之前 相同 的方向前进。如果两个机器人的健康度相同，则将二者都从路线中移除。

请你确定全部碰撞后幸存下的所有机器人的 健康度 ，并按照原来机器人编号的顺序排列。即机器人 1 （如果幸存）的最终健康度，机器人 2 （如果幸存）的最终健康度等。 如果不存在幸存的机器人，则返回空数组。

在不再发生任何碰撞后，请你以数组形式，返回所有剩余机器人的健康度（按机器人输入中的编号顺序）。

注意：位置 positions 可能是乱序的。
 */

import java.util.*;
import java.util.stream.Collectors;


public class L2751 {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        //i, pos, health, d
        int[][] all = new int[n][4];
        for (int i = 0; i < n; i++){
            all[i] = new int[]{i, positions[i], healths[i], directions.charAt(i) == 'L' ? -1 : 1};
        }
        Arrays.sort(all, Comparator.comparingInt(o -> o[1]));
        LinkedList<int[]> stack = new LinkedList<>();
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && (collide(stack.getLast(), all[i]))) {
                int[] a = stack.removeLast();
                int[] b = all[i];
                if(a[2] > b[2]) {
                    all[i] = new int[]{a[0], (a[1]+b[1])/2, a[2]-1, a[3]};
                } else if(a[2] < b[2]) {
                    all[i] = new int[]{b[0], (a[1]+b[1])/2, b[2]-1, b[3]};
                } else {
                    all[i] = null;
                    break;
                }
            }
            if(all[i] != null)stack.addLast(all[i]);
        }
        stack.sort(Comparator.comparingInt(o -> o[0]));
        return stack.stream().map(i -> i[2]).collect(Collectors.toList());
    }

    //i,pos, health, d
    private boolean collide(int[] a, int[] b) {
        return a[3] == 1 && b[3] == -1;
    }

    public static void main(String[] args) {
        System.out.println(new L2751().survivedRobotsHealths(new int[]{2,19,46}, new int[]{42,45,2}, "LRL"));
    }
}
