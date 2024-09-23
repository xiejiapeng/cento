package index.binary;

/*
给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。

在给定的矩形覆盖的空间内的任何整数点都有可能被返回。

请注意 ，整数点是具有整数坐标的点。

实现 Solution 类:

Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class L497 {
    class Solution {


        int[][] rects;
        int[][] all;
        int[] start;
        int sum;
        Random r;

        public Solution(int[][] rects) {
            this.rects = rects;
            int n = rects.length;
            int[][] all = new int[n][2];
            for (int i = 0; i < rects.length; i++){
                all[i] = new int[]{(rects[i][2]-rects[i][0]) * (rects[i][3]-rects[i][1]), i};
            }
            Arrays.sort(all, Comparator.comparingInt(o -> o[0]));
            int[] start = new int[n];
            start[0] = 0;
            int sum = all[0][0];
            for (int i = 1; i < n; i++){
                start[i] = start[i-1] + all[i-1][0];
                sum += all[i][0];
            }

            this.all = all;
            this.start = start;
            this.sum = sum;
            this.r = new Random();


        }

        public int[] pick() {
            int t = r.nextInt(sum);
            int id = find(all, start, t, 0, start.length-1);
            int a = rects[id][0] + r.nextInt(rects[id][2] - rects[id][0]);
            int b = rects[id][1] + r.nextInt(rects[id][3] - rects[id][1]);
            return new int[]{a,b};
        }

        //find last x, start[x] <= end
        public int find(int[][] all, int[] start, int end, int left, int right) {
            if(left == right) {
                return all[left][1];
            } else if(left == right - 1) {
                if(start[right] <= end) {
                    return all[right][1];
                } else {
                    return all[left][1];
                }
            } else {
                int mid = (left + right) / 2;
                if(start[mid] <= end) {
                    return find(all, start, end, mid, right);
                } else {
                    return find(all, start, end, left, mid -1);
                }
            }
        }
    }
}
