package index.bfs;

/*
n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。

每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。

如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。

就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。

给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：

dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
返回表示最终状态的字符串。
 */

import java.util.LinkedList;
import java.util.Queue;

public class L838 {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] ans = new int[n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < dominoes.length(); i++) {
            char c = dominoes.charAt(i);
            if (c == 'L') {
                queue.add(new int[]{i, -1});
                ans[i] = -1;
            } else if (c == 'R') {
                queue.add(new int[]{i, 1});
                ans[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] tmp = new int[n];
            while (size-- > 0) {
                int[] p = queue.poll();
                if (p[1] == -1) {
                    if (p[0] - 1 >= 0 && ans[p[0] - 1] == 0) {
                        tmp[p[0] - 1] -= 1;
                    }

                } else if (p[1] == 1) {
                    if (p[0] + 1 < n && ans[p[0] + 1] == 0) {
                        tmp[p[0] + 1] += 1;
                    }
                }

            }
            for (int i = 0; i < n; i++) {
                if (tmp[i] == -1) {
                    ans[i] = -1;
                    queue.add(new int[]{i, -1});
                } else if (tmp[i] == 1) {
                    ans[i] = 1;
                    queue.add(new int[]{i, 1});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (ans[i] == -1) {
                sb.append("L");
            } else if (ans[i] == 1) {
                sb.append("R");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();

    }
}
