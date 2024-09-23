package index.binary;

/*
给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。

请你找出并返回只出现一次的那个数。

你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 */

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public class L540 {
    public int singleNonDuplicate(int[] nums) {
        return Arrays.stream(nums).reduce(0, (left, right) -> left ^ right);
    }
}
