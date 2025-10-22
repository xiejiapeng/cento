package linshen.backtrace;

/*
你将会得到一份单词表 words，一个字母表 letters （可能会有重复字母），以及每个字母对应的得分情况表 score。

请你帮忙计算玩家在单词拼写游戏中所能获得的「最高得分」：能够由 letters 里的字母拼写出的
任意 属于 words 单词子集中，分数最高的单词集合的得分。

单词拼写游戏的规则概述如下：

玩家需要用字母表 letters 里的字母来拼写单词表 words 中的单词。
可以只使用字母表 letters 中的部分字母，但是每个字母最多被使用一次。
单词表 words 中每个单词只能计分（使用）一次。
根据字母得分情况表score，字母 'a', 'b', 'c', ... , 'z' 对应的得分
分别为 score[0], score[1], ..., score[25]。
本场游戏的「得分」是指：玩家所拼写出的单词集合里包含的所有字母的得分之和。
提示：

1 <= words.length <= 14
1 <= words[i].length <= 15
1 <= letters.length <= 100
letters[i].length == 1
score.length == 26
0 <= score[i] <= 10
words[i] 和 letters[i] 只包含小写的英文字母。
 */

import java.util.LinkedList;
import java.util.List;

public class L1255 {
    int max = 0;
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] les = new int[26];
        for (char c : letters) {
            les[c - 'a']++;
        }
        dfs(words, 0, les, score, new LinkedList<>());
        return max;
    }

    private void dfs(String[] words, int cur, int[] les, int[] score, LinkedList<String> all) {
        if(cur == words.length) {
            int s = 0;
            for (String w : all) {
                for (char c : w.toCharArray()) {
                    s += score[c - 'a'];
                }
            }
            max = Math.max(max, s);
        } else {
            //choose cur
            int[] w = new int[26];
            for (char c : words[cur].toCharArray()) {
                w[c - 'a']++;
            }
            boolean can = true;
            for (int i = 0; i < 26; i++) {
                if(w[i] > les[i])can = false;
            }
            if(can) {
                for (int i = 0; i < 26; i++){
                    les[i] -= w[i];
                }
                all.addLast(words[cur]);
                dfs(words, cur + 1, les, score, all);
                all.removeLast();
                for (int i = 0; i < 26; i++){
                    les[i] += w[i];
                }
            }

            dfs(words, cur + 1, les, score, all);

        }
    }

}
