package index.dfs;

import java.util.ArrayList;
import java.util.List;
/*
给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。

你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 */

public class L386 {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++){
            if(i <= n) {
                ans.add(i);
                dfs(n, i, ans);
            }
        }
        return ans;
    }

    private void dfs(int n, int cur, List<Integer> ans) {
        if(cur <= n) {
            for (int i = 0; i <= 9; i++){
                int x = cur * 10 + i;
                if(x <= n) {
                    ans.add(x);
                    dfs(n, x, ans);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L386().lexicalOrder(13));
    }
}
