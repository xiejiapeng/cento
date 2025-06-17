package linshen.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
提示：

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s 和 wordDict[i] 仅由小写英文字母组成
wordDict 中的所有字符串 互不相同
 */

public class L139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[n] = true;
        Set<String> words = new HashSet<>(wordDict);
        for (int i = n - 1; i > -1; i--) {
            for (String word : words) {
                if(s.substring(i).startsWith(word)) {
                    dp[i] |= dp[i+word.length()];
                }
            }
        }
        return dp[0];
    }
}
