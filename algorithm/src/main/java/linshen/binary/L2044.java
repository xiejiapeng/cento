package linshen.binary;

/*
给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。

如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。

对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。

1 <= nums.length <= 16
1 <= nums[i] <= 105
 */

public class L2044 {
    int max = -1;
    int ans = 0;
    public int countMaxOrSubsets(int[] nums) {
        dfs(nums, 0, 0);
        return ans;
    }

    private void dfs(int[] nums, int id, int count) {
        if(id == nums.length) {
            if(count > max) {
                max = count;
                ans = 1;
            } else if(count == max) {
                ans ++;
            }
        } else {
            //choose
            dfs(nums, id + 1, count | nums[id]);
            //don't choose
            dfs(nums, id + 1, count);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L2044().countMaxOrSubsets(new int[]{3,1}));
    }
}
