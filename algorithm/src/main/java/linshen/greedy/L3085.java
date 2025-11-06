package linshen.greedy;

//给你一个字符串 word 和一个整数 k。
//
// 如果 |freq(word[i]) - freq(word[j])| <= k 对于字符串中所有下标 i 和 j 都成立，则认为 word 是 k 特殊字
//符串。
//
// 此处，freq(x) 表示字符 x 在 word 中的出现频率，而 |y| 表示 y 的绝对值。
//
// 返回使 word 成为 k 特殊字符串 需要删除的字符的最小数量。

import java.util.*;

public class L3085 {
    //todo hhh 搞不懂才1765分，难道是我想复杂了？？确实是的，cnt的长度只有26，没必要使用堆和前缀和优化了
    public int minimumDeletions(String word, int k) {
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) {
            cnt[c-'a']++;
        }
        Arrays.sort(cnt);
        List<Integer> l = new ArrayList<>();
        for (int x : cnt) {
            if(x != 0)l.add(x);
        }
        int n = l.size();
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + l.get(i-1);
        }
        TreeMap<Integer, Integer> pos = new TreeMap<>();
        for (int i = n - 1; i > -1; i--){
            pos.put(l.get(i), i);
        }
        TreeMap<Integer, Integer> pos2 = new TreeMap<>();
        for (int i = 0; i < n; i++){
            pos2.put(l.get(i), i);
        }


        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            int left = sum[i];
            int right = 0;
            Integer p = pos.higherKey(l.get(i) + k);
            if(p != null){
                right = sum[n] - sum[pos.get(p)] - (n - pos.get(p)) * (l.get(i) + k);
            }
            min = Math.min(min, left + right);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new L3085().minimumDeletions("dabdcbdcdcd", 2));
    }
}
