package linshen.zhizhen;

/*
给你一个整数数组 nums 和一个 正整数 k 。

请你统计有多少满足 「 nums 中的 最大 元素」至少出现 k 次的子数组，并返回满足这一条件的子数组的数目。

子数组是数组中的一个连续元素序列。
 */

import java.util.Arrays;
import java.util.LinkedList;

public class L2962 {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        int cnt = 0;
        long ans = 0;
        LinkedList<Integer> pos = new LinkedList<>();
        for (int i = 0; i < n; i++){
            if(nums[i] == max){
                pos.addLast(i);
                cnt++;
            }
            if(cnt < k)continue;
            else if(cnt == k) {
                //todo 不仅仅是+1
                int t = pos.getFirst();
                ans += (t + 1);
            }
            else {
                while (cnt > k) {
                    pos.removeFirst();
                    cnt--;
                }
                int t = pos.getFirst();
                ans += (t + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L2962().countSubarrays(new int[]{1,3,2,3,3}, 2));
    }
}
