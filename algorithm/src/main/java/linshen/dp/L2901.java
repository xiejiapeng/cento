package linshen.dp;

/*
给定一个字符串数组 words ，和一个数组 groups ，两个数组长度都是 n 。

两个长度相等字符串的 汉明距离 定义为对应位置字符 不同 的数目。

你需要从下标 [0, 1, ..., n - 1] 中选出一个 最长子序列 ，将这个子序列记作长度为 k 的 [i0, i1, ..., ik - 1] ，它需要满足以下条件：

相邻 下标对应的 groups 值 不同。即，对于所有满足 0 < j + 1 < k 的 j 都有 groups[ij] != groups[ij + 1] 。
对于所有 0 < j + 1 < k 的下标 j ，都满足 words[ij] 和 words[ij + 1] 的长度 相等 ，且两个字符串之间的 汉明距离 为 1 。
请你返回一个字符串数组，它是下标子序列 依次 对应 words 数组中的字符串连接形成的字符串数组。如果有多个答案，返回任意一个。

子序列 指的是从原数组中删掉一些（也可能一个也不删掉）元素，剩余元素不改变相对位置得到的新的数组。

注意：words 中的字符串长度可能 不相等 。

提示：

1 <= n == words.length == groups.length <= 1000
1 <= words[i].length <= 10
1 <= groups[i] <= n
words 中的字符串 互不相同 。
words[i] 只包含小写英文字母。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L2901 {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        List<Integer>[] dp = new List[n+1];
        dp[n] = new ArrayList<>();
        List<Integer> max = new ArrayList<>();
        for (int i = n - 1; i > -1; i--) {
            dp[i] = Arrays.asList(i);
            if(words[i].equals("bab")){
                System.out.println("f");
            }
            //choose i
            for (int j = i + 1; j < n; j++){
                if(words[j].length() == words[i].length() && groups[j] != groups[i] && dist(words[i], words[j]) == 1) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.addAll(dp[j]);
                    if(tmp.size() > dp[i].size())dp[i] = tmp;
                }
            }
            if(dp[i].size() > max.size())max = dp[i];
        }

        return max.stream().map(i -> words[i]).collect(Collectors.toList());
    }

    private int dist(String a, String b) {
        int d = 0;
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) != b.charAt(i))d++;
        }
        return d;
    }

    public static void main(String[] args) {
        System.out.println(new L2901().getWordsInLongestSubsequence(new String[]{"bab","dab","cab"}, new int[]{1,2,2}));
    }
}
