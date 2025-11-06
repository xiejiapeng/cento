package linshen.greedy;

/*
给你一个下标从 0 开始的字符串数组 words ，数组的长度为 n ，且包含下标从 0 开始的若干字符串。

你可以执行以下操作 任意 次数（包括零次）：

选择整数i、j、x和y，满足0 <= i, j < n，0 <= x < words[i].length，0 <= y < words[j].length，交换 字符 words[i][x] 和 words[j][y] 。
返回一个整数，表示在执行一些操作后，words 中可以包含的回文串的 最大 数量。

注意：在操作过程中，i 和 j 可以相等。
 */

import java.util.Arrays;

public class L3035 {
    //todo hh 一次性过挺意外，回文（非dp）感觉没有固定的解法，记住题目即可，方法没必要记
    public int maxPalindromesAfterOperations(String[] words) {
        int n = words.length;
        int[] len = new int[n];
        int[] all = new int[26];
        for (int i = 0; i < n; i++){
            len[i] = words[i].length();
            for (char c : words[i].toCharArray()) {
                all[c - 'a']++;
            }
        }
        int pair = 0;
        int single = 0;
        for (int i = 0; i < 26; i++) {
            pair += all[i] / 2;
            single += all[i] % 2;
        }
        Arrays.sort(len);
        int cnt = 0;
        for (int i = 0; i < n; i++){
            if(len[i] % 2 == 0) {
                if(pair >= len[i] / 2) {
                    pair -= len[i] / 2;
                    cnt += 1;
                }
            } else {
                if(pair >= len[i] / 2) {
                    pair -= len[i] / 2;

                    if(single > 0) {
                        single -= 1;
                        cnt++;
                    } else {
                        if(pair > 0) {
                            pair -= 1;
                            single += 1;
                            cnt++;
                        }
                    }
                }

            }
        }
        return cnt;
    }
}
