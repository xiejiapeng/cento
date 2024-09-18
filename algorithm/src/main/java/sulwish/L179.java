package sulwish;

/*
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class L179 {
    public String largestNumber(int[] nums) {
        List<Integer> ns = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(ns, (o1, o2) -> {
            String s1 = "" + o1 + o2;
            String s2 = "" + o2 + o1;
            return -1 * s1.compareTo(s2);
        });
        StringBuilder sb = new StringBuilder();
        for (int x : ns) {
            sb.append(x);
        }
        String a = sb.toString();
        int i = 0;
        for (; i <= a.length(); i++){
            if(a.charAt(i) != '0')break;
        }
        if(i == a.length()){
            return "0";
        } else {
            return a.substring(i);
        }
    }
}
