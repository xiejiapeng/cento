package solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class L357 {
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0)return 1;
        else if(n == 1)return 10;
        else {
            return count(n) + countNumbersWithUniqueDigits(n-1);
        }
    }

    private int count(int n){
        int c = 9;
        int t = 9;
        for(int i = 2; i <= n; i++){
            c *= t;
            t--;
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new L357().countNumbersWithUniqueDigits(8));
        int[] nums1 = new int[0];
        int[] nums2 = new int[0];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(nums1[o1[0]]+nums2[o1[1]], nums1[o2[0]]+nums2[o2[1]]));
        PriorityQueue<int[]> qxxxx = new PriorityQueue<>((o1, o2) -> Integer.compare(nums1[o1[0]]+nums2[o1[1]], nums1[o2[0]]+nums2[o2[1]]));
    }
}
