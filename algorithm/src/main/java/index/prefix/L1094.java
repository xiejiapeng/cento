package index.prefix;

/*
车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）

给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。

当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105
 */

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class L1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> all = new TreeMap<>();
        for (int[] trip : trips) {
            int from = trip[1];
            int to = trip[2];
            int ps = trip[0];
            all.put(from ,all.getOrDefault(from, 0) - ps);
            all.put(to, all.getOrDefault(to, 0) + ps);
        }

        AtomicInteger cap = new AtomicInteger(capacity);
        AtomicBoolean failed = new AtomicBoolean(false);
        all.navigableKeySet().iterator().forEachRemaining(time -> {
            cap.set(cap.get() + all.get(time));
            if(cap.get() < 0) {
                failed.set(true);
            }
        });
        return !failed.get();
    }
}
