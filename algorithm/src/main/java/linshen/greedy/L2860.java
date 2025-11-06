package linshen.greedy;

/*
给你一个下标从 0 开始、长度为 n 的整数数组 nums ，其中 n 是班级中学生的总数。
班主任希望能够在让所有学生保持开心的情况下选出一组学生：

如果能够满足下述两个条件之一，则认为第 i 位学生将会保持开心：

这位学生被选中，并且被选中的学生人数 严格大于 nums[i] 。
这位学生没有被选中，并且被选中的学生人数 严格小于 nums[i] 。
返回能够满足让所有学生保持开心的分组方法的数目。
//todo hhh 记住本节的标题，从特殊到一般，往往需要一个梳理的过程，可以用一个简单的例子开始去发现规律
nums[0] <= nums[1] <= nums[2] <= ... <= nums[n-1]
选3人，要求
    有三位以上 nums[i] < 3，比如 nums[0],nums[1],nums[2],nums[3],nums[4]
    剩余nums[5],...,nums[n-1] > 3
 */

import java.util.Collections;
import java.util.List;

public class L2860 {
    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            int cur = i + 1;
            if(nums.get(i) < cur && (i+1 == nums.size() || nums.get(i+1) > cur))cnt++;
        }
        return cnt;
    }
}
