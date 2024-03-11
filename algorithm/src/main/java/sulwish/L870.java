package sulwish;

import java.util.Arrays;
import java.util.Comparator;

/*
给定两个长度相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。

返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 */
public class L870 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] a = new int[n][2];
        int[][] b = new int[n][2];
        for (int i = 0; i < n; i++){
            a[i][0] = nums1[i];
            a[i][1] = i;
            b[i][0] = nums2[i];
            b[i][1] = i;
        }
        Arrays.sort(a, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(b, Comparator.comparing(o -> o[0]));

        int left = 0;
        int right = 0;
        int last = n - 1;
        int[] ans = new int[n];

        while (left < n && right < n) {
            if(a[left][0] > b[right][0]) {
                ans[b[right][1]] = a[left][0];
                left++;
                right++;
            } else {
                ans[b[last][1]] = a[left][0];
                last--;
                left++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L870().advantageCount(new int[]{12,24,8,32}, new int[]{13,25,32,11})));
    }
}
