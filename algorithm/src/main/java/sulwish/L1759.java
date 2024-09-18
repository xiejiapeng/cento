package sulwish;

/*
给你一个字符串 s ，返回 s 中 同质子字符串 的数目。由于答案可能很大，只需返回对 109 + 7 取余 后的结果。

同质字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同质字符串。

子字符串 是字符串中的一个连续字符序列。

abbcccaa
 */

public class L1759 {
    int mod = (int)(1e9+7);
    public int countHomogenous(String s) {
        int n = s.length();
        long ans = 0;
        for (int left = 0, right = 0; left < n;) {
            long cnt = 0;
            while (right < n && s.charAt(right) == s.charAt(left)) {
                cnt++;
                right++;
                cnt %= mod;
            }
            System.out.println(cnt);
            ans += (cnt + (cnt-1)*cnt/2);
            System.out.println(ans);
            left = right;
        }
        return (int)(ans % mod);
    }
}
