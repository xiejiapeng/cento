package linshen.dp;

/*
两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。

图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。

老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。

在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。

此外，猫无法移动到洞中（节点 0）。

然后，游戏在出现以下三种情形之一时结束：

如果猫和老鼠出现在同一个节点，猫获胜。
如果老鼠到达洞中，老鼠获胜。
如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：

如果老鼠获胜，则返回 1；
如果猫获胜，则返回 2；
如果平局，则返回 0 。
提示：

3 <= graph.length <= 50
1 <= graph[i].length < graph.length
0 <= graph[i][j] < graph.length
graph[i][j] != i
graph[i] 互不相同
猫和老鼠在游戏中总是可以移动

state
{0,1},{0,1} ... {0001000100}
m
0 -> mouse
1 -> cat

0 -> mouse left
1 -> cat left
 */

import scala.Int;

import java.util.*;
import java.util.stream.Collectors;

public class L913 {
    Map<Long, Integer> dp = new HashMap<>();
    Set<Long> history = new HashSet<>();
    public int catMouseGame(int[][] graph) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++){
            map.put(i, Arrays.stream(graph[i]).boxed().collect(Collectors.toList()));
        }
        long state = 0;
        state = state | (1L);
        state = state | (1L << 1);
        state = state | (1L << 62);
        return can(map, state);
    }

    public int can(Map<Integer, List<Integer>> map, long state) {
        if(dp.containsKey(state)){
            return dp.get(state);
        }
        int ans;
        if(history.contains(state))ans = 0;
        else {
            boolean mouseTurn = (state & (1L << 63)) == 0;
            boolean mouseLeft = (state & (1L << 62)) == 0;
            List<Integer> pos = new ArrayList<>();
            for (int i = 0; i < 55; i++){
                long t = state & (1L << i);
                if(t != 0)pos.add(i+1);
            }
            if(pos.size() == 1) {
                ans = 2;
            } else {
                int mousePos;
                int catPos;
                if(mouseLeft){
                    catPos = pos.get(0);
                    mousePos = pos.get(1);
                } else {
                    catPos = pos.get(1);
                    mousePos = pos.get(0);
                }
                if(mousePos == 0) {
                    ans = 1;
                } else {
                    if(mouseTurn) {
                        List<Integer> next = map.get(mousePos);
                        boolean catWin = true;
                        for (int x : next) {
                            //mouse to x
                            long s = state | (1L << (x-1));
                            //mouse pos to 0
                            s = s & ~(1L << (mousePos - 1));
                            if(x > catPos){
                                s = s & ~(1L << (62));
                            } else {
                                s = s & (1L << (62));
                            }
                            s = s & (1L << 63);
                            if(can(map, s) == 1){
                                catWin = false;
                                break;
                            }
                        }
                        if(catWin) {
                            ans = 2;
                        } else {
                            ans = 1;
                        }
                    } else {
                        List<Integer> next = map.get(catPos);
                        boolean catWin = false;
                        for (int x : next) {
                            //cat to x
                            long s = state | (1L << (x-1));
                            //cat pos to 0
                            s = s & ~(1L << (catPos - 1));
                            if(mousePos > x){
                                s = s & ~(1L << (62));
                            } else {
                                s = s & (1L << (62));
                            }
                            s = s & ~(1L << (63));
                            if(can(map, s) == 1){
                                catWin = true;
                                break;
                            }
                        }
                        if(catWin) {
                            ans = 2;
                        } else {
                            ans = 1;
                        }
                    }
                }

            }

        }
        dp.put(state, ans);
        return ans;
    }
}
