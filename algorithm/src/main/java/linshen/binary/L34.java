package linshen.binary;

/*
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */

public class L34 {
    public int[] searchRange(int[] nums, int target) {
        int a = larger(nums, 0, nums.length-1, target);
        int b = small(nums, 0, nums.length-1, target);
        //todo 记住判断条件
        if(a == -1 || b == -1 || nums[a] != target) {
            return new int[]{-1,-1};
        } else {
            return new int[]{a,b};
        }
    }

    //第一个大于等于target
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

    //最后一个小于等于
    private int small(int[] nums, int left, int right, int target) {
        if(left > right)return -1;
        if(left == right) {
            if(nums[left] <= target)return left;
            else return -1;
        } else if(left == right - 1) {
            if(nums[right] <= target)return right;
            else if(nums[left] <= target)return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(nums[mid] <= target)return small(nums, mid, right, target);
            else return small(nums, left, mid - 1, target);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L34().searchRange(new int[]{5,7,7,8,8,10}, 8));
    }
}
