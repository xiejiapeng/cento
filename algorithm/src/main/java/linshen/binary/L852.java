package linshen.binary;

/*
给定一个长度为 n 的整数 山脉 数组 arr ，其中的值递增到一个 峰值元素 然后递减。

返回峰值元素的下标。

你必须设计并实现时间复杂度为 O(log(n)) 的解决方案。
 */

public class L852 {
    public int peakIndexInMountainArray(int[] arr) {
        return find(arr, 0, arr.length-1);
    }

    private int find(int[] arr, int left, int right) {
        if(left == right)return left;
        else if(left == right - 1) {
            if(arr[right] > arr[left])return right;
            else return left;
        } else {
            int mid = (left + right) / 2;
            if(mid - 1 < 0 || arr[mid] > arr[mid - 1])return find(arr, mid, right);
            else return find(arr, left, mid);
        }
    }
}
