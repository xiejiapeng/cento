package index.seqdp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */

public class L139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> dicts = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n];
        for (int i = n - 1; i > -1; i--){
            dp[i] = dicts.contains(s.substring(i));
            if(dp[i])continue;
            for (int j = i + 1; j < n; j++) {
                if (dicts.contains(s.substring(i, j)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }

        }
        return dp[0];
    }
}
