package index.seqdp;

import java.util.*;
import java.util.stream.Collectors;

/*
给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
连接词 定义为：一个完全由给定数组中的至少两个较短单词（不一定是不同的两个单词）组成的字符串。

1 <= words.length <= 104
1 <= words[i].length <= 30
words[i] 仅由小写英文字母组成。
words 中的所有字符串都是 唯一 的。
1 <= sum(words[i].length) <= 105
 */

public class L472 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Set<String> all = Arrays.stream(words).collect(Collectors.toSet());
        int n = words.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(isCombine(words[i], all))ans.add(words[i]);
        }
        return ans;
    }

    private boolean isCombine(String s, Set<String> all) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= n; len++){
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if(len == 1) dp[i][j] = false;
                else if(len == 2) {
                    dp[i][j] = all.contains(s.substring(i,i+1)) && all.contains(s.substring(j,j+1));
                } else {
                    //[i,k],[k+1,j]
                    for (int k = i; k < j; k++){
                        String left = s.substring(i,k+1);
                        String right = s.substring(k+1, j+1);
                        dp[i][j] |= ((all.contains(left) || dp[i][k]) && (all.contains(right) || dp[k+1][j]));
                    }
                }
            }
        }
        return dp[0][n-1];
    }


}
