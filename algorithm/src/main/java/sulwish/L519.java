package sulwish;

/*
给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。

尽量最少调用内置的随机函数，并且优化时间和空间复杂度。

实现 Solution 类：

Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
void reset() 将矩阵中所有的值重置为 0
 */

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class L519 {
    static class Solution {
        int m;
        int n;

        Set<String> all;

        Random r;

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            this.all = new HashSet<>();
            this.r = new Random();
        }

        public int[] flip() {
            while (true) {
                int x = r.nextInt(m);
                int y = r.nextInt(n);
                int[] a = new int[]{x,y};
                if(!all.contains(x+"_"+y)){
                    all.add(x+"_"+y);
                    return a;
                }
            }
        }

        public void reset() {
            all = new HashSet<>();
        }
    }
}
