package sulwish;

/*
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */

import java.util.Arrays;
import java.util.List;

public class L139 {
    public static void main(String[] args) {
        System.out.println(new L139().wordBreak("", Arrays.asList()));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] ans = new boolean[n];
        for (int i = n - 1; i > -1; i--) {
            ans[i] = false;
            if (i == n - 1) {
                ans[i] = wordDict.contains(s.substring(n - 1));
            } else {
                String tmp = s.substring(i);
                for (String w : wordDict) {
                    if (tmp.startsWith(w)) {
                        ans[i] |= (i + w.length() == n || ans[i + w.length()]);
                    }
                }
            }
        }
        return ans[0];
    }
}
