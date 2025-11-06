package linshen.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
给你一个下标从 0 开始长度为 n 的整数数组 buses ，其中 buses[i] 表示第 i 辆公交车的出发时间。
同时给你一个下标从 0 开始长度为 m 的整数数组 passengers ，其中 passengers[j] 表示第 j 位乘客的到达时间。
所有公交车出发的时间互不相同，所有乘客到达的时间也互不相同。

给你一个整数 capacity ，表示每辆公交车 最多 能容纳的乘客数目。

每位乘客都会排队搭乘下一辆有座位的公交车。如果你在 y 时刻到达，公交在 x 时刻出发，满足 y <= x 且公交没有满，
那么你可以搭乘这一辆公交。最早 到达的乘客优先上车。

返回你可以搭乘公交车的最晚到达公交站时间。你 不能 跟别的乘客同时刻到达。

注意：数组 buses 和 passengers 不一定是有序的。
 */
public class L2332 {
    //todo hhhhh 用set来防止重复，t不断往前退，不要想的太复杂了
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        Set<Integer> passengerSet = new HashSet<>();
        for (int p : passengers) passengerSet.add(p);

        int p = 0;
        for (int b = 0; b < buses.length; b++) {
            int count = 0;
            while (p < passengers.length && passengers[p] <= buses[b] && count < capacity) {
                p++;
                count++;
            }

            // 最后一辆车
            if (b == buses.length - 1) {
                int t;
                if (count < capacity) {
                    // 没满：可以在 bus 出发前任意没被占的时间到达
                    t = buses[b];
                } else {
                    // 满了：必须比最后一位上车乘客早一点
                    t = passengers[p - 1] - 1;
                }
                // 避开已有乘客的时间
                while (passengerSet.contains(t)) t--;
                return t;
            }
        }
        return -1; // 不会到这里
    }

    public static void main(String[] args) {
        System.out.println(new L2332().latestTimeCatchTheBus(new int[]{3,2}, new int[]{2}, 2));
    }
}
