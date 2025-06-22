package linshen.dp;

/*
我们定义 arr 是 山形数组 当且仅当它满足：

arr.length >= 3
存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
给你整数数组 nums ，请你返回将 nums 变成 山形状数组 的 最少 删除次数。
提示：
3 <= nums.length <= 1000
1 <= nums[i] <= 109
题目保证 nums 删除一些元素后一定能得到山形数组。
 */

public class L1671 {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = n - 1; i > -1; i--) {
            right[i] = 1;
            for (int j = i + 1; j < n; j++){
                if(nums[i] > nums[j]) {
                    right[i] = Math.max(right[i], 1 + right[j]);
                }
            }
        }
        for (int i = 0; i < n; i++){
            left[i] = 1;
            for (int j = i - 1; j > -1; j--){
                if(nums[i] > nums[j]) {
                    left[i] = Math.max(left[i], 1 + left[j]);
                }
            }
        }
        int max = 3;
        for (int i = 0; i < n; i++){
            if(left[i] > 1 && right[i] > 1) {
                max = Math.max(max, left[i] + right[i] - 1);
            }
        }
        return n - max;
    }

    public static void main(String[] args) {
        System.out.println(new L1671().minimumMountainRemovals(new int[]{9,8,1,7,6,5,4,3,2,1}));
    }
}
