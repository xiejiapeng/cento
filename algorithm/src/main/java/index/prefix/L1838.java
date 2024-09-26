package index.prefix;

/*
元素的 频数 是该元素在一个数组中出现的次数。

给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。

执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。


 */

import java.util.Arrays;

public class L1838 {
    public int maxFrequency(int[] x, int k) {
        int n = x.length;
        long[] nums = new long[n];
        for (int i = 0; i < n; i++){
            nums[i] = (long)x[i];
        }
        Arrays.sort(nums);


        long[] sum = new long[n+1];
        for (int i = 0; i < n; i++){
            sum[i+1] = nums[i] + sum[i];
        }
        int max = Integer.MIN_VALUE;
        //(j,i]
        for (int i = 0, j = -1; i < n; i++){
            while (j < i && (((i-j)*nums[i]) - (sum[i+1]-sum[j+1])) > k) {
                j++;
            }
            max = Math.max(max, i-j);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L1838().maxFrequency(new int[]{1,1,10}, 1));
    }
}
