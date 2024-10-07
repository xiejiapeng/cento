package index.hashtable;

/*
请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）


注意：

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
空白格用 '.' 表示。
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L36 {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> rows = new HashMap<>();
        Map<Integer, Set<Integer>> cols = new HashMap<>();
        Map<Integer, Set<Integer>> blocks = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int row = i;
                    int col = j;
                    int block = i / 3 * 3 + j / 3;
                    int x = board[i][j] - '0';
                    if (rows.containsKey(row) && rows.get(row).contains(x)) return false;
                    if (cols.containsKey(col) && cols.get(col).contains(x)) return false;
                    if (blocks.containsKey(block) && blocks.get(block).contains(x)) return false;
                    rows.putIfAbsent(row, new HashSet<>());
                    rows.get(row).add(x);
                    cols.putIfAbsent(col, new HashSet<>());
                    cols.get(col).add(x);
                    blocks.putIfAbsent(block, new HashSet<>());
                    blocks.get(block).add(x);
                }
            }
        }
        return true;
    }
}
