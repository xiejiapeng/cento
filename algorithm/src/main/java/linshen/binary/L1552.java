package linshen.binary;

/*
在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，
它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，
Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。

已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。

给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 */

import java.util.Arrays;

public class L1552 {

    public int maxDistance(int[] price, int k) {
        Arrays.sort(price);
        //todo 注意，再求left和right区间的时候，不要管题意是求绝对差的最小还是最大，只要得到绝对差的范围，就能得到其最大或最小值的范围
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < price.length - 1; i++){
            min = Math.min(min, price[i+1] - price[i]);
        }
        int mi = Arrays.stream(price).min().getAsInt();
        int mx = Arrays.stream(price).max().getAsInt();
        return findLargest(price, k, min, mx-mi);
    }

    private int findLargest(int[] price, int k, int left, int right) {
        if(left == right)return right;
        else if(left == right - 1) {
            if(check(price, k, right))return right;
            else return left;
        } else {
            //todo 下标溢出
            int mid = left + (right - left) / 2;
            if(check(price, k, mid))return findLargest(price, k, mid, right);
            else return findLargest(price, k, left, mid - 1);
        }
    }

    private boolean check(int[] price, int k, int target) {
        int n = price.length;
        int right = n - 1;
        int cnt = 1;
        while (right > -1) {
            int left = right - 1;
            while (left >= 0 && price[right] - price[left] < target) {
                left--;
            }
            if(left >= 0)cnt++;
            right = left;
            if(cnt >= k)return true;
        }
        return cnt >= k;
    }
}
