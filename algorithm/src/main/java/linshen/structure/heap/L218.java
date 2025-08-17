package linshen.structure.heap;

import java.util.*;

public class L218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> current = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] != o2[0])return Integer.compare(o1[0], o2[0]);
            else return Integer.compare(o1[3], o2[3]);
        });
        //todo hhh stack不适合用linkedlist或者stack，因为要remove的元素不一定总是在末尾；而这两种结构无法移除中间的元素，只有map能做到
        TreeMap<Integer,Integer> stack = new TreeMap<>();
        for (int i = 0; i < buildings.length; i++){
            int[] bd = buildings[i];
            int l = bd[0];
            int r = bd[1];
            int h = bd[2];
            current.add(new int[]{l, 1, h, i});
            current.add(new int[]{r, -1, h, i});
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (!current.isEmpty()) {
            int pos = current.peek()[0];
            int ch = stack.isEmpty() ? 0 : stack.lastKey();
            while (!current.isEmpty() && current.peek()[0] == pos) {
                int[] c = current.poll();
                if(c[1] == 1) {
                    stack.put(c[2], stack.getOrDefault(c[2], 0) + 1);
                } else {
                    if(stack.get(c[2]) == 1)stack.remove(c[2]);
                    else stack.put(c[2], stack.get(c[2])-1);
                }
            }
            int nh = stack.isEmpty() ? 0 : stack.lastKey();
            if(nh != ch) {
                ans.add(Arrays.asList(pos, nh));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L218().getSkyline(new int[][]{{1,2,1},{1,2,2},{1,2,3}}));
    }
}
