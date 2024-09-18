package sulwish;

/*
给你两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为 子序列 的最短字符串。
如果答案不止一个，则可以返回满足条件的 任意一个 答案。

如果从字符串 t 中删除一些字符（也可能不删除），可以得到字符串 s ，那么 s 就是 t 的一个子序列。
 */

import java.util.Arrays;

public class L1092 {
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        //ans[i][j] = str1{i...}+str2{j...}
        String[] x = new String[m+1]; //cur answer
        String[] y = new String[m+1]; // last answer
        for (int i = n; i > -1; i--){
            y = x;
            x = new String[m+1];
            System.out.println(Arrays.toString(y));
            for (int j = m; j > -1; j--){
                if(i == n && j == m){
                    x[j] = "";
                }else if(i == n){
                    x[j] = str2.substring(j);
                } else if(j == m) {
                    x[j] = str1.substring(i);
                } else {
                    String a = "";
                    int min = Integer.MAX_VALUE;
//                    System.out.println(i+","+j);
                    if(str1.charAt(i) == str2.charAt(j)){
                        min = 1 + y[j+1].length();
                        a = str1.charAt(i) + y[j+1];
                    }
                    if(y[j].length() + 1 < min) {
                        min = y[j].length() + 1;
                        a = str1.charAt(i) + y[j];
                    }
                    if(x[j+1].length() + 1 < min){
                        min = x[j+1].length() + 1;
                        a = str2.charAt(j) + x[j+1];
                    }
                    x[j] = a;
                }

            }
        }
        return x[0];
    }

    public static void main(String[] args) {
        System.out.println(new L1092().shortestCommonSupersequence("abac","cab"));
    }
}
