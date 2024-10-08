package index.localhash;

/*
给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。

你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。


 */

public class L287 {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++){
            if(nums[i] != i + 1) {
                int x = nums[i];
                while (x != i + 1 && x - 1 >= 0 && x - 1 < n) {
                    int y = nums[x-1];
                    if(x == y)return x;
                    nums[x-1] = x;
                    x = y;
                }
                nums[i] = x;
            }
        }
        return -1;
    }
}
