package linshen.zhizhen;

/*
给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和 'B' 分别表示白色和黑色。

给你一个整数 k ，表示想要 连续 黑色块的数目。

每一次操作中，你可以选择一个白色块将它 涂成 黑色块。

请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 */
public class L2379 {
    public int minimumRecolors(String blocks, int k) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < blocks.length(); right++) {
            int left = right - k + 1;
            sum += (blocks.charAt(right) == 'B' ? 1 : 0);
            if(left - 1 >= 0) {
                sum -= (blocks.charAt(left - 1) == 'B' ? 1 : 0);
            }
            if(left >= 0) {
                min = Math.min(min, k - sum);
            }
        }
        return min;
    }
}
