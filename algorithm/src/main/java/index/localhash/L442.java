package index.localhash;

import java.util.*;

/*
给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。
请你找出所有出现 两次 的整数，并以数组形式返回。

你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间（不包括存储输出所需的空间）的算法解决此问题。
 */

public class L442 {
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < n; i++){
            if(nums[i] != i + 1) {
                int x = nums[i];
                while (x != i + 1 && x - 1 > -1 && x - 1 < n) {
                    int y = nums[x-1];
                    if(x == y){
                        ans.add(x);
                        break;
                    }
                    nums[x-1] = x;
                    x = y;
                }
                nums[i] = x;
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        System.out.println(new L442().findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
    }
}