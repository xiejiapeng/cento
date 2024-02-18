package sulqn;

public class Trie {
    Node root;

    public Trie() {
        root = new Node('.');
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple");
        System.out.println(t.search("apple"));
        t.insert("app");
        System.out.println(t.search("app"));
    }

    public void insert(String word) {
        char w = word.charAt(0);
        if (root.children[w - 'a'] == null) {
            Node child = new Node(w);
            root.children[w - 'a'] = child;
            if (word.length() == 1) {
                root.endsWith[w - 'a'] = true;
            } else {
                child.insert(word.substring(1));
            }

        } else {
            Node child = root.children[w - 'a'];
            if (word.length() == 1) {
                root.endsWith[w - 'a'] = true;
            } else {
                child.insert(word.substring(1));
            }
        }
    }

    public boolean search(String word) {
        return root.search(word);
    }

    public boolean startsWith(String prefix) {
        return root.prefix(prefix);
    }

    class Node {
        char c;
        Node[] children;
        boolean[] endsWith;

        public Node(char c) {
            this.c = c;
            children = new Node[26];
            endsWith = new boolean[26];
        }

        public void insert(String a) {
            char w = a.charAt(0);
            if (children[w - 'a'] == null) {
                Node child = new Node(w);
                children[w - 'a'] = child;

            }

            Node child = children[w - 'a'];
            if (a.length() == 1) {
                endsWith[w - 'a'] = true;
            } else {
                child.insert(a.substring(1));
            }


        }

        public boolean search(String a) {
            if (children[a.charAt(0) - 'a'] == null) return false;
            else {
                if (a.length() == 1) return endsWith[a.charAt(0) - 'a'];
                else {
                    return children[a.charAt(0) - 'a'].search(a.substring(1));
                }
            }
        }

        public boolean prefix(String a) {
            if (children[a.charAt(0) - 'a'] == null) return false;
            else {
                if (a.length() == 1) return true;
                else return children[a.charAt(0) - 'a'].prefix(a.substring(1));
            }
        }
    }
}
