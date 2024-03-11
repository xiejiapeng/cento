package sulwish;

import org.apache.spark.sql.sources.In;

import java.util.*;

/*
数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。

给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。
返回 所有数对距离中 第 k 小的数对距离。
 */
public class L719 {
    public int smallestDistancePair(int[] nums, int k) {
        List<Integer> all = divide(nums, 0, nums.length - 1, k);
        return all.get(0);
    }

    public List<Integer> divide(int[] nums, int start, int end, int k) {
        if(start >= end) {
            return new ArrayList<>();
        } else if(start == end - 1) {
            if(nums[end] < nums[start]) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
            }
            return Arrays.asList(nums[end] - nums[start]);
        } else {
            int mid = (start + end) / 2;
            PriorityQueue<Integer> all = new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1, o2));
            List<Integer> left = divide(nums, start, mid, k);
            List<Integer> right = divide(nums, mid+1, end, k);
            all.addAll(left);
            while (all.size() >= k)all.poll();
            all.addAll(right);
            while (all.size() >= k)all.poll();
            int l = start;
            int r = mid + 1;
            int id = start;
            while (l <= mid || r <= end) {
                if(l == mid){
                    nums[id++] = nums[r];
                    r++;
                } else if(r == end){
                    nums[id++] = nums[l];
                    l++;
                } else {
                    if(nums[l] <= nums[r]){
                        nums[id++] = nums[l];
                        l++;
                        all.add(nums[r] - nums[l]);
                    } else {
                        nums[id++] = nums[r];
                        r++;
                        all.add(nums[l] - nums[r]);
                    }
                    while (all.size() >= k)all.poll();
                }
            }
            return new ArrayList<>(all);
        }
    }
}
