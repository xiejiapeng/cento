package linshen.greedy;

/*
在一根无限长的数轴上，你站在0的位置。终点在target的位置。

你可以做一些数量的移动 numMoves :

每次你可以选择向左或向右移动。
第 i 次移动（从 i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
0-1+2-3+4+5-6

提示:
-109 <= target <= 109
target != 0
 */

public class L754 {
    //todo h 未能解决
    public int reachNumber(int target) {
        int cur = 0;
        int last = 0;
        int c = 0;
        while (cur != target) {
            c++;
            last++;
            if(cur < target) {
                if(cur + last == target){
                    return c;
                } else if(cur + last < target) {
                    cur += last;
                } else {
                    int diff = Math.min(target - cur, cur + last - target);
                    return c + diff;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new L754().reachNumber(3));
    }
}
