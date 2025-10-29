package linshen.backtrace;

import linshen.structure.trie.L208;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。

单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
同一个单元格内的字母在一个单词中不允许被重复使用。

提示：
m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] 是一个小写英文字母
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] 由小写英文字母组成
words 中的所有字符串互不相同
 */

public class L212 {
    Set<String> ans = new HashSet<>();
    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        //todo hhhhh 对于在图上游走型的回溯，起始位置要visited=false
        //有些回溯，比如所有排列，会走到无路可走的情况，即cur=arr.length，因此它的state为遍历cur之前的状态
        //对于这种图的例子，不会存在无路可走，state状态设置为处理过cur之后的状态
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                String s = "" + board[i][j];
                if(trie.startsWith(s)) {
                    visited[i][j] = true;
                    dfs(board, board.length, board[0].length, trie, i, j, visited, "" + board[i][j]);
                    visited[i][j] = false;
                }

            }
        }
        return new ArrayList<>(ans);
    }

    //todo hhhhh 这个思路没有问题，但是用trie会有点小问题；words虽然很短，但很多，建立trie会很慢，可能会TLE
    //不如直接将word和prefix放入到set中提前建立好
    private void dfs(char[][] board, int m, int n, Trie trie, int curi, int curj, boolean[][] visited, String l) {
        if(trie.search(l)) {
            ans.add(l);
        }
        for (int[] dir : dirs) {
            int x = curi + dir[0];
            int y = curj + dir[1];
            if(x >= 0 && x < m && y >= 0 && y < n) {
                if(!visited[x][y]) {
                    String t = l + board[x][y];
                    if (trie.startsWith(t)) {
                        visited[x][y] = true;
                        dfs(board, m, n, trie, x, y, visited, t);
                        visited[x][y] = false;
                    }
                }
            }
        }
    }

    public static class Trie {
        Trie.Node[] children;
        class Node {
            boolean endHere;
            Trie.Node[] children;

            public Node(){
                endHere = false;
                children = new Trie.Node[26];
            }

            public void insert(String s) {
                if(s.length() == 1) endHere = true;
                else {
                    if(children[s.charAt(1) - 'a'] == null){
                        children[s.charAt(1) - 'a'] = new Trie.Node();
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
            children = new Trie.Node[26];
        }

        public void insert(String word) {
            if(children[word.charAt(0) - 'a'] == null)children[word.charAt(0) - 'a'] = new Trie.Node();
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
}
