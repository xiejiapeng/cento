package index.binary;

/*
峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 */

public class L162 {
    public int findPeakElement(int[] nums) {
        return find(nums, 0, nums.length - 1);
    }

    public int find(int[] nums, int left, int right) {
        if(left == right) return left;
        else if(left == right - 1) {
            if(nums[left] > nums[right]) {
                return left;
            } else {
                return right;
            }
        } else {
            int mid = (left + right) / 2;
            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1])return mid;
            else if(nums[mid] < nums[mid - 1]) {
                return find(nums, left, mid);
            } else {
                return find(nums, mid, right);
            }
        }
    }
}
