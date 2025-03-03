package linshen.zhizhen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/*
给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：

子串中不同字母的数目必须小于等于 maxLetters 。
子串的长度必须大于等于 minSize 且小于等于 maxSize 。
 */
public class L1297 {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        //todo 可以简化到只枚举 minSize
        return IntStream.range(minSize, maxSize+1).map(i -> maxF(s, maxLetters, i))
                .max().orElse(0);
    }

    public int maxF(String s, int maxLetters, int k) {
        int n = s.length();
        Map<String, Integer> ans = new HashMap<>();
        Map<Character, Integer> all = new HashMap<>();
        for (int right = 0; right < n; right++) {
            int left = right - k + 1;
            all.put(s.charAt(right), all.getOrDefault(s.charAt(right), 0) + 1);
            if(left - 1 > -1) {
                all.put(s.charAt(left-1), all.get(s.charAt(left-1)) - 1);
                if(all.get(s.charAt(left - 1)) == 0)all.remove(s.charAt(left - 1));
            }
            if(left >= 0) {
                if(all.keySet().size() <= maxLetters) {
                    String t = s.substring(left, right+1);
                    ans.put(t, ans.getOrDefault(t, 0) + 1);
                }
            }
        }
        return ans.values().stream().mapToInt(i -> i).max().orElse(0);
    }

    public static void main(String[] args) {
        /*
            4: aaac, aacb, acbc
         */
        System.out.println(new L1297().maxFreq("aaaaacbc", 2,4,6));
    }
}
