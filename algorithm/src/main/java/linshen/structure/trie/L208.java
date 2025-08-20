package linshen.structure.trie;

/*
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；
否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；
否则，返回 false 。
 */

public class L208 {
    public static class Trie {
        Node[] children;
        class Node {
            boolean endHere;
            Node[] children;

            public Node(){
                endHere = false;
                children = new Node[26];
            }

            public void insert(String s) {
                if(s.length() == 1) endHere = true;
                else {
                    if(children[s.charAt(1) - 'a'] == null){
                        children[s.charAt(1) - 'a'] = new Node();
                    }
                    children[s.charAt(1) - 'a'].insert(s.substring(1));
                }
            }

            public boolean search(String s) {
                if(s.length() == 1) {
                    return endHere;
                } else {
                    if(children[s.charAt(1) - 'a'] == null)return false;
                    else return children[s.charAt(1) - 'a'].search(s.substring(1));
                }
            }

            public boolean startWith(String s) {
                if(s.length() == 1)return true;
                else {
                    if(children[s.charAt(1) - 'a'] == null)return false;
                    else return children[s.charAt(1) - 'a'].startWith(s.substring(1));
                }
            }
        }

        public Trie() {
            children = new Node[26];
        }

        public void insert(String word) {
            if(children[word.charAt(0) - 'a'] == null)children[word.charAt(0) - 'a'] = new Node();
            children[word.charAt(0) - 'a'].insert(word);
        }

        public boolean search(String word) {
            if(children[word.charAt(0) - 'a'] == null)return false;
            else return children[word.charAt(0) - 'a'].search(word);
        }

        public boolean startsWith(String prefix) {
            if(children[prefix.charAt(0) - 'a'] == null)return false;
            else return children[prefix.charAt(0) - 'a'].startWith(prefix);
        }
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple");
        System.out.println(t.startsWith("app"));
    }
}
