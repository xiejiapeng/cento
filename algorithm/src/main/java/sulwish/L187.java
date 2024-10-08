package sulwish;

/*
DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。

例如，"ACGAATTCCG" 是一个 DNA序列 。
在研究 DNA 时，识别 DNA 中的重复序列非常有用。

给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的
长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L187 {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> all = new HashSet<>();
        Set<String> ans = new HashSet<>();
        if(s.length() < 10)return new ArrayList<>();
        else {
            String last = s.substring(0,10);
            all.add(last);
            for (int i = 10; i< s.length(); i++){
                last += s.charAt(i);
                last = last.substring(1);
                if(all.contains(last)){
                    ans.add(last);
                }
                all.add(last);
            }
            return new ArrayList<>(ans);
        }
    }
}
