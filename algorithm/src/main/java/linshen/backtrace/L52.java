package linshen.backtrace;

/*
n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 */

import java.util.LinkedList;
import java.util.List;

public class L52 {
    int cnt = 0;
    public int totalNQueens(int n) {
        dfs(n, 0, new LinkedList<>());
        return cnt;
    }

    private void dfs(int n, int cur, LinkedList<Integer> l) {
        if(cur == n) {
            cnt++;
        } else {
            if(l.isEmpty()) {
                for (int j = 0; j < n; j++){
                    l.addLast(j);
                    dfs(n, cur + 1, l);
                    l.removeLast();
                }
            } else {
                for (int i = 0; i < n; i++){
                    boolean can = true;

                    for (int j = 0; j < l.size(); j++){
                        int c = l.get(j);
                        if(i == c || Math.abs(i - c) == Math.abs(cur - j)) {
                            can = false;
                        }
                    }

                    if(can) {
                        l.addLast(i);
                        dfs(n, cur + 1, l);
                        l.removeLast();
                    }

                }
            }

        }
    }

    public static void main(String[] args) {
        System.out.println(new L52().totalNQueens(8));
    }
}
