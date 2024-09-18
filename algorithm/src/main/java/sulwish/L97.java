package sulwish;

/*
给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空
子字符串
：

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
注意：a + b 意味着字符串 a 和 b 连接。

|---i---| s3
|--j--| s2
|-k-|
 */

import java.util.Arrays;

public class L97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int a = s1.length();
        int b = s2.length();
        int c = s3.length();
        if(a + b != c)return false;
        else {
            boolean[][] dp = new boolean[c+1][b+1];
            for (int i = 0; i <= c; i++){
                for (int j = 0; j <= a; j++){
                    if(i < j)dp[i][j] = false;
                }
            }
            dp[0][0] = true;
            for(int i = 1; i <= c; i++){
                for(int j = 0; j <= i && j <= b; j++){
                    int k = i - j;
                    if(k > a)dp[i][j] = false;
                    else {
                        if(i==1&&j==1){
                            System.out.println("f");
                        }
                        System.out.println("i="+i+",j="+j+",k="+k);
                        //s3 ends with s2
                        for (int t = 0; i-1-t>=0&&j-1-t>=0&&(s2.charAt(j-1-t)==s3.charAt(i-1-t)); t++) {
                            System.out.println("k");
                            dp[i][j] |= dp[i-1-t][j-1-t];
                        }
                        //s3 ends with s1
                        for(int t = 0; i-1-t>=0&&k-1-t>=0&&(s1.charAt(k-1-t)==s3.charAt(i-1-t)); t++) {
                            dp[i][j] |= dp[i-1-t][j];
                        }
                    }

                }
            }

            for (int i = 0; i < c; i++){
                System.out.println(Arrays.toString(dp[i]));
            }
            return dp[c][a];
        }
    }

    public static void main(String[] args) {
        System.out.println(new L97().isInterleave("","b","b"));
    }
}
