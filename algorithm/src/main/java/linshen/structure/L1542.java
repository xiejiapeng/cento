package linshen.structure;

/*
给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。

「超赞子字符串」需满足满足下述两个条件：

该字符串是 s 的一个非空子字符串
进行任意次数的字符交换后，该字符串可以变成一个回文字符串
提示：

1 <= s.length <= 10^5
s 仅由数字组成

todo hh 秒了，挺意外的，当经典题型记下来吧
注意状态的记忆
注意分类讨论
    对0-9分类
    对奇偶分类
 */

import java.util.HashMap;
import java.util.Map;

public class L1542 {
    public int longestAwesome(String s) {
        int n = s.length();
        Map<Integer, Integer> odd = new HashMap<>();
        Map<Integer, Integer> even = new HashMap<>();
        int cur = 0;
        even.put(cur, 0);
        int max = -1;
        for (int i = 0; i < s.length(); i++){
            int t = s.charAt(i) - '0';
            cur = cur ^ (1 << t);
            //目前为偶数even
            if((i + 1) % 2 == 0) {
                //target even
                if(even.containsKey(cur)) {
                    max = Math.max(max, i + 1 - even.get(cur));
                }
                //target odd
                for (int j = 0; j <= 9; j++){
                    int x = cur ^ (1 << j);
                    if(odd.containsKey(x)) {
                        max = Math.max(max, i + 1 - odd.get(x));
                    }
                }
                if(!even.containsKey(cur)){
                    even.put(cur, i+1);
                }
            } else {
                //target even
                if(odd.containsKey(cur)) {
                    max = Math.max(max, i + 1 - odd.get(cur));
                }
                //target odd
                for (int j = 0; j <= 9; j++){
                    int x = cur ^ (1 << j);
                    if(even.containsKey(x)) {
                        max = Math.max(max, i + 1 - even.get(x));
                    }
                }
                if(!odd.containsKey(cur)) {
                    odd.put(cur, i + 1);
                }
            }
        }
        return max;
    }
}
