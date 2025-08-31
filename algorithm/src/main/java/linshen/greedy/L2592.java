package linshen.greedy;

/*
给你一个下标从 0 开始的整数数组 nums 。你需要将 nums 重新排列成一个新的数组 perm 。

定义 nums 的 伟大值 为满足 0 <= i < nums.length 且 perm[i] > nums[i] 的下标数目。

请你返回重新排列 nums 后的 最大 伟大值。
 */

import java.util.*;

public class L2592 {
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        List<Integer> ks = new ArrayList<>(cnt.keySet());
        Collections.sort(ks);
        List<Integer> vs = new ArrayList<>();
        for (int k : ks) {
            vs.add(cnt.get(k));
        }
        int ans = 0;
        List<Integer> remain = new ArrayList<>(vs);
        int j = 0;
        for (int i = 0; i < vs.size(); i++){
            //todo h i和j不能是同一档位
            if(i == j)j++;
            while (j < remain.size() && remain.get(j) <= vs.get(i)){
                vs.set(i, vs.get(i) - remain.get(j));
                ans += remain.get(j);
                remain.set(j ,0);
                j++;
            }
            if(j < remain.size()){
                remain.set(j, remain.get(j) - vs.get(i));
                ans += vs.get(i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        /*
        8 13 21 21 28 35 42 75
        13 21 28 35 42 75 21 8
         */
        System.out.println(new L2592().maximizeGreatness(new int[]{2,3,3,4}));
    }
}
