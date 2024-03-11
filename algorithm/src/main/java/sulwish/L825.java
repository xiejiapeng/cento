package sulwish;

/*
在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。

如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：

ages[y] <= 0.5 * ages[x] + 7
ages[y] > ages[x]
ages[y] > 100 && ages[x] < 100
否则，x 将会向 y 发送一条好友请求。

y > 0.5x+7 && y <= x && (y <= 100 || x >= 100)

注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。

返回在该社交媒体网站上产生的好友请求总数。
 */

import java.util.Arrays;

public class L825 {
    public int numFriendRequests(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int cnt = 0;
        int have = 0;
        for (int left = 0, right = 0; right < n; right++) {
            if(right + 1 < n && ages[right] == ages[right + 1]) {
                have++;
            } else {
                while (left < right && ages[left] <= 0.5 * ages[right] + 7) left++;
                if(left < right)cnt += (have+1) * (right - left);
                have = 0;
            }

        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L825().numFriendRequests(new int[]{16,16}));
    }
}
