package linshen.zhizhen;

/*
给你一个二维整数数组 tiles ，其中 tiles[i] = [li, ri] ，
表示所有在 li <= j <= ri 之间的每个瓷砖位置 j 都被涂成了白色。

同时给你一个整数 carpetLen ，表示可以放在 任何位置 的一块毯子的长度。

请你返回使用这块毯子，最多 可以盖住多少块瓷砖。

提示：

1 <= tiles.length <= 5 * 104
tiles[i].length == 2
1 <= li <= ri <= 109
1 <= carpetLen <= 109
tiles 互相 不会重叠 。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L2271 {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int n = tiles.length;
        int max = 0;
        //todo h: 记得先排序
        Arrays.sort(tiles, Comparator.comparingInt(o -> o[0]));
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + tiles[i-1][1] - tiles[i-1][0] + 1;
        }
        for (int left = 0, right = 0; right < n; right++){
            //记得有max操作
            int tail = Math.max(0, tiles[right][1] - carpetLen + 1);
            while (tiles[left][1] < tail){
                left++;
            }
            int len = sum[right+1] - sum[left];
            //记得有max操作
            len -= Math.max(0, tail - tiles[left][0]);
            max = Math.max(max, len);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] tiles = {{1,5},{10,11},{12,18},{20,25},{30,32}};
        System.out.println(new L2271().maximumWhiteTiles(tiles, 10));
    }
}
