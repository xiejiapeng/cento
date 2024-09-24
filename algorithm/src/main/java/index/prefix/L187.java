package index.prefix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。

例如，"ACGAATTCCG" 是一个 DNA序列 。
在研究 DNA 时，识别 DNA 中的重复序列非常有用。

给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的
长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。

0 <= s.length <= 105
s[i]=='A'、'C'、'G' or 'T'
 */

public class L187 {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> ans = new HashSet<>();
        if(s == null || s.length() < 10)return new ArrayList<>(ans);
        else {
            Set<String> see = new HashSet<>();
            String a = s.substring(0, 10);
            see.add(a);
            for (int i = 10; i < s.length(); i++){
                a = a.substring(1);
                a = a + s.charAt(i);
                if(see.contains(a))ans.add(a);
                else see.add(a);
            }
            return new ArrayList<>(ans);
        }
    }
}
