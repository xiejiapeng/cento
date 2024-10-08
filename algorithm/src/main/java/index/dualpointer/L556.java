package index.dualpointer;

/*
给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，
并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。

注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，
同样返回 -1 。
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L556 {
    public int nextGreaterElement(int x) {
        String s = "" + x;
        int n = s.length();
        List<Integer> l = new ArrayList<>();
        for (int i = n - 1; i > -1; i--){
            if(i+1<n && s.charAt(i)<s.charAt(i+1)) {
                int t = find(l, s, 0, l.size()-1, s.charAt(i));
                StringBuilder sb = new StringBuilder();
                sb.append(s.charAt(l.get(t)));
                for (int j : l) {
                    if(j == l.get(t)) {
                        sb.append(s.charAt(i));
                    } else {
                        sb.append(s.charAt(j));
                    }
                }

                String y = s.substring(0, i);
                y = y + sb;
                long v = Long.valueOf(y);
                if(v > Integer.MAX_VALUE)return -1;
                else return (int)v;
            } else {
                l.add(i);
            }
        }
        return -1;
    }

    private int find(List<Integer> l, String s, int left, int right, char x) {
        if(left == right) {
            return left;
        } else if(left == right - 1) {
            if(s.charAt(l.get(left)) > x)return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(s.charAt(l.get(mid)) > x)return find(l, s, left, mid, x);
            else {
                return find(l, s, mid + 1, right, x);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L556().nextGreaterElement(234780189));
    }
}
