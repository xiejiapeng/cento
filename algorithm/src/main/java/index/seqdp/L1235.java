package index.seqdp;

/*
你打算利用空闲时间来做兼职工作赚些零花钱。

这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。

给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。

注意，时间上出现重叠的 2 份工作不能同时进行。

如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L1235 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] all = new int[n][3];
        for (int i = 0; i < n; i++){
            all[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(all, Comparator.comparingInt(o -> o[1]));
        int[] f = new int[n];
        for (int i = 0; i < n; i++){
            f[i] = all[i][2];
            //choose i
            int t = find(all, 0, i, all[i][0]);
            if(t != -1) {
                f[i] = Math.max(f[i], all[i][2] + f[t]);
            }

            //don't choose i
            f[i] = Math.max(f[i], (i>=1?f[i-1]:0));
        }
        return f[n-1];
    }

    /*
    [start, end] last element <= upper
     */
    private int find(int[][] all, int start, int end, int upper) {
        if(start == end) {
            if(all[end][1] <= upper) return end;
            else return -1;
        } else if(start == end - 1) {
            if(all[end][1] <= upper) return end;
            else if(all[start][1] <= upper) return start;
            else return -1;
        } else {
            int mid = (start + end) / 2;
            if(all[mid][1] <= upper) return find(all, mid, end, upper);
            else return find(all, start, mid - 1, upper);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L1235().jobScheduling(new int[]{1,2,3,3}, new int[]{3,4,5,6}, new int[]{50,10,40,70}));
    }
}
