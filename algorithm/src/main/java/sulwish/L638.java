package sulwish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。

给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[i] 是需要购买第 i 件物品的数量。

还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 j 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。

返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。
 */

public class L638 {
    int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new L638().shoppingOffers(Arrays.asList(9, 9), Arrays.asList(Arrays.asList(1, 1, 1)), Arrays.asList(2, 2)));
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        List<Integer> have = new ArrayList<>();
        for (int i = 0; i < price.size(); i++) {
            have.add(0);
        }
        dfs(price, special, needs, 0, have, 0);
        return min;
    }

    private void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int cur, List<Integer> have, int total) {
        if (cur == special.size()) {
            for (int i = 0; i < have.size(); i++) {
                total += (needs.get(i) - have.get(i)) * price.get(i);
            }
            min = Math.min(min, total);
        } else {
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < price.size(); i++) {
                System.out.println("i=" + i + ",cur=" + cur + ",sp size is " + special);
                if (special.get(cur).get(i) > 0) {
                    max = Math.min(max, (needs.get(i) - have.get(i)) / special.get(cur).get(i));
                }
            }

            System.out.println("max=" + max);

            for (int i = 0; i <= max; i++) {
                for (int j = 0; j < price.size(); j++) {
                    have.set(j, have.get(j) + i * special.get(cur).get(j));
                }
                dfs(price, special, needs, cur + 1, have, total + i * special.get(cur).get(price.size()));
                for (int j = 0; j < price.size(); j++) {
                    have.set(j, have.get(j) - i * special.get(cur).get(j));
                }
            }
        }
    }
}
