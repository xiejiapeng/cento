package index.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。

整数 a 比整数 b 更接近 x 需要满足：

|a - x| < |b - x| 或者
|a - x| == |b - x| 且 a < b
 */

public class L658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int id = find(arr, x, 0, arr.length - 1);
        LinkedList<Integer> ans = new LinkedList<>();
        int a, b;
        if(arr[id] <= x) {
            a = id;
            b = id + 1;
        } else {
            a = id - 1;
            b = id;
        }
        while (a > -1 || b < arr.length) {
            if(a <= -1) {
                ans.addLast(arr[b]);
                b++;
            } else if(b >= arr.length) {
                ans.addFirst(arr[a]);
                a--;
            } else {
                if(x-arr[a] <= arr[b]-x) {
                    ans.addFirst(arr[a]);
                    a--;
                } else {
                    ans.addLast(arr[b]);
                    b++;
                }
            }
            if(ans.size() >= k)break;
        }
        return ans;
    }

    private int find(int[] arr, int x, int left, int right) {
        if(left == right) {
            return left;
        } else if(left == right - 1) {
            if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x))return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(arr[mid] == x)return mid;
            else if(arr[mid] < x)return find(arr, x, mid, right);
            else return find(arr, x, left, mid);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L658().findClosestElements(new int[]{0,0,0,1,3,5,6,7,8,8}, 2, 2));
    }
}
