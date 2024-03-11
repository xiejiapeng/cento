package sulwish;

import org.apache.spark.sql.sources.In;

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
        LinkedList<Integer> ans = new LinkedList<>();
        if(arr[0] >= x){
            for (int i = 0; i < k; i++)ans.add(arr[i]);
        } else if(arr[arr.length-1] <= x){
            for (int i = 0; i < k; i++)ans.add(arr[arr.length - k + i]);
        } else {
            int first = -1;
            for (int i = 0; i < arr.length; i++){
                if(arr[i] >= x){
                    first = i;
                    break;
                }
            }
            int right = first;
            int left = first - 1;
            while (k-->0){
                if(left < 0){
                    ans.addLast(arr[right]);
                    right++;
                } else if(right >= arr.length){
                    ans.addFirst(arr[left]);
                    left--;
                } else {
                    if(arr[right] - x >= x - arr[left]){
                        ans.addFirst(arr[left]);
                        left--;
                    } else {
                        ans.addLast(arr[right]);
                        right++;
                    }
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L658().findClosestElements(new int[]{-2,-1,1,2,3,4,5}, 7, 3));
    }
}
