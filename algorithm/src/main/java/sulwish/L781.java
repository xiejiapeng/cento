package sulwish;

/*
森林中有未知数量的兔子。提问其中若干只兔子 "还有多少只兔子与你
（指被提问的兔子）颜色相同?" ，将答案收集到一个整数数组 answers 中，
其中 answers[i] 是第 i 只兔子的回答。

给你数组 answers ，返回森林中兔子的最少数量。


 */

import java.util.HashMap;
import java.util.Map;

public class L781 {
    public int numRabbits(int[] answers) {
        Map<Integer,Integer> buckets = new HashMap<>();
        int ans = 0;
        for (int x : answers) {
            int target = x + 1;
            if(buckets.containsKey(target)) {
                if(buckets.get(target) == target){
                    ans += target;
                    buckets.remove(target);
                    buckets.put(target, 1);
                } else {
                    buckets.put(target, buckets.get(target)+1);
                }
            } else {
                buckets.put(target, 1);
            }

        }
        for (int target : buckets.keySet()) {
            ans += target;
        }
        return ans;
    }
}
