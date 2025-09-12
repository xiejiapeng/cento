package linshen.greedy;

/*
给你一个字符串 s 。s[i] 要么是小写英文字母，要么是问号 '?' 。

对于长度为 m 且 只 含有小写英文字母的字符串 t ，我们定义函数 cost(i) 为下标 i 之前
（也就是范围 [0, i - 1] 中）出现过与 t[i] 相同 字符出现的次数。

字符串 t 的 分数 为所有下标 i 的 cost(i) 之 和 。

比方说，字符串 t = "aab" ：

cost(0) = 0
cost(1) = 1
cost(2) = 0
所以，字符串 "aab" 的分数为 0 + 1 + 0 = 1 。
你的任务是用小写英文字母 替换 s 中 所有 问号，使 s 的 分数最小 。

请你返回替换所有问号 '?' 之后且分数最小的字符串。如果有多个字符串的 分数最小 ，那么返回字典序最小的一个。

aa?bb?cc?dd
x,y
=> f(x) + g(y) + if(x,y)
&&&
--
****

 */

import scala.Char;

import java.util.*;

public class L3081 {
    //todo hhh 没做出来，应该要能通过数学公式推理出frequency对总和的贡献，而每个字母frequency的总和又是固定的
    public String minimizeStringValue(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '?')cnt[s.charAt(i) - 'a']++;
        }

        LinkedList<Character> lc = new LinkedList<>();
        for (int i = 0; i < n; i++){
            if(s.charAt(i) == '?'){
                int min = Integer.MAX_VALUE;
                int ans = -1;
                for (int j = 0; j < 26; j++){
                    int c = cnt[j];
                    if(c < min) {
                        min = c;
                        ans = j;
                    }
                }
                lc.addLast((char)('a' + ans));
                cnt[ans]++;
            }

        }

        Collections.sort(lc);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            if(s.charAt(i) != '?')sb.append(s.charAt(i));
            else {
                sb.append(lc.pollFirst());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new L3081().minimizeStringValue("abcdefghijklmnopqrstuvwxy??"));
    }
}
