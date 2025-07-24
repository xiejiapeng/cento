package linshen.structure;

/*
给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
请你返回「表现良好时间段」的最大长度。
 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class L1124 {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        for (int i = 0; i < n; i++){
            hours[i] = hours[i] > 8 ? 1 : -1;
        }
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + hours[i-1];
        }
        int max = 0;
        TreeMap<Integer, Integer> see = new TreeMap<>();
        see.put(0, 0);
        for (int i = 0; i < n; i++){
            if(i == 3){
                System.out.println("f");
            }
            int s = sum[i+1];
            //s - t > 0
            Integer t = see.lowerKey(s);
            if(t != null) {
                max = Math.max(max, i - see.get(t) + 1);
            }
            int lowest = see.lastKey();
            //todo h 注意，相同的key，选取最远的那个
            if(s < lowest && !see.containsKey(s))see.put(s, i + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L1124().longestWPI(new int[]{8,10,6,16,5}));
    }
}
