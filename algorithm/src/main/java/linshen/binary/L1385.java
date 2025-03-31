package linshen.binary;

/*
给你两个整数数组 arr1 ， arr2 和一个整数 d ，请你返回两个数组之间的 距离值 。

「距离值」 定义为符合此距离要求的元素数目：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d 。

arr1[i] - d <= arr[j]  <= arr1[i] + d
 */

import java.util.Arrays;

public class L1385 {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int i = 0; i < arr1.length; i++){
            int t = findLarger(arr2, arr1[i] + d, 0, arr2.length-1);
            if(t == -1)ans++;
            else{
                if(arr2[t] < arr1[i] - d)ans++;
            }
        }
        return ans;
    }

    private int findLarger(int[] arr2, int limit, int left, int right) {
        if(left > right)return -1;
        else if(left == right) {
            if(arr2[right] <= limit)return right;
            else return -1;
        } else if(left == right - 1) {
            if(arr2[right] <= limit)return right;
            else if(arr2[left] <= limit)return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(arr2[mid] <= limit)return findLarger(arr2, limit, mid, right);
            else return findLarger(arr2,limit, left, mid-1);
        }
    }
}
