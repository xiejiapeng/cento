package index.binary;
/*
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */

import java.util.Arrays;

public class L34 {
    public int[] searchRange(int[] nums, int target) {

        int x = first(nums, target, 0, nums.length - 1);
        if(x == -1)return new int[]{-1,-1};
        else {
            int y = last(nums, target, 0, nums.length - 1);
            if(y < x)return new int[]{-1,-1};
            else return new int[]{x,y};
        }
    }

    //first >= target
    private int first(int[] nums, int target ,int left, int right) {
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
            if(nums[mid] >= target) {
                return first(nums, target, left, mid);
            } else {
                return first(nums, target, mid + 1, right);
            }
        }
    }

    //last <= target
    private int last(int[] nums, int target ,int left, int right) {
        if(left == right) {
            if(nums[left] <= target) return left;
            else return -1;
        } else if(left == right - 1) {
            if(nums[right] <= target)return right;
            else if(nums[left] <= target) return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(nums[mid] <= target) {
                return last(nums, target, mid, right);
            } else {
                return last(nums, target, left, mid - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L34().searchRange(new int[]{5,7,7,8,8,10}, 6)));
    }
}
