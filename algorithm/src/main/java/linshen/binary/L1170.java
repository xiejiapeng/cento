package linshen.binary;

/*
定义一个函数 f(s)，统计 s  中（按字典序比较）最小字母的出现频次 ，其中 s 是一个非空字符串。

例如，若 s = "dcce"，那么 f(s) = 2，因为字典序最小字母是 "c"，它出现了 2 次。

现在，给你两个字符串数组待查表 queries 和词汇表 words 。对于每次查询 queries[i] ，
需统计 words 中满足 f(queries[i]) < f(W) 的 词的数目 ，W 表示词汇表 words 中的每个词。

请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是第 i 次查询的结果。

1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j]、words[i][j] 都由小写英文字母组成
 */

import java.util.Arrays;

public class L1170 {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = queries.length;
        int m = words.length;
        int[] ans = new int[n];
        int[] times = new int[m];
        for (int i = 0; i < m; i++){
            times[i] = time(words[i]);
        }
        Arrays.sort(times);
        for (int i = 0; i < n; i++){
            int t = findLeast(times, 0, m - 1, time(queries[i]));
            if(t == -1)ans[i] = 0;
            else ans[i] = (m - t);
        }
        return ans;
    }

    private int findLeast(int[] times, int left, int right, int limit) {
        if(left > right)return -1;
        else if(left == right) {
            if(times[left] > limit)return left;
            else return -1;
        } else if(left == right - 1) {
            if(times[left] > limit)return left;
            else if(times[right] > limit)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(times[mid] > limit) return findLeast(times, left, mid, limit);
            else return findLeast(times, mid+1,right, limit);
        }
    }

    private int time(String word) {
        int[] a = new int[26];
        for (char c : word.toCharArray()) {
            a[c-'a']++;
        }
        for (int x : a) {
            if(x != 0) return x;
        }
        return 0;
    }
}
