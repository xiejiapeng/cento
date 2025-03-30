package linshen.binary;

/*
给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。

换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
注意：0 既不是正整数也不是负整数。
 */

public class L2529 {
    public int maximumCount(int[] nums) {
        int x = firstLarger(nums, 0, nums.length - 1);
        int y = lastSmaller(nums, 0, nums.length - 1);
        if(x == -1) {
            x = 0;
        } else {
            x = nums.length - x;
        }
        if(y == -1) {
            y = 0;
        } else {
            y = y + 1;
        }
        return Math.max(x, y);
    }

    private int firstLarger(int[] nums, int left, int right) {
        if(left > right)return -1;
        else if(left == right) {
            if(nums[left] > 0)return left;
            else return -1;
        } else if(left == right - 1) {
            if(nums[left] > 0)return left;
            else if(nums[right] > 0)return right;
            else return -1;
        } else {
            int mid = (left +right) /2;
            if(nums[mid] > 0)return firstLarger(nums, left, mid);
            else return firstLarger(nums, mid + 1, right);
        }
    }

    private int lastSmaller(int[] nums, int left, int right) {
        if(left > right)return -1;
        else if(left == right) {
            if(nums[left] < 0)return left;
            else return -1;
        } else if(left == right - 1) {
            if(nums[right] < 0)return right;
            else if(nums[left] < 0)return left;
            else return -1;
        } else {
            int mid = (left +right) /2;
            if(nums[mid] < 0)return lastSmaller(nums, mid, right);
            else return lastSmaller(nums, left, mid-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L2529().maximumCount(new int[]{-2,-1,-1,1,2,3}));
    }
}
