package linshen.binary;

/*
给你一个整数数组 start 和一个整数 d，代表 n 个区间 [start[i], start[i] + d]。

你需要选择 n 个整数，其中第 i 个整数必须属于第 i 个区间。所选整数的 得分 定义为所选整数两两之间的 最小 绝对差。

返回所选整数的 最大可能得分 。
2 <= start.length <= 105
0 <= start[i] <= 109
0 <= d <= 109
 */

import java.util.Arrays;

public class L3281 {
    //todo 当成典型题
    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < start.length - 1; i++){
            min = Math.min(min, Math.max(start[i+1] - start[i] - d, 0));
            max = Math.max(max, start[i+1] + d - start[i]);
        }
        return findLargest(start, d, min, max);
    }

    private int findLargest(int[] start, int d, int left, int right) {
        if(left == right)return right;
        else if(left == right - 1) {
            if(check(start, d, right))return right;
            else return left;
        } else {
            //todo 下标溢出
            int mid = left + (right - left) / 2;
            if(check(start, d, mid))return findLargest(start, d, mid, right);
            else return findLargest(start, d, left, mid - 1);
        }
    }

    //todo 贪心的时候记住，target不是确定性的限制要求每个距离都是target，只要求距离大于等于target即可。只需要考虑极端的情况，如果不满足，不要被target限制，间距可以大于target
    private boolean check(int[] start, int d, int target) {
        int n = start.length;
        int last = -1;
        for (int i = n - 1; i > 0; i--) {
            int right = i == n - 1 ? start[i] + d : last;
            int left = right - target;
            if(left < start[i-1])return false;
            else {
                last = Math.min(left, start[i-1] + d);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L3281().maxPossibleScore(new int[]{1000000000,0}, 1000000000));
    }
}
