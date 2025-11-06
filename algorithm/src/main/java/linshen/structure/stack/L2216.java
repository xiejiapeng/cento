package linshen.structure.stack;

/*
给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：

nums.length 为偶数
对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
注意，空数组同样认为是美丽数组。

你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。

返回使 nums 变为美丽数组所需删除的 最少 元素数目。
提示：

1 <= nums.length <= 105
0 <= nums[i] <= 105
 */

import java.util.Stack;

public class L2216 {
    public int minDeletion(int[] nums) {
        //todo h 挺有意思的一个小题，注意左侧不变形；对左边的非美丽对，一定要进行操作
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i > -1; i--){
            stack.add(nums[i]);
        }
        int cnt = 0;
        while (!stack.isEmpty()) {
            if(stack.size() == 1){
                stack.pop();
                cnt++;
            } else {
                int x = stack.pop();
                int y = stack.pop();
                if(x == y) {
                    stack.add(x);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
