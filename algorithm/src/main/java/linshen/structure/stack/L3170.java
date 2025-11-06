package linshen.structure.stack;

/*
给你一个字符串 s 。它可能包含任意数量的 '*' 字符。你的任务是删除所有的 '*' 字符。

当字符串还存在至少一个 '*' 字符时，你可以执行以下操作：

删除最左边的 '*' 字符，同时删除该星号字符左边一个字典序 最小 的字符。如果有多个字典序最小的字符，你可以删除它们中的任意一个。
请你返回删除所有 '*' 字符以后，剩余字符连接而成的 字典序最小 的字符串。
 */

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class L3170 {
    public String clearStars(String s) {
        PriorityQueue<Integer> ds = new PriorityQueue<>((o1, o2) -> {
            if(s.charAt(o1) != s.charAt(o2)) {
                return s.charAt(o1) - s.charAt(o2);
            } else {
                return o2 - o1;
            }
        });
        Set<Integer> a = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '*') {
                a.add(ds.poll());
                a.add(i);
            } else {
                ds.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if(!a.contains(i)){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
