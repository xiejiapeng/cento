package index.dualpointer;
/*
求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。

如果方程没有解或存在的解不为整数，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。

题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 */

import java.util.Arrays;

public class L640 {
    public String solveEquation(String equation) {
        String[] es = equation.split("=");
        int[] left = parse(es[0]);
        int[] right = parse(es[1]);
//        System.out.println(Arrays.toString(left));
//        System.out.println(Arrays.toString(right));
        int x = left[0] - right[0];
        int y = left[1] - right[1];
        if(x == 0) {
            if(y == 0)return "Infinite solutions";
            else return "No solution";
        } else {
            return "x="+(-y / x);
        }
    }

    private int[] parse(String s) {
        int x = 0;
        int v = 0;
        s = '+' + s;
        int i = 0;
        int n =s.length();
        while (i < n) {
            if(s.charAt(i) == '+' || s.charAt(i) == '-') {
                int cur = 0;
                int j = i + 1;
                while (j < n && s.charAt(j) != '+' && s.charAt(j) != '-' && s.charAt(j) != 'x') {
                    cur = cur * 10 + (s.charAt(j)-'0');
                    j++;
                }
                if(j<n && s.charAt(j) == 'x') {
                    if(j==i+1&&cur == 0)cur = 1;
                    if(s.charAt(i)=='+')x += cur;
                    else x -= cur;
                    i=j+1;
                } else {
                    if(s.charAt(i)=='+')v += cur;
                    else v -= cur;
                    i=j;
                }

            }
        }
        return new int[]{x,v};
    }

    public static void main(String[] args) {
        System.out.println(new L640().solveEquation("0x=0"));
    }
}
