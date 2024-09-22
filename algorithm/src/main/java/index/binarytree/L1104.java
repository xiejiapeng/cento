package index.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L1104 {
    public static void main(String[] args) {
        System.out.println(new L1104().pathInZigZagTree(14));
    }

    public List<Integer> pathInZigZagTree(int label) {
        if (label == 1) return Arrays.asList(1);
        int level = level(label);
        System.out.println("level is " + level);
        if (level % 2 == 0) {
            int u = (int) (Math.pow(2, level - 1) + Math.pow(2, level) - 1 - label);
            List<Integer> v = pathInZigZagTree(u / 2);
            List<Integer> ans = new ArrayList<>();
            ans.addAll(v);
            ans.add(label);
            return ans;
        } else {
            int u = label / 2;
            int l = level - 1;
            int r = (int) (Math.pow(2, l - 1) + Math.pow(2, l) - 1 - u);
            List<Integer> x = pathInZigZagTree(r);
            List<Integer> ans = new ArrayList<>();
            ans.addAll(x);
            ans.add(label);
            return ans;
        }
    }

    //1, [3,2], [4,5,6,7] [8,9,10,11,12,13,14,15]
    private int level(int x) {
        int t = 0;
        while (x != 0) {
            x /= 2;
            t++;
        }
        System.out.println("x=" + x + ",level=" + t);
        return t;
    }
}
