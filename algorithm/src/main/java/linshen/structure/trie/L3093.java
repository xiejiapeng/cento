package linshen.structure.trie;

/*
给你两个字符串数组 wordsContainer 和 wordsQuery 。

对于每个 wordsQuery[i] ，你需要从 wordsContainer 中找到一个与 wordsQuery[i] 有 最长公共后缀 的字符串。
如果 wordsContainer 中有两个或者更多字符串有最长公共后缀，那么答案为长度 最短 的。如果有超过两个字符串有 相同 最短长度，
那么答案为它们在 wordsContainer 中出现 更早 的一个。

请你返回一个整数数组 ans ，其中 ans[i]是 wordsContainer中与 wordsQuery[i] 有 最长公共后缀 字符串的下标。
提示：

1 <= wordsContainer.length, wordsQuery.length <= 104
1 <= wordsContainer[i].length <= 5 * 103
1 <= wordsQuery[i].length <= 5 * 103
wordsContainer[i] 只包含小写英文字母。
wordsQuery[i] 只包含小写英文字母。
wordsContainer[i].length 的和至多为 5 * 105 。
wordsQuery[i].length 的和至多为 5 * 105 。
 */

import java.util.Arrays;
import java.util.TreeSet;

public class L3093 {
    //todo hhhhh 值得记忆的前缀题模板；1. 记住在每个节点中可以构造queue等结构记录经过这个节点的字符串 2. trie中有个root，其不保存数据，数据在children中保存；3.任何一个node，insert意味着插入其子树中，其实就是将children[]的对应元素实例化！！
    static class Node {
        Node[] children;
        TreeSet<Integer> pass;
        String[] wordsContainer;
        public Node(String[] wordsContainer) {
            children = new Node[26];
            pass = new TreeSet<>((o1, o2) -> {
                if(wordsContainer[o1].length() != wordsContainer[o2].length()) {
                    return Integer.compare(wordsContainer[o1].length(), wordsContainer[o2].length());
                } else {
                    return Integer.compare(o1, o2);
                }
            });
            this.wordsContainer = wordsContainer;
        }
        public void insert(char c, int i) {
            if(children[c-'a'] == null){
                children[c-'a'] = new Node(wordsContainer);
            }
            //todo 注意，是加到children的pass中的！！！
            children[c-'a'].pass.add(i);
        }

    }
    static class Trie {
        Node root;
        public Trie(String[] wordsContainer) {
            root = new Node(wordsContainer);
        }
        public void insert(String s, int i) {
            Node node = root;
            for (int j = s.length() - 1; j > -1; j--) {
                char c = s.charAt(j);
                node.insert(c, i);
                node = node.children[c-'a'];
            }
        }

        public int find(String s) {
            Node node = root;
            for (int j = s.length() - 1; j > -1; j--){
                char c = s.charAt(j);
                if(node.children[c-'a'] != null) {
                    node = node.children[c-'a'];
                } else break;
            }
            return node.pass.isEmpty() ? -1 : node.pass.first();
        }

    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie(wordsContainer);
        TreeSet<Integer> de = new TreeSet<>((o1, o2) -> {
            if(wordsContainer[o1].length() != wordsContainer[o2].length()) {
                return Integer.compare(wordsContainer[o1].length(), wordsContainer[o2].length());
            } else {
                return Integer.compare(o1, o2);
            }
        });
        for (int i = 0; i < wordsContainer.length; i++){
            de.add(i);
        }
        int da = de.first();
        for (int i = 0; i < wordsContainer.length; i++){
            trie.insert(wordsContainer[i], i);
        }
        int n = wordsQuery.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++){
            int t = trie.find(wordsQuery[i]);
            ans[i] = t == -1 ? da : t;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L3093().stringIndices(new String[]{"abcd","bcd","xbcd"}, new String[]{"xyz"})));
    }
}
