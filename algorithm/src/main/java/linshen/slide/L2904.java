package linshen.slide;

/*
给你一个二进制字符串 s 和一个正整数 k 。

如果 s 的某个子字符串中 1 的个数恰好等于 k ，则称这个子字符串是一个 美丽子字符串 。

令 len 等于 最短 美丽子字符串的长度。

返回长度等于 len 且字典序 最小 的美丽子字符串。如果 s 中不含美丽子字符串，则返回一个 空 字符串。
 */
public class L2904 {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int min = Integer.MAX_VALUE;
        String ans = "";
        int cnt = 0;
        for (int left = 0, right = 0; right < n; right++){
            cnt += (s.charAt(right) - '0');
            if(cnt < k)continue;
            //todo 注意这里是>=
            while (left < right && cnt - (s.charAt(left) - '0') >= k) {
                cnt -= (s.charAt(left) - '0');
                left++;
            }
            if(cnt == k) {
                System.out.println(s.substring(left, right+1));
                if(right - left + 1 < min) {
                    min = right - left + 1;
                    ans = s.substring(left, right+1);
                } else if(right - left + 1 == min) {
                    if(s.substring(left, right+1).compareTo(ans) < 0) {
                        ans = s.substring(left, right+1);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L2904().shortestBeautifulSubstring("100011001",3));
    }
}
