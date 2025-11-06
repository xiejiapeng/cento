package linshen.backtrace;

/*
你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。
你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，
而且每根火柴棒必须 使用一次 。

如果你能使这个正方形，则返回 true ，否则返回 false 。

提示:
1 <= matchsticks.length <= 15
1 <= matchsticks[i] <= 10^8
 */

import java.util.*;

public class L473 {
    Set<List<Integer>> schemes = new HashSet<>();
    boolean ans = false;
    public boolean makesquare(int[] matchsticks) {
        Arrays.sort(matchsticks);
        int total = Arrays.stream(matchsticks).sum();
        if(total % 4 != 0){
            return false;
        }
        int target = total / 4;
        dfs(matchsticks, 0, target, new LinkedList<>());
        Map<Integer, Integer> have = new HashMap<>();
        for (int x : matchsticks) {
            have.put(x, have.getOrDefault(x, 0) + 1);
        }
        choose(matchsticks, have, new HashMap<>(), new LinkedList<>());
        return ans;
    }

    private void choose(int[] matchsticks, Map<Integer, Integer> have, Map<Integer, Integer> used, LinkedList<List<Integer>> l) {
        if(l.size() == 4) {
            int total = l.stream().mapToInt(List::size).sum();
            if(total == matchsticks.length) {
                ans = true;
            }
        } else {
            for (List<Integer> s : schemes) {
                boolean valid = true;
                for (int x : s) {
                    if(used.getOrDefault(x, 0) >= have.get(x)) {
                        valid = false;
                    }
                }
                if(valid) {
                    for (int x : s) {
                        used.put(x, used.getOrDefault(x, 0) + 1);
                    }
                    l.addLast(s);
                    choose(matchsticks, have, used, l);
                    l.removeLast();
                    for (int x : s) {
                        used.put(x, used.get(x) - 1);

                    }
                }
            }
        }
    }

    //todo hhhhh 记住，schemes中存的不是下标，如果存下标，schemes会很大，特别是重复值元素的下标都不同
    private void dfs(int[] matchsticks, int cur, int target, LinkedList<Integer> l) {
        int len = l.stream().mapToInt(i -> i).sum();
        if(cur == matchsticks.length) {
            if(len == target) {
                schemes.add(new ArrayList<>(l));
            }
        } else {
            dfs(matchsticks, cur + 1, target, l);
            if(len < target) {
                l.addLast(matchsticks[cur]);
                dfs(matchsticks, cur + 1, target, l);
                l.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L473().makesquare(new int[]{1,2,1,2,3,1,1,1,1,1,1,1,4}));
    }
}
