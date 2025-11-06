package linshen.structure.trie;

/*
给你一个长度为 n 的数组 words ，该数组由 非空 字符串组成。

定义字符串 term 的 分数 等于以 term 作为 前缀 的 words[i] 的数目。

例如，如果 words = ["a", "ab", "abc", "cab"] ，那么 "ab" 的分数是 2 ，
因为 "ab" 是 "ab" 和 "abc" 的一个前缀。
返回一个长度为 n 的数组 answer ，其中 answer[i] 是 words[i] 的每个非空前缀的分数 总和 。

注意：字符串视作它自身的一个前缀。
 */

import java.util.Arrays;

public class L2416 {
    static class Trie {
        Node[] children;
        static class Node {
            boolean endHere;
            int cnt = 0;
            Node[] children;
            int ssum = -1;

            public Node(){
                endHere = false;
                children = new Node[26];
            }

            public void insert(String s) {
                if(s.length() == 1) {
                    endHere = true;
                    cnt++;
                }
                else {
                    if(children[s.charAt(1) - 'a'] == null){
                        children[s.charAt(1) - 'a'] = new Node();
                    }
                    children[s.charAt(1) - 'a'].insert(s.substring(1));
                }
            }

            public int startWith(String s) {
                int t = sum();
                ssum = t;
                if(s.length() > 1) {
                    if(children[s.charAt(1) - 'a'] != null) {
                        t += children[s.charAt(1) - 'a'].startWith(s.substring(1));
                    }
                }
                return t;
            }

            //todo hhh 记住，不要这样写；直接在insert的时候统计经过某个node的字符串个数（而不是恰好在这里停止的字符串个数），这样就不要加它的child了；
            private int sum() {
                if(ssum == -1) {
                    int ans = 0;
                    ans += cnt;
                    for (Node c : children) {
                        if(c != null) ans += c.sum();
                    }
                    ssum = ans;
                }
                return ssum;
            }
        }

        public Trie() {
            children = new Node[26];
        }

        public void insert(String word) {
            if(children[word.charAt(0) - 'a'] == null)children[word.charAt(0) - 'a'] = new Node();
            children[word.charAt(0) - 'a'].insert(word);
        }

        public int startsWith(String prefix) {
            if(children[prefix.charAt(0) - 'a'] == null)return 0;
            else return children[prefix.charAt(0) - 'a'].startWith(prefix);
        }
    }
    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        int n = words.length;
        int[] ans = new int[n];
        for (int i = 0; i < words.length; i++){
            ans[i] = trie.startsWith(words[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L2416().sumPrefixScores(new String[]{"abc", "ab", "bc", "b"})));
    }
}
