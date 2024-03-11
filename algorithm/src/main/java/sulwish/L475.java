package sulwish;

import java.util.Arrays;

/*
冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。

在加热器的加热半径范围内的每个房屋都可以获得供暖。

现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。

注意：所有供暖器 heaters 都遵循你的半径标准，加热的半径也一样。
 */
public class L475 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        long min = 0;
        int n = houses.length;
        long[] left = new long[n];
        long[] right = new long[n];
        int j = -1;
        for (int i = 0; i < houses.length; i++){
            while (j + 1 < heaters.length && heaters[j+1] <= houses[i]){
                j++;
            }
            if(j < 0)left[i] = Long.MAX_VALUE;
            else left[i] = houses[i] - heaters[j];
        }

        j = heaters.length;
        for (int i = houses.length - 1; i > -1; i--){
            while (j - 1 > -1 && heaters[j-1] >= houses[i]){
                j--;
            }
            if(j == heaters.length)right[i] = Long.MAX_VALUE;
            else right[i] = heaters[j] - houses[i];
        }

        for (int i = 0; i < n; i++){
            long hmin = Math.min(left[i],right[i]);
            min = Math.max(min, hmin);
        }

        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        return (int)min;
    }

    public static void main(String[] args) {
        System.out.println(new L475().findRadius(new int[]{282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923},
                new int[]{823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612}));
    }
}
