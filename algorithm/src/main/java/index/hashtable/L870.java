package index.hashtable;

/*
给定两个长度相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。

返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。

2,0,4,1,2

2 4 1 2 0
1,3,0,0,2
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class L870 {
    public static void main(String[] args) {
        System.out.println(new L870().advantageCount(new int[]{2, 4, 1, 2, 0}, new int[]{1, 3, 0, 0, 2}));
    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeSet<int[]> all = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                else return Integer.compare(o1[1], o2[1]);
            }
        });
        for (int i = 0; i < nums1.length; i++) {
            all.add(new int[]{nums1[i], i});
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int[] y = all.higher(new int[]{nums2[i], Integer.MAX_VALUE});
            if (y != null) {
                all.remove(y);
                ans[i] = y[0];
            }
        }
        for (int i = 0; i < n; i++) {
            if (ans[i] == -1) {
                ans[i] = all.pollFirst()[0];
            }
        }
        return ans;
    }
}
