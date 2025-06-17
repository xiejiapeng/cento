package linshen.dp;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/*
给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。
你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。
s 中可能会有一些 额外的字符 不在任何子字符串中。
请你采取最优策略分割 s ，使剩下的字符 最少 。
提示：

1 <= s.length <= 50
1 <= dictionary.length <= 50
1 <= dictionary[i].length <= 50
dictionary[i] 和 s 只包含小写英文字母。
dictionary 中的单词互不相同。
 */
public class L2707 {
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        Set<String> words = Arrays.stream(dictionary).collect(Collectors.toSet());
        int[] dp = new int[n+1];
        for (int i = n - 1; i > -1; i--){
            //don't choose i
            dp[i] = dp[i+1];
            for (int j = i; j < n; j++){
                if(words.contains(s.substring(i,j+1))) {
                    dp[i] = Math.max(dp[i], j-i+1 + dp[j+1]);
                }
            }
        }
        return n - dp[0];
    }
}
