package sulwish;

/*
给定两个长度相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。

返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 */

import java.util.*;

public class L870v2 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        TreeMap<Integer,Integer> all = new TreeMap<>();
        for (int x : nums1) {
            all.put(x, all.getOrDefault(x,0) + 1);
        }
        Map<Integer,Integer> ans = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            int x = nums2[i];
            Integer y = all.ceilingKey(x+1);
            if(y != null) {
                ans.put(i, y);
                all.put(y, all.get(y) - 1);
                if(all.get(y) == 0)all.remove(y);
            }
        }
        int t = 0;


        for (int x : all.keySet()) {
            int c = all.get(x);
            for (int i = 0; i < c; i++){
                while (ans.containsKey(t)) {
                    t++;
                }
                ans.put(t, x);
            }
        }

        int n = nums1.length;
        int[] aans = new int[n];
        for (int i = 0; i < n; i++){
            aans[i] = ans.get(i);
        }
        return aans;
    }
}
