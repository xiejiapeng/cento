package linshen.zhizhen;

/*
力扣嘉年华的花店中从左至右摆放了一排鲜花，记录于整型一维矩阵 flowers 中每个数字表示该位置所种鲜花的品种编号。
你可以选择一段区间的鲜花做成插花，且不能丢弃。 在你选择的插花中，如果每一品种的鲜花数量都不超过 cnt 朵，那么我们认为这束插花是 「美观的」。

例如：[5,5,5,6,6] 中品种为 5 的花有 3 朵， 品种为 6 的花有 2 朵，每一品种 的数量均不超过 3
请返回在这一排鲜花中，共有多少种可选择的区间，使得插花是「美观的」。

注意：

答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1

1 <= flowers.length <= 10^5
1 <= flowers[i] <= 10^5
1 <= cnt <= 10^5

 1 2 3 2 , 1
 1
 12 2
 123 23 3
 32 2
 */

import java.util.HashMap;
import java.util.Map;

public class LCP68 {
    int mod = (int)(1e9+7);
    public int beautifulBouquet(int[] flowers, int cnt) {
        int n = flowers.length;
        Map<Integer, Integer> all = new HashMap<>();
        int ans = 0;
        for (int left = 0, right = 0; right < n; right++){
            all.put(flowers[right], all.getOrDefault(flowers[right], 0) +1);
            while(left < right && all.get(flowers[right]) > cnt) {
                all.put(flowers[left], all.get(flowers[left]) - 1);
                if(all.get(flowers[left]) == 0)all.remove(flowers[left]);
                left++;
            }
            System.out.println(right-left+1);
            ans += (right - left + 1);
            ans %= mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LCP68().beautifulBouquet(new int[]{1,2,3,2}, 1));
    }
}
