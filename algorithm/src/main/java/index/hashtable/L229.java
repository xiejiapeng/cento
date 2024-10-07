package index.hashtable;

import java.util.ArrayList;
import java.util.List;

/*
给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 */

public class L229 {
    int none = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new L229().majorityElement(new int[]{1, 1, 1}));
    }

    public List<Integer> majorityElement(int[] nums) {
        int a = none;
        int b = none;
        int ac = 0;
        int bc = 0;
        for (int x : nums) {
            if (a == x) ac++;
            else if (b == x) bc++;
            else {
                if (ac == 0) {
                    a = x;
                    ac = 1;
                } else if (bc == 0) {
                    b = x;
                    bc = 1;
                } else {

                    ac--;
                    bc--;
                    if (bc == 0) b = none;
                    else if (ac == 0) {
                        if (bc != 0) {
                            a = b;
                            ac = bc;

                            b = none;
                            bc = 0;
                        }
                    }
                }
            }
            //put into empty slot

        }

        List<Integer> ans = new ArrayList<>();
        ac = 0;
        bc = 0;
        for (int x : nums) {
            if (x == a) ac++;
            if (x == b) bc++;
        }
        int n = nums.length / 3;
        if (ac > n) ans.add(a);
        if (bc > n) ans.add(b);
        return ans;
    }
}
