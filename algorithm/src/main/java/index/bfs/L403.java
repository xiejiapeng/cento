package index.bfs;

/*
一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。
青蛙可以跳上石子，但是不可以跳入水中。

给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃 1 个单位（即只能从单元格 1 跳至单元格 2 ）。

如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。
另请注意，青蛙只能向前方（终点的方向）跳跃。
 */

import java.util.*;
import java.util.stream.Collectors;

public class L403 {
    public boolean canCross(int[] stones) {
        int last = stones[stones.length - 1];
        Set<Integer> all = Arrays.stream(stones).boxed().collect(Collectors.toSet());
        Queue<int[]> pos = new LinkedList<>();
        Set<String> added = new HashSet<>();
        pos.add(new int[]{0, 0});
        while (!pos.isEmpty()) {
            int[] p = pos.poll();
            int x = p[0];
            int y = p[1];
            if (x == last) return true;
            if (y - 1 > 0 && all.contains(x + y - 1)) {
                int[] np = new int[]{x + y - 1, y - 1};
                if (!added.contains((x + y - 1) + "_" + (y - 1))) {
                    pos.add(np);
                    added.add((x + y - 1) + "_" + (y - 1));
                }
            }
            if (y > 0 && all.contains(x + y)) {
                int[] np = new int[]{x + y, y};
                if (!added.contains((x + y) + "_" + (y))) {
                    pos.add(np);
                    added.add((x + y) + "_" + (y));
                }
            }
            if (y + 1 > 0 && all.contains(x + y + 1)) {
                int[] np = new int[]{x + y + 1, y + 1};
                if (!added.contains((x + y + 1) + "_" + (y + 1))) {
                    pos.add(np);
                    added.add((x + y + 1) + "_" + (y + 1));
                }
            }
        }
        return false;
    }
}
