package linshen.slide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L2841 {
    public long maxSum(List<Integer> nums, int m, int k) {
        int n = nums.size();
        long max = 0;
        long sum = 0;
        Map<Integer,Integer> count = new HashMap<>();
        for (int right = 0; right < n; right++) {
            int left = right - k + 1;
            int rightn = nums.get(right);
            sum += rightn;
            count.put(rightn, count.getOrDefault(rightn, 0) + 1);
            if(left - 1 >= 0) {
                int l = nums.get(left - 1);
                sum -= l;
                count.put(l, count.get(l) - 1);
                if(count.get(l) == 0){
                    count.remove(l);
                }
            }
            //todo 虽然这里不影响结果，但是逻辑是有问题的。应该要判断left是否大于等于0，即是否形成了数组
            if(count.keySet().size() >= m) {
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
