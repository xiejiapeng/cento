package index.binary;

/*
冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。

在加热器的加热半径范围内的每个房屋都可以获得供暖。

现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。

注意：所有供暖器 heaters 都遵循你的半径标准，加热的半径也一样。
 */

import java.util.Arrays;

public class L475 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int n = houses.length;
        int m = heaters.length;
        int left = n - 1;
        int right = m;
        int max = Integer.MIN_VALUE;
        while (left > -1) {
            while (right-1>-1&&heaters[right-1]>=houses[left]){
                right-=1;
            }
            if(right-1==-1) {
                max = Math.max(max, Math.abs(heaters[right] - houses[left]));
            } else {
                if(right == m) {
                    max = Math.max(max, Math.abs(heaters[right-1] - houses[left]));
                } else {
                    int x = Math.min(heaters[right]-houses[left], houses[left]-heaters[right-1]);
                    max = Math.max(max, x);
                }

            }
        }
        return max;

    }
}
