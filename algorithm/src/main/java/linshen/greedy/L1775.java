package linshen.greedy;

/*
给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。

每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。

请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L1775 {
    public int minOperations(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        if(s1 > s2) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
            int ts = s1;
            s1 = s2;
            s2 = ts;
        }
        int diff = s2 - s1;
        int cnt = 0;
        PriorityQueue<Integer> all = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        for (int i = 0; i < nums1.length; i++){
            all.add(6 - nums1[i]);
        }
        for (int i = nums2.length - 1; i > -1; i--){
            all.add(nums2[i] - 1);
        }
        while (diff > 0 && !all.isEmpty()) {
            int t = all.poll();
            //todo hh 不需要减到正好为diff，可以大于diff，因为我们是按照最大可减少量估计的
            if(t >= diff) {
                cnt++;
                return cnt;
            } else {
                cnt++;
                diff -= t;
            }
        }
        if(diff != 0)return -1;
        else return cnt;

    }

    public static void main(String[] args) {
        System.out.println(new L1775().minOperations(new int[]{6,6}, new int[]{1}));
    }
}
