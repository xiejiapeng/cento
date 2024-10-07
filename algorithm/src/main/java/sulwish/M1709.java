package sulwish;

/*
有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，
而是必须不包含其他的素因子。
例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M1709 {
    public int getKthMagicNumber(int k) {
        List<Integer> all = new ArrayList<>();
        Set<Integer> see = new HashSet<>();
        all.add(1);
        see.add(1);
        int i = 0;
        int j = 0;
        int t = 0;
        while (all.size() < k) {
            int n3 = all.get(i) * 3;
            int n5 = all.get(j) * 5;
            int n7 = all.get(t) * 7;

            if(n3 <= n5 && n3 <= n7){
                if(!see.contains(n3)){
                    all.add(n3);
                    see.add(n3);
                }
                i++;
            } else if(n5 <= n3 && n5 <= n7){
                if(!see.contains(n5)){
                    all.add(n5);
                    see.add(n5);
                }
                j++;
            } else {
                if(!see.contains(n7)){
                    all.add(n7);
                    see.add(n7);
                }
                t++;
            }
        }
        return all.get(k-1);
    }
}
