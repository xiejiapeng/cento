package index.dfs;

/*
你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。

如果你能使这个正方形，则返回 true ，否则返回 false 。


 */

import java.util.Arrays;

public class L473 {
    boolean ans = false;
    public boolean makesquare(int[] matchsticks) {
        int len = Arrays.stream(matchsticks).sum();
        if(len % 4 != 0)return false;
        if(matchsticks.length < 4)return false;
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        dfs(matchsticks,1,up+matchsticks[0],down,left,right,len/4);
        return ans;
    }

    private void dfs(int[] matchsticks, int id, int up, int down, int left, int right, int target) {
        if(ans)return;
        if(id == matchsticks.length) {
            if(up == down && up == left && up == right) {
                ans = true;
            }
        } else {
            if(up + matchsticks[id] <= target) {
                dfs(matchsticks, id+1, up + matchsticks[id], down, left, right, target);
            }
            if(down + matchsticks[id] <= target) {
                dfs(matchsticks, id+1, up, down+matchsticks[id],left,right, target);
            }
            if(left + matchsticks[id] <= target) {
                dfs(matchsticks, id+1,up,down,left+matchsticks[id],right,target);
            }
            if(right + matchsticks[id] <= target) {
                dfs(matchsticks, id+1,up,down,left,right+matchsticks[id], target);
            }
        }
    }
}
