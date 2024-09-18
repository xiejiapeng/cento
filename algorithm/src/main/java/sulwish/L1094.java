package sulwish;

/*
车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）

给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行
有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。

当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        int n = trips.length;
        int[][] all = new int[2*n][2];
        int t = 0;
        for(int i = 0; i < trips.length; i++){
            int p = trips[i][0];
            int from = trips[i][1];
            int to = trips[i][2];
            all[t++] = new int[]{from, -1 * p};
            all[t++] = new int[]{to, p};
        }
        Arrays.sort(all, (o1, o2) -> {
            if(o1[0] != o2[0])return Integer.compare(o1[0], o2[0]);
            else return -1 * Integer.compare(o1[1], o2[1]);
        });
        int sum = capacity;
        for (int i = 0; i < all.length; i++){
            sum += all[i][1];
            if(sum < 0)return false;
        }
        return true;
    }
}
