package index.binaryenumerate;

/*
给定你一个整数数组 nums

我们要将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，并且 average(A) == average(B) 。

如果可以完成则返回true ， 否则返回 false  。

注意：对于数组 arr ,  average(arr) 是 arr 的所有元素的和除以 arr 长度。
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class L805 {
    int[] f;

    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * n - sum;
        }

        int m = n / 2;
        Set<Integer> left = new HashSet<>();
        for (int i = 0; i < 1 << m; i++) {
            int s = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    s += nums[j];
                }
            }
            if (s == 0) {
                return true;
            }
            left.add(s);
        }

        int rsum = 0;
        for (int i = m; i < n; i++) {
            rsum += nums[i];
        }
        for (int i = 1; i < (1 << (n - m)); i++) {
            int tot = 0;
            for (int j = m; j < n; j++) {
                if ((i & (1 << (j - m))) != 0) {
                    tot += nums[j];
                }
            }
            if (tot == 0 || (rsum != tot && left.contains(-tot))) {
                return true;
            }
        }
        return false;

    }

}
