package linshen.binary;

/*
给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：
0 <= i < j < n，且
lower <= nums[i] + nums[j] <= upper

lower - nums[j] <= nums[i] <= upper - nums[j]

1 <= nums.length <= 105
nums.length == n
-109 <= nums[i] <= 109
-109 <= lower <= upper <= 109
 */

import java.util.Arrays;

public class L2563 {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;
        for (int i = 0; i < nums.length; i++){
            int x = findLess(nums, 0, nums.length-1, upper - nums[i]);
            int y = findLarger(nums, 0, nums.length-1, lower - nums[i]);
            if(x == -1 || y == -1)continue;
            else {
                if(i >= y && i <= x)ans += x-y;
                else ans += Math.max(0, x - y + 1);
            }
        }
        return ans / 2;
    }

    private int findLess(int[] nums, int left, int right, int upper) {
        if(left > right)return -1;
        if(left == right) {
            if(nums[left] <= upper)return left;
            else return -1;
        } else if(left == right - 1) {
            if(nums[right] <= upper)return right;
            else if(nums[left] <= upper)return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(nums[mid] <= upper)return findLess(nums, mid, right, upper);
            else return findLess(nums, left, mid - 1, upper);
        }
    }

    private int findLarger(int[] nums, int left, int right, int lower) {
        if(left > right)return -1;
        if(left == right) {
            if(nums[left] >= lower)return left;
            else return -1;
        } else if(left == right - 1) {
            if(nums[left] >= lower)return left;
            else if(nums[right] >= lower)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(nums[mid] >= lower)return findLarger(nums, left, mid, lower);
            else return findLarger(nums, mid + 1, right, lower);
        }
    }

    //todo highest:记住双指针解法。1是注意区间与right的移动方向。2是注意最后的判断条件
    public long countFairPairs2(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int l1 = n-1, l2 = n-1, right = 0; right < n; right++){
            while (l2>-1&&nums[right] + nums[l2] > upper){
                l2--;
            }
            while (l1-1 >-1 && nums[right]+nums[l1-1] >= lower) {
                l1--;
            }

            if(l1 == -1 || l2 == -1)continue;
            if(nums[right] + nums[l2] <= upper && nums[right] + nums[l1] >= lower) {
                if(right >= l1 && right <= l2)ans += l2-l1;
                else ans += (l2 - l1 + 1);
            }

        }
        return ans / 2;
    }

    public static void main(String[] args) {
        System.out.println(new L2563().countFairPairs2(new int[]{-1,0}, 1, 1));
    }
}
