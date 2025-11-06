package linshen.greedy;

/*
给你一个下标从 0 开始的整数数组 coins，表示可用的硬币的面值，以及一个整数 target 。

如果存在某个 coins 的子序列总和为 x，那么整数 x 就是一个 可取得的金额 。

返回需要添加到数组中的 任意面值 硬币的 最小数量 ，使范围 [1, target] 内的每个整数都属于 可取得的金额 。

数组的 子序列 是通过删除原始数组的一些（可能不删除）元素而形成的新的 非空 数组，删除过程不会改变剩余元素的相对位置。

1 2 5 9
[1,10]

=>
提示：

1 <= target <= 105
1 <= coins.length <= 105
1 <= coins[i] <= target
 */

import java.util.*;

public class L2952 {
    //todo hhhhh 记住吧
    class Solution {
        public int minimumAddedCoins(int[] coins, int target) {
            Arrays.sort(coins);
            int ans = 0;
            int x = 1;
            int length = coins.length, index = 0;
            while (x <= target) {
                if (index < length && coins[index] <= x) {
                    x += coins[index];
                    index++;
                } else {
                    x *= 2;
                    ans++;
                }
            }
            return ans;
        }
    }


    public static void main(String[] args) {
        //1,2,4,8
        /*
        1 2 4 5 6 7 8
         */
    }
}
