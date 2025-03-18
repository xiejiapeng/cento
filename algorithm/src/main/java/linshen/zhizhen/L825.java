package linshen.zhizhen;

/*
在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。

如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：

ages[y] <= 0.5 * ages[x] + 7
ages[y] > ages[x]
ages[y] > 100 && ages[x] < 100
否则，x 将会向 y 发送一条好友请求。

注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。

返回在该社交媒体网站上产生的好友请求总数。

n == ages.length
1 <= n <= 2 * 104
1 <= ages[i] <= 120
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L825 {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
//        System.out.println(Arrays.toString(ages));
        int n = ages.length;
        int ans = 0;
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++){
            last.put(ages[i], i);
        }
        for (int left = 0, right = 0; right < n; right++){
            while (left < right && ages[left] <= 0.5 * ages[right] + 7) {
                left++;
            }
            if(left < right) {
                ans += (right - left);
            }
            {
                //todo 记得判断条件
                if(ages[right] >= 0.5 * ages[right] + 7) {
                    ans += (last.get(ages[right]) - right);
                }

            }

        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L825().numFriendRequests(new int[]{6, 6, 106, 110}));
    }
}
