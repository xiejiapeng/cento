package index.binary;

/*
给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。

k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：

0 <= i, j < nums.length
i != j
|nums[i] - nums[j]| == k
注意，|val| 表示 val 的绝对值。
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class L532 {
    public int findPairs(int[] nums, int k) {
        Set<Integer> a = new HashSet<>();
        Arrays.sort(nums);
        int next = 0;
        for (int i = 0; i < nums.length; i++){
            if(i-1>-1&&nums[i]==nums[i-1]){
                a.add(nums[i]);
                continue;
            }
            else{
                nums[next++]=nums[i];
            }
        }
        if(k == 0)return a.size();
        return find(nums, k, 0, next-1);
    }

    private int find(int[] nums, int k, int left, int right) {
        if(left == right) {
            return 0;
        } else if(left == right - 1) {
            if(nums[right] - nums[left] == k) {
                return 1;
            } else {
                return 0;
            }
        } else {
            int mid = (left + right) / 2;
            int a = find(nums, k, left, mid);
            int b = find(nums, k, mid + 1, right);
            int ans = a + b;
            int l = left;
            int r = mid + 1;
            while (l <= mid && r <= right) {
                if(l-1>=left&&nums[l-1]==nums[l]){
                    l++;
                    continue;
                }
                if(r-1>=mid+1&&nums[r-1]==nums[r]){
                    r++;
                    continue;
                }
                if(nums[r] - nums[l] == k){
                    ans++;
                    l++;
                    r++;
                }
                else if(nums[r] - nums[l] > k) {
                    l++;
                } else {
                    r++;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(new L532().findPairs(new int[]{0,1,2,2,3,3,3,4,4,9}, 3));
    }
}
