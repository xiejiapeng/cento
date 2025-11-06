package linshen.structure;

/*
给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，请你找出并返回两个非重叠 子数组 中元素的最大和，长度分别为 firstLen 和 secondLen 。

长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。

子数组是数组的一个 连续 部分。
提示：

1 <= firstLen, secondLen <= 1000
2 <= firstLen + secondLen <= 1000
firstLen + secondLen <= nums.length <= 1000
0 <= nums[i] <= 1000
 */

public class L1031 {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                //first, second
                if(i + 1 >= firstLen && j - i >= secondLen) {
                    max = Math.max(max, sum[i+1]-sum[i+1-firstLen]+sum[j+1]-sum[j+1-secondLen]);
                }
                //second, first
                if(i + 1 >= secondLen && j - i >= firstLen) {
                    max = Math.max(max, sum[i+1] - sum[i+1-secondLen] + sum[j+1]-sum[j+1-firstLen]);
                }
            }
        }
        return max;
    }
}
