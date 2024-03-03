package sulwish;

import java.util.Arrays;

/*
你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。

如果你能使这个正方形，则返回 true ，否则返回 false 。
 */
public class L473 {
    public static void main(String[] args) {
        System.out.println(new L473().makesquare(new int[]{12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}));
    }

    public boolean makesquare(int[] matchsticks) {
        long total = Arrays.stream(matchsticks).mapToLong(v -> (long) v).sum();
        if (total % 4 != 0) return false;
        long avg = total / 4;
        return dfs(matchsticks, 0, 0, 0, 0, avg, 0);
    }

    private boolean dfs(int[] matchsticks, long up, long down, long left, long right, long avg, int cur) {
        if (cur == matchsticks.length) {
            if (up == down && down == left && left == right) return true;
            else return false;
        } else {
            boolean can = false;
            //add to up
            if (up + matchsticks[cur] <= avg && up + matchsticks[cur] <= down) {
                can |= dfs(matchsticks, up + matchsticks[cur], down, left, right, avg, cur + 1);
            }

            if (down + matchsticks[cur] <= avg && down + matchsticks[cur] <= left) {
                can |= dfs(matchsticks, up, down + matchsticks[cur], left, right, avg, cur + 1);
            }

            if (left + matchsticks[cur] <= avg && left + matchsticks[cur] <= right) {
                can |= dfs(matchsticks, up, down, left + matchsticks[cur], right, avg, cur + 1);
            }

            if (right + matchsticks[cur] <= avg) {
                can |= dfs(matchsticks, up, down, left, right + matchsticks[cur], avg, cur + 1);
            }

            return can;
        }
    }
}
