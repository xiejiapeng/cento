package sulwish;
/*
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。


 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class L179v2 {
    public String largestNumber(int[] nums) {
        List<String> ns = new ArrayList<>();
        for (int num : nums) {
            ns.add(String.valueOf(num));
        }
        Collections.sort(ns, (o1, o2) -> {
            String a = o1 + o2;
            String b = o2 + o1;
            return a.compareTo(b);
        });
        String ret = ns.stream().reduce("", (a,b) -> a + b);
        int i = 0;
        for(;i < ret.length(); i++){
            if(ret.charAt(i) != '0')break;
        }
        return ret.substring(i);
    }
}
