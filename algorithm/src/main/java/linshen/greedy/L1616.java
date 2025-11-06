package linshen.greedy;

/*
给你两个字符串 a 和 b ，它们长度相同。请你选择一个下标，将两个字符串都在 相同的下标 分割开。
由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix + asuffix ，
同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。
请你判断 aprefix + bsuffix 或者 bprefix + asuffix 能否构成回文串。

当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。
比方说， s = "abc" 那么 "" + "abc" ， "a" + "bc" ， "ab" + "c" 和 "abc" + "" 都是合法分割。

如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。

注意， x + y 表示连接字符串 x 和 y 。
提示：

1 <= a.length, b.length <= 105
a.length == b.length
a 和 b 都只包含小写英文字母
 */

public class L1616 {
    //todo hhhhh 没能解决，记住吧；但是原来的解法也是基本正确，只是忽视了由a来补的情况；
    //回文没有固定套路，不能想的太复杂；回文是很强很强的约束；比如这题的a前缀+b后缀构成回文，
    //对a和b的要求极高，直接遍历就能发现解法；只是当第一次不满足时，存在用a补和b补两种情况
    //用a补不太容易想到，因为天然觉得是在a上选个地方切一刀，a的选择就固定了，剩余的都是b
    //但是要记住，即使a和b不匹配了，也不意味着要在这里切一刀！！
    public boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }

    private boolean check(String a, String b) {
        int l = 0, r = a.length() - 1;
        while (l < r && a.charAt(l) == b.charAt(r)) {
            l++;
            r--;
        }
        // 此时如果退出了，说明 a[l] != b[r]
        // 剩下要么 a[l..r] 本身是回文，要么 b[l..r] 本身是回文
        return isPalindrome(a, l, r) || isPalindrome(b, l, r);
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new L1616().checkPalindromeFormation("xbdef", "xecab"));
    }

}
