package sulwish;

/*
定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串，所以 base 看起来是这样的：

"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现。


 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L467 {
    public int findSubstringInWraproundString(String s) {
        int n = s.length();
        int[] f = new int[n];
        Map<Character,Integer> ans = new HashMap<>();
        for (int i = n - 1; i > -1; i--){
            if(i == n - 1)f[i] = 1;
            else {
                if(s.charAt(i) == 'z' && s.charAt(i+1) ==  'a'){
                    f[i] = f[i+1] + 1;
                } else {
                    if(s.charAt(i+1) - s.charAt(i) == 1){
                        f[i] = f[i+1] + 1;
                    } else {
                        f[i] = 1;
                    }
                }
            }
            ans.put(s.charAt(i), Math.max(ans.getOrDefault(s.charAt(i), 0), f[i]));
        }
        return ans.values().stream().mapToInt(i -> i).sum();
    }

    public static void main(String[] args) {
        System.out.println(new L467().findSubstringInWraproundString("cac"));
    }
}
