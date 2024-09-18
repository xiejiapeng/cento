package sulwish;

/*
如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，
那么该字符串是 单调递增 的。

给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。

返回使 s 单调递增的最小翻转次数。
 */

public class L926 {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + (s.charAt(i-1) - '0');
        }
        int total = sum[n];
        int ans = total;
        //1从i开始
        for (int i = 0; i < n; i++){
            int cnt = 0;
            if(s.charAt(i) == '0'){
                cnt++;
            }
            int left = sum[i];
            int right = n - i - 1 - (total - sum[i+1]);

            cnt += left;
            cnt += right;

            System.out.println("i="+i+",left="+left+",right="+right);

            ans = Math.min(ans, cnt);
        }
        return ans;
    }
}
