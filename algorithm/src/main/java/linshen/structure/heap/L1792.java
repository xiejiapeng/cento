package linshen.structure.heap;

/*
一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组 classes ，其中 classes[i] = [passi, totali] ，
表示你提前知道了第 i 个班级总共有 totali 个学生，其中只有 passi 个学生可以通过考试。

给你一个整数 extraStudents ，表示额外有 extraStudents 个聪明的学生，他们 一定 能通过任何班级的期末考。你需要给这 extraStudents 个学生每人都安排一个班级，
使得 所有 班级的 平均 通过率 最大 。

一个班级的 通过率 等于这个班级通过考试的学生人数除以这个班级的总人数。平均通过率 是所有班级的通过率之和除以班级数目。

请你返回在安排这 extraStudents 个学生去对应班级后的 最大 平均通过率。与标准答案误差范围在 10-5 以内的结果都会视为正确结果。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L1792 {
    public static void main(String[] args) {

    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> cs = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //todo h 整数除法注意不要丢失精度
                double a = ((double) o1[0] + 1) / ((double) o1[1] + 1) - (double) o1[0] / o1[1];
                double b = ((double) o2[0] + 1) / ((double) o2[1] + 1) - (double) o2[0] / o2[1];
                return Double.compare(b, a);
            }
        });
        for (int[] c : classes) {
            cs.add(c);
        }
        for (int i = 0; i < extraStudents; i++) {
            int[] c = cs.poll();
            c[0] += 1;
            c[1] += 1;
            cs.add(c);
        }
        double p = 0;
        while (!cs.isEmpty()) {
            int[] c = cs.poll();
            System.out.println(Arrays.toString(c));
            System.out.println(c[0] / c[1]);
            p += ((double) c[0] / c[1]);
        }
        return p / classes.length;
    }
}
