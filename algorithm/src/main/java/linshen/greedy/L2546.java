package linshen.greedy;

/*
给你两个下标从 0 开始的 二元 字符串 s 和 target ，两个字符串的长度均为 n 。你可以对 s 执行下述操作 任意 次：

选择两个 不同 的下标 i 和 j ，其中 0 <= i, j < n 。
同时，将 s[i] 替换为 (s[i] OR s[j]) ，s[j] 替换为 (s[i] XOR s[j]) 。
例如，如果 s = "0110" ，你可以选择 i = 0 和 j = 2，然后同时将 s[0] 替换为 (s[0] OR s[2] = 0 OR 1 = 1)，并将 s[2] 替换为 (s[0] XOR s[2] = 0 XOR 1 = 1)，最终得到 s = "1110" 。

如果可以使 s 等于 target ，返回 true ，否则，返回 false 。
0,0 -> 0,0
0,1 -> 1,1
1,0 -> 1,1
1,1 -> 1,0

0 1000
0 0000
 */

import java.util.HashSet;
import java.util.Set;

public class L2546 {
    //todo hh 通过or或nor的规律题，稍微记住就行
    public boolean makeStringsEqual(String s, String target) {
        Set<Integer> ones = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                ones.add(i);
            }
        }
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if(cs[i] != target.charAt(i)) {
                if(cs[i] == '0') {
                    if(!ones.isEmpty()){
                        ones.add(i);
                    }
                    else return false;
                } else {
                    if(ones.size() < 2){
                        if(i + 1 < n){
                            ones.remove(i);
                            ones.add(i+1);
                            cs[i+1] = '1';
                        } else {
                            return false;
                        }

                    } else {
                        ones.remove(i);
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L2546().makeStringsEqual("10110001", "01001110"));
    }
}
