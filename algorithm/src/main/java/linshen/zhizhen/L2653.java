package linshen.zhizhen;

/*
给你一个长度为 n 的整数数组 nums ，请你求出每个长度为 k 的子数组的 美丽值 。

一个子数组的 美丽值 定义为：如果子数组中第 x 小整数 是 负数 ，那么美丽值为第 x 小的数，否则美丽值为 0 。

请你返回一个包含 n - k + 1 个整数的数组，依次 表示数组中从第一个下标开始，每个长度为 k 的子数组的 美丽值 。

子数组指的是数组中一段连续 非空 的元素序列。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

//todo h: 记住这种topK 可以考虑双队列，包括求中位数也可以. 但是本题 nums[i]取值为[-50,50]，完全可以枚举窗口内的所有值

public class L2653 {
    public int[] getSubarrayBeauty2(int[] nums, int k, int x) {
        //always keep the capacity of small to x, if less than x, move the least one from the big queue
        //o => [number, index]
        PriorityQueue<int[]> small = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] == o2[0])return -1;
            else return -1 * Integer.compare(o1[0], o2[0]);
        });
        PriorityQueue<int[]> big = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] == o2[0])return -1;
            else return Integer.compare(o1[0], o2[0]);
        });
        int n = nums.length;
        int[] ans = new int[n-k+1];
        int i = 0;
        int[][] tmp = new int[n][2];
        for (int j = 0; j < n; j++){
            tmp[j] = new int[]{nums[j], j};
        }
        for (int right = 0; right < n; right++) {
            int left = right - k + 1;
            if(small.size() < x) {
                small.add(tmp[right]);
            } else {
                if(nums[right] >= small.peek()[0]) {
                    big.add(tmp[right]);
                } else {
                    int[] t = small.poll();
                    small.add(tmp[right]);
                    big.add(t);
                }
            }
            if(left - 1 >= 0) {
                int[] rm = tmp[left-1];
                if(small.contains(rm)) {
                    small.remove(rm);
                    while (small.size() < x && !big.isEmpty()) {
                        small.add(big.poll());
                    }
                } else {
                    big.remove(rm);
                }
            }
            if(left >= 0) {
                ans[i++] = small.peek()[0] < 0 ? small.peek()[0] : 0;
            }
        }
        return ans;
    }

    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        boolean allpos = true;
        for (int t : nums) {
            if(t < 0)allpos = false;
        }
        int[] ans = new int[n-k+1];
        if(allpos){
            return ans;
        }
            int i = 0;
        TreeMap<Integer, Integer> all = new TreeMap<>(Comparator.comparingInt(o -> o));
        for (int right = 0; right < n; right++){
            int left = right - k + 1;
            all.put(nums[right], all.getOrDefault(nums[right], 0) + 1);
            if(left - 1 >= 0) {
                all.put(nums[left-1], all.get(nums[left-1]) - 1);
                if(all.get(nums[left-1]) == 0)all.remove(nums[left-1]);
            }
            if(left >= 0) {
                int acc = 0;
                for(int t : all.navigableKeySet()) {
                    acc += all.get(t);
                    if(acc >= x){
                        ans[i++] = Math.min(t, 0);
                        break;
                    }
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L2653().getSubarrayBeauty(new int[]{-3,1,2,-3,0,-3}, 2,1)));
    }
}
