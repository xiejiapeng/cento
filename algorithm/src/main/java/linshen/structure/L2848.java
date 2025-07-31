package linshen.structure;

/*
给你一个下标从 0 开始的二维整数数组 nums 表示汽车停放在数轴上的坐标。对于任意下标 i，nums[i] = [starti, endi] ，
其中 starti 是第 i 辆车的起点，endi 是第 i 辆车的终点。

返回数轴上被车 任意部分 覆盖的整数点的数目。
提示：

1 <= nums.length <= 100
nums[i].length == 2
1 <= starti <= endi <= 100
 */

import java.util.List;

public class L2848 {
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] diff = new int[200];
        for (List<Integer> car : nums) {
            int start = car.get(0);
            int end = car.get(1);
            diff[start] += 1;
            diff[end + 1] -= 1;
        }
        int cnt = 0;
        int x = diff[0];
        for (int i = 0; i < diff.length; i++) {
            x += diff[i];
            if (x != 0) cnt += 1;
        }
        return cnt;
    }
}
