package linshen.binary;

/*
给你一个数组 time ，其中 time[i] 表示第 i 辆公交车完成 一趟旅途 所需要花费的时间。

每辆公交车可以 连续 完成多趟旅途，也就是说，一辆公交车当前旅途完成后，可以 立马开始 下一趟旅途。每辆公交车 独立 运行，也就是说可以同时有多辆公交车在运行且互不影响。

给你一个整数 totalTrips ，表示所有公交车 总共 需要完成的旅途数目。请你返回完成 至少 totalTrips 趟旅途需要花费的 最少 时间。
x/t1+x/t2+...+x/tn>=total
n*x/t1 >= total

1 <= time.length <= 105
1 <= time[i], totalTrips <= 107
 */

import java.util.Arrays;

public class L2187 {
    public long minimumTime(int[] time, int totalTrips) {
        int n = time.length;
        long max = Arrays.stream(time).max().getAsInt();
        long upper = (totalTrips / n + 1) * max;
        return findSmallest(1, upper, totalTrips, time);
    }

    private long findSmallest(long left, long right, int threshold, int[] nums) {
        if(left == right) return left;
        else if(left == right - 1) {
            if(check(nums, left, threshold))return left;
            else return right;
        } else {
            long mid = (left + right) / 2;
            System.out.println("left="+left+",mid="+mid+",right="+right);
            if(check(nums, mid, threshold))return findSmallest(left, mid, threshold, nums);
            else return findSmallest(mid + 1, right, threshold, nums);
        }
    }

    private boolean check(int[] time, long t, int totalTrips) {
        long sum = 0;
        for (int x : time) {
            sum += (t / x);
        }
        return sum >= totalTrips;
    }

    public static void main(String[] args) {
        System.out.println(new L2187().minimumTime(new int[]{1935,5727103}, 6189436));
    }
}
