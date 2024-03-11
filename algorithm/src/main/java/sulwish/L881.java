package sulwish;

/*
给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。

每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。

返回 承载所有人所需的最小船数 。
 */

import java.util.Arrays;

public class L881 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int ans = 0;
        while (left < right) {
            if(people[left] + people[right] <= limit) {
                left++;
                right--;
                ans++;
            } else {
                right--;
                ans++;
            }
        }
        if(left == right)ans++;
        return ans;
    }
}
