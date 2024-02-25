package sulwish;

import java.util.*;

public class L310 {
    public static void main(String[] args) {
        int[][] edges = new int[][]{{1, 0}, {1, 2}, {1, 3}};
        System.out.println(new L310().findMinHeightTrees(4, edges));
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Arrays.asList(0);
        Map<Integer, Set<Integer>> em = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            em.putIfAbsent(x, new HashSet<>());
            em.putIfAbsent(y, new HashSet<>());
            em.get(x).add(y);
            em.get(y).add(x);
        }


        Set<Integer> candidates = new HashSet<>();
        for (int x : em.keySet()) {
            if (em.get(x).size() == 1) candidates.add(x);
        }
        int level = 0;
        while (!candidates.isEmpty()) {
            level++;
            Set<Integer> nc = new HashSet<>();
            for (int x : candidates) {
                for (int y : em.get(x)) {
                    if (candidates.contains(y)) return Arrays.asList(x, y);
                    else {
                        em.get(y).remove(x);
                        if (em.get(y).size() == 1) nc.add(y);
                    }
                }
            }

            if (nc.size() == 1) return new ArrayList<>(nc);
            candidates = nc;
        }
        return new ArrayList<>();
    }
}
