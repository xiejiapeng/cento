package sulwish;

import java.util.HashMap;
import java.util.Map;

public class L211 {
    public static void main(String[] args) {
        WordDictionary w = new WordDictionary();
        w.addWord("at");
        w.addWord("and");
        w.addWord("an");
        w.addWord("add");

        System.out.println(w.search("a"));

    }

    static class WordDictionary {

        Node root;

        public WordDictionary() {
            root = new Node('.');
        }

        public void addWord(String word) {
            root.insert(word);
        }

        public boolean search(String word) {
            return root.search(word);
        }

        class Node {
            char c;
            Map<Character, Node> children;
            boolean stop = false;

            public Node(char c) {
                this.c = c;
                this.children = new HashMap<>();
            }

            public void insert(String str) {
                if (str.length() == 0) stop = true;
                else {
                    char c = str.charAt(0);
                    children.putIfAbsent(c, new Node(c));
                    children.get(c).insert(substr(str));
                }
            }

            public boolean search(String str) {
                char c = str.charAt(0);
                if (c == '.') {
                    String s = substr(str);
                    boolean find = false;
                    for (Node node : children.values()) {
                        if (s.length() == 0) find |= node.stop;
                        else find |= node.search(s);
                    }
                    return find;
                } else {
                    if (children.containsKey(c)) {
                        String s = substr(str);
                        Node node = children.get(c);
                        if (s.length() == 0) return node.stop;
                        else return node.search(s);
                    } else {
                        return false;
                    }
                }
            }

            public String substr(String str) {
                if (str.length() <= 1) return "";
                else return str.substring(1);
            }
        }
    }
}
