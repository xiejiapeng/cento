package linshen.binary;

import java.util.Arrays;

/*
给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。

商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。

返回礼盒的 最大 甜蜜度。
 */
public class L2517 {
    public int maximumTastiness(int[] price, int k) {
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

    public static void main(String[] args) {
        System.out.println(new L2517().maximumTastiness(new int[]{1,3,1}, 2));
    }

}
