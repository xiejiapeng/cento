package linshen.dp;
/*
给你 2 枚相同 的鸡蛋，和一栋从第 1 层到第 n 层共有 n 层楼的建筑。

已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都 会碎 ，从 f 楼层或比它低 的楼层落下的鸡蛋都 不会碎 。

每次操作，你可以取一枚 没有碎 的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。

请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
提示：
1 <= n <= 1000
0 1 2

2:
先1，如果碎，则f=0
    如果不碎
 */

import java.util.Arrays;

public class L1884 {
    public int twoEggDrop(int n) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++){
            dp[i] = i;
            if(i == 2) {
                System.out.println("f");
            }
            //throws at j
            for (int j = 1; j < i; j++){
                //broken
                int x = j - 1;
                //not broken
                //todo hhhhhh 记住，转化为了这个子问题！
                int y = dp[i-j];
                dp[i] = Math.min(dp[i], 1 + Math.max(x, y));
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new L1884().twoEggDrop(2));
    }
}
