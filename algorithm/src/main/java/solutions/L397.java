package solutions;

import java.util.HashMap;
import java.util.Map;

/*
给定一个正整数n ，你可以做如下操作：

如果n是偶数，则用n / 2替换n 。
如果n是奇数，则可以用n + 1或n - 1替换n 。
返回 n变为 1 所需的 最小替换次数 。
 */
public class L397 {
    Map<Long,Integer> cache = new HashMap<>();

    private int dfs(long n){
        if(cache.containsKey(n))return cache.get(n);
        else {
            int ans;
            if(n == 1) ans = 0;
            else {
                if(n % 2 == 0){
                    ans = 1 + dfs(n / 2);
                } else {
                    ans = 1 + Math.min(dfs(n-1),dfs(n+1));
                }
            }

            cache.put(n, ans);
            return ans;
        }
    }
    public int integerReplacement(int n) {
        return dfs(n);
    }
}
