package linshen.greedy;

import java.util.*;

/*
给你一个下标从 0 开始的 非递减 整数数组 nums 。
你可以执行以下操作任意次：

选择 两个 下标 i 和 j ，满足 nums[i] < nums[j] 。
将 nums 中下标在 i 和 j 处的元素删除。剩余元素按照原来的顺序组成新的数组，下标也重新从 0 开始编号。
请你返回一个整数，表示执行以上操作任意次后（可以执行 0 次），nums 数组的 最小 数组长度。
3 4 4 5
1 1 2 2 3 3
 */

public class L2856 {
    public int minLengthAfterRemovals(List<Integer> nums) {
        Map<Integer, Integer> all = new HashMap<>();
        for (int x : nums) {
            all.put(x, all.getOrDefault(x, 0) + 1);
        }
        PriorityQueue<int[]> cnt = new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1[1], o2[1]));
        for (int x : all.keySet()) {
            cnt.add(new int[]{x, all.get(x)});
        }
        int ans = 0;
        for (int i = 0; i < nums.size(); i++){
            int x = nums.get(i);
            if(all.get(x) == 0)continue;
            else {
                while (!cnt.isEmpty() && cnt.peek()[0] <= x)cnt.poll();
                if(!cnt.isEmpty()) {
                    int[] p = cnt.poll();
                    p[1] -= 1;
                    System.out.println("find " + x + "," + p[0]);
                    all.put(p[0], p[1]);
                    if(p[1] >= 1)cnt.add(p);
                    ans += 2;
                }
            }
            all.put(x, all.get(x) - 1);
        }
        return nums.size() - ans;
    }

    public static void main(String[] args) {
        System.out.println(new L2856().minLengthAfterRemovals(Arrays.asList(1,1,2,2,3,3,4)));
    }
}
