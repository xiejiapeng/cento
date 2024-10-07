package sulwish;

/*
给定一个正整数数组 nums和一个整数 k，返回 nums 中 「好子数组」 的数目。

如果 nums 的某个子数组中不同整数的个数恰好为 k，则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。

例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
子数组 是数组的 连续 部分。
 */


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class L992 {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int cnt = 0;
        Map<Integer,Integer> all = new HashMap<>();
        Map<Integer,Integer> last = new HashMap<>();
        PriorityQueue<Integer> mq = new PriorityQueue<>();
        for (int left = 0, right = 0; right < nums.length; right++) {
            all.put(nums[right], all.getOrDefault(nums[right], 0) + 1);
            if(last.containsKey(nums[right])){
                mq.remove(last.get(nums[right]));
            }
            last.put(nums[right], right);
            mq.add(right);
            while (left <= right && all.size() > k) {
                int x = nums[left];
                if(all.get(x) == 1)all.remove(x);
                else all.put(x, all.get(x) - 1);
                if(last.get(x) == left){
                    last.remove(x);
                    mq.remove(left);
                }
                left++;
            }
            if(left <= right && all.size() == k){
                int min = mq.peek();
                cnt += (min - left + 1);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L992().subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
    }
}
