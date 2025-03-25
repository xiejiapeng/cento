package linshen.binary;

/*
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，
返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 O(log n) 的算法。
 */

public class L35 {
    public int searchInsert(int[] nums, int target) {
        int a = larger(nums, 0, nums.length - 1, target);
        if(a == -1) {
            return nums.length;
        } else {
            //todo 记住不是a-1
            return a;
        }
    }

    private int larger(int[] nums, int left, int right, int target) {
        if(left > right)return -1;
        if(left == right) {
            if(nums[left] >= target)return left;
            else return -1;
        } else if(left == right - 1) {
            if(nums[left] >= target)return left;
            else if(nums[right] >= target)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(nums[mid] >= target)return larger(nums, left, mid, target);
            else return larger(nums, mid + 1, right, target);
        }
    }
}
