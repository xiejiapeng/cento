package linshen.structure.stack;

/*
给定一个整数数组 asteroids，表示在同一行的小行星。数组中小行星的索引表示它们在空间中的相对位置。

对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。
每一颗小行星以相同的速度移动。

找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，
则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远不会发生碰撞。
提示：
2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class L735 {
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        List<Integer> x = new ArrayList<>();
        for (int i = 0; i < n; i++){
            x.add(asteroids[i]);
        }

        boolean find = true;
        while (find) {
            List<Integer> y = new ArrayList<>();
            find = false;
            for (int i = 0; i < x.size(); i++){
                if(x.get(i) > 0 && i + 1 < x.size() && x.get(i+1) < 0) {
                    find = true;
                    if(x.get(i) + x.get(i+1) > 0) {
                        y.add(x.get(i));
                    } else if(x.get(i) + x.get(i+1) < 0) {
                        y.add(x.get(i+1));
                    }
                    i+=1;
                } else {
                    y.add(x.get(i));
                }
            }
            x = y;
        }
        return x.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(new L735().asteroidCollision(new int[]{5,10,-5}));
    }
}
