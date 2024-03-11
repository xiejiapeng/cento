package sulwish;

import java.util.ArrayList;
import java.util.List;

/*
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
public class L438 {
    public List<Integer> findAnagrams(String s, String p) {
        int[] pa = all(p);
        if(s.length() < p.length())return new ArrayList<>();
        else {
            List<Integer> ans = new ArrayList<>();
            int[] sa = all(s.substring(0, p.length()));
            for (int left = 0, right = p.length() - 1; right < s.length(); right++) {
                if(match(sa, pa))ans.add(left);
                if(right + 1 < s.length()){
                    sa[s.charAt(right + 1) - 'a']++;
                    sa[s.charAt(left) - 'a']--;
                    left++;
                }
            }
            return ans;
        }
    }

    private int[] all(String s) {
        int[] ans = new int[26];
        for (int i = 0; i < s.length(); i++){
            ans[s.charAt(i) - 'a']++;
        }
        return ans;
    }

    private boolean match(int[] a, int[] b) {
        for (int i = 0; i < 26; i++){
            if(a[i] != b[i])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L438().findAnagrams("abca","abc"));
    }
}
