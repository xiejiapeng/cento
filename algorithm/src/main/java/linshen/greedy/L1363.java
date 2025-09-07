package linshen.greedy;

/*
给你一个整数数组 digits，你可以通过按 任意顺序 连接其中某些数字来形成 3 的倍数，请你返回所能得到的最大的 3 的倍数。

由于答案可能不在整数数据类型范围内，请以字符串形式返回答案。如果无法得到答案，请返回一个空字符串。返回的结果不应包含不必要的前导零。
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class L1363 {
    public static void main(String[] args) {
        System.out.println(new L1363().largestMultipleOfThree(new int[]{0, 0, 0, 0, 0, 1}));
//        System.out.println(new L1363().largestMultipleOfThree(new int[]{2,9,1,6,1,6,2,2,4,3,2,8,6,1,1,7,2,3,8,6,2,6,4,8,5,0,0,3,3,7,9,1,3,4,3,8,1,7,1,3,9,7,4,2,8,6,0,6,2,7,0,5,1,9,0,7,8,0,4,5,6,2,7,9,8,4,8,6,2,2,1,9,6,1,0,1,0,3,6,1,1,8,6,1,6,9,1,3,6,4,7,7,3,2,5,3,1,7,3,9,0,9,3,2,6,8,7,2,7,9,3,5,9,7,3,7,5,0,0,3,5,3,8,3,8,2,9,6,6,9,0,5,3,1,2,8,3,0,4,9,6,7,6,4,6,6,6,6,6,5,1,8,5,3,7,8,2,7,9,8,3,9,8,1,2,6,5,4,7,6,1,9,0,7,2,1,4,4,7,5,0,9,5,0,7,7,9,2,0,9,6,7,0,8,3,1,3,4,0,4,9,4,0,9,7,8,5,8,8,7,6,4,5,8,8,9,3,4,1,3,7,5,6,1,2,7,4,1,5,8,1,2,0,7,1,7,0,0,8,8,4,7,3,2,9,2,2,0,1,2,7,9,0,1,7,0,8,2,3,7,6,6,8,6,6,4,0,1,1,6,7,1,9,3,4,3,7,8,6,7,1,7,0,7,5,6,6,8,8,3,7,5,7,6,8,0,8,7,8,2,4,6,2,6,2,7,1,0,0,4,1,7,3,2,6,5,0,4,7,2,1,7,6,5,9,7,5,2,0,9,9,0,7,3,3,9,5,6,1,6,5,2,7,9,0,0,3,3,1,2,8,0,2,6,6,8,3,9,1,4,6,5,9,1,1,1,6,4,7,0,6,1,0,1,7,0,0,7,9,2,5,3,3,8,5,6,7,2,8,4,2,4,1,9,5,5,5,9,5,7,1,1,8,7,4,0,2,3,7,2,8,1,1,8,2,0,8,2,8,7,4,5,3,8,1,5,0,7,7,3,3,1,1,7,9,6,6,9,3,1,9,1,0,9,1,8,6,9,4,2,8,1,0,9,7,7,4,1,6,3,8,6,6,3,9,0,4,7,9,9,4,9,5,1,8,7,0,8,8,6,3,8,6,1,3,1,2,2,3,4,2,4,4,7,7,9,9,2,0,4,8,6,6,2,7,1,8,5,4,3,5,5,5,2,4,7,7,3,8,1,9,9,4,4,7,9,9,3,2,2,2,8,5,4,3,5,5,4,7,9,5,7,2,9,4,8,2,3,1,2,6,5,2,5,7,5,8,2,2,5,3,6,8,4,4,6,5,6,1,1,0,5,4,3,1,4,6,1,9,5,2,4,9,0,9,2,3,4,4,8,4,5,4,1,5,5,1,2,2,9,3,0,1,6,7,4,6,5,4,9,5,4,0,8,1,9,3,4,9,7,8,6,6,4,8,6,9,3,7,7,5,8,5,8,5,1,9,1,7,6,9,3,4,5,9,4,9,9,4,4,0,6,4,2,1,9,3,5,1,3,7,1,8,4,7,2,0,9,2,0,9,3,0,4,5,6,0,2,3,5,4,0,5,9,5,2,9,5,7,8,5,9,3,9,5,6,8,4,5,3,1,6,5,8,1,6,5,7,1,7,4,3,2,5,5,1,8,4,1,4,1,2,9,8,2,4,1,1,6,5,7,7,0,5,9,7,4,3,6,1,8,7,7,4,1,7,0,3,2,9,9,4,7,5,9,4,6,0,9,7,6,4,4,6,1,1,7,1,1,2,3,3,2,4,2,8,7,1,5,4,2,0,8,4,3,2,3,7,2,3,1,6,7,6,7,5,0,1,9,9,6,9,8,7,1,1,5,4,1,6,5,1,9,4,3,2,8,2,1,3,1,9,0,7,7,7,0,4,8,6,4,2,3,6,7,4,6,9,5,9,0,6,6,3,8,0,5,5,2,5,1,5,5,1,2,9,8,4,3,6,5,0,2,8,5,2,0,5,7,0,1,5,5,5,9,5,5,2,3,2,4,2,2,0,1,9,1,5,2,7,8,4,8,4,6,8,6,2,7,8,2,3,7,8,7,9,6,9,6,8,6,8,8,2,5,3,1,1,3,3,4,3,9,2,2,7,0,8,5,7,9,3,6,2,2,6,3,1,3,5,2,5,8,3,5,9,6,8,2,6,4,2,5,4,8,5,2,3,7,9,9,3,5,3,1,4,2,8,2,6,5,9,2,1,2,9,8,5,2,3,1,7,5,6,9,9,5,1,6,8,3,6,6,0,1,5,2,8,7,6}));
    }

    public String largestMultipleOfThree(int[] digits) {
        int n = digits.length;
        int[] mods = new int[n];
        PriorityQueue<Integer> m1 = new PriorityQueue<>(Comparator.comparingInt(o -> -digits[o]));
        PriorityQueue<Integer> m2 = new PriorityQueue<>(Comparator.comparingInt(o -> -digits[o]));
        PriorityQueue<Integer> all = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                int o1 = digits[i1];
                int o2 = digits[i2];
                String x1 = String.valueOf(o1) + String.valueOf(o2);
                String x2 = String.valueOf(o2) + String.valueOf(o1);
                return -1 * x1.compareTo(x2);
            }
        });
        int t = 0;
        for (int i = 0; i < n; i++) {
            mods[i] = digits[i] % 3;
            t += mods[i];
            if (mods[i] == 1) {
                m1.add(i);
                if (m1.size() > 2) m1.poll();
            }
            if (mods[i] == 2) {
                m2.add(i);
                if (m2.size() > 2) m2.poll();
            }
            all.add(i);
        }
        LinkedList<Integer> as = new LinkedList<>();
        while (!all.isEmpty()) {
            as.add(all.poll());
        }

        if (t % 3 == 1) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            //remove one m1
            if (!m1.isEmpty()) {
                int r = -1;
                while (!m1.isEmpty()) {
                    r = m1.poll();
                }
                for (int i = 0; i < as.size(); i++) {
                    if (as.get(i) != r) s1.append(digits[as.get(i)]);
                }
                m1.add(r);
                return removeLeading(s1.toString());
            } else if (m2.size() == 2) {
                int r1 = m2.poll();
                int r2 = m2.poll();
                for (int i = 0; i < as.size(); i++) {
                    if (as.get(i) != r1 && as.get(i) != r2) s2.append(digits[as.get(i)]);
                }
                m2.add(r1);
                m2.add(r2);
                return removeLeading(s2.toString());
            } else return "";

        } else if (t % 3 == 2) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            //remove one m2
            if (!m2.isEmpty()) {
                int r = -1;
                while (!m2.isEmpty()) {
                    r = m2.poll();
                }
                for (int i = 0; i < as.size(); i++) {
                    if (as.get(i) != r) s1.append(digits[as.get(i)]);
                }
                m2.add(r);
                return removeLeading(s1.toString());
            } else if (m1.size() == 2) {
                int r1 = m1.poll();
                int r2 = m1.poll();
                for (int i = 0; i < as.size(); i++) {
                    if (as.get(i) != r1 && as.get(i) != r2) s2.append(digits[as.get(i)]);
                }
                m1.add(r1);
                m1.add(r2);
                return removeLeading(s2.toString());
            } else {
                return "";
            }
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < as.size(); i++) {
                sb.append(digits[as.get(i)]);
            }
            return removeLeading(sb.toString());
        }
    }

    public String removeLeading(String s) {
        if (s.isEmpty()) return "";
        else if (s.length() == 1) return s;
        else {
            if (s.charAt(0) == '0') return removeLeading(s.substring(1));
            else return s;
        }
    }
}
