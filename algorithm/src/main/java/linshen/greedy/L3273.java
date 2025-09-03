package linshen.greedy;

/*
给你一个整数 power 和两个整数数组 damage 和 health ，两个数组的长度都为 n 。

Bob 有 n 个敌人，如果第 i 个敌人还活着（也就是健康值 health[i] > 0 的时候），每秒钟会对 Bob 造成 damage[i] 点 伤害。

每一秒中，在敌人对 Bob 造成伤害 之后 ，Bob 会选择 一个 还活着的敌人进行攻击，该敌人的健康值减少 power 。

请你返回 Bob 将 所有 n 个敌人都消灭之前，最少 会受到多少伤害。
|-| * |---|
|---| * |-|

a b c
b a c

a -> x, b -> y, c -> z
u = a(t+x)+b(t+x+y)
v = b(t+y)+a(t+x+y)
u - v = a * -y + b * x

3 2 0 1

2 => 3,2
3 => 4,2
u - v = 3 * -2 + 4 * 2


1 99
5 100
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class L3273 {
    public long minDamage(int power, int[] damage, int[] health) {
        //todo hhhhh 这种紧凑型的区间问题，尝试交换区间顺序，如果结果有变大/变小，则约定了一种顺序
        int n = damage.length;
        for (int i = 0; i < n; i++){
            health[i] = (int)Math.ceil((double)health[i] / power);
        }
        List<Integer> l = IntStream.range(0, n).boxed().collect(Collectors.toList());
        Collections.sort(l, (o1, o2) -> damage[o1] * -1 * health[o2] + damage[o2] * health[o1]);
        long t = 0;
        long ans = 0;
        for (int i = 0; i < n; i++){
            int id = l.get(i);
            t += health[id];
            ans += t * damage[id];
        }
        return ans;
    }

    public static void main(String[] args) {
        /*
        2 1 3 0
        =>
         */
        System.out.println(new L3273().minDamage(4, new int[]{1,2,3,4}, new int[]{4,5,6,8}));
    }
}
