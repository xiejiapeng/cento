package index.prefix;

/*
在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。

如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：

ages[y] <= 0.5 * ages[x] + 7
ages[y] > ages[x]
ages[y] > 100 && ages[x] < 100
否则，x 将会向 y 发送一条好友请求。

n == ages.length
1 <= n <= 2 * 104
1 <= ages[i] <= 120

注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。

返回在该社交媒体网站上产生的好友请求总数。


 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L825 {
    public int numFriendRequests(int[] ages) {
        Map<Integer,Integer> as = new HashMap<>();
        for (int x : ages) {
            as.put(x, as.getOrDefault(x, 0) + 1);
        }
        int n = as.keySet().size();
        int[] ages2 = new int[n];
        int[] cs = new int[n];
        int t = 0;
        for (int x : as.keySet()) {
            ages2[t] = x;
            t++;
        }
        int[] sum = new int[n + 1];

        Arrays.sort(ages2);
        for (int i = 0; i < n; i++){
            cs[i] = as.get(ages2[i]);
        }
        for (int i = 0; i < n; i++){
            sum[i+1] = cs[i] + sum[i];
        }
        int cnt = 0;
        for (int i = 0, j = 0; i < n; i++){
            while (j < i && ages2[j] <= 0.5 * ages2[i] + 7) {
                j++;
            }
            if(j != i) {
                cnt += (sum[i] - sum[j])*cs[i];
            }
        }
        for (int x : ages2) {
            int c = as.get(x);
            if(c <= 1)continue;
            else {
                if(x > 0.5 * x + 7) {
                    cnt += (c-1)*c;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L825().numFriendRequests(new int[]{6,26,35,46,6}));
    }
}
