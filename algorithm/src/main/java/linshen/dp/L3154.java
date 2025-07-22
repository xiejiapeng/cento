package linshen.dp;

/*
给你有一个 非负 整数 k 。有一个无限长度的台阶，最低 一层编号为 0 。

Alice 有一个整数 jump ，一开始值为 0 。Alice 从台阶 1 开始，可以使用 任意 次操作，
目标是到达第 k 级台阶。假设 Alice 位于台阶 i ，一次 操作 中，Alice 可以：

向下走一级到 i - 1 ，但该操作 不能 连续使用，如果在台阶第 0 级也不能使用。
向上走到台阶 i + 2^jump 处，然后 jump 变为 jump + 1 。
请你返回 Alice 到达台阶 k 处的总方案数。

注意，Alice 可能到达台阶 k 处后，通过一些操作重新回到台阶 k 处，这视为不同的方案。
 */

import java.util.HashMap;
import java.util.Map;

public class L3154 {
    Map<Integer, Map<Integer,Integer>> up = new HashMap<>();
    Map<Integer, Map<Integer,Integer>> tmp = new HashMap<>();
    public int waysToReachStair(int k) {
        return ways(1, k, 0, false);
    }

    public int ways(int start, int k, int jump, boolean lastDown) {
        if(start > k + 10)return 0;
        if(lastDown) {
            if(up.containsKey(start) && up.get(start).containsKey(jump)){
                return up.get(start).get(jump);
            } else {
                //todo m 可以延迟构建hashmap，否则内存会超出
                if(!up.containsKey(start))up.put(start, new HashMap<>());
                int t = start == k ? 1 : 0;
                if(start <= k)t += ways(start + ((int)Math.pow(2, jump)), k, jump + 1, false);
                up.get(start).put(jump, t);
            }
            return up.get(start).get(jump);
        } else {
            if(tmp.containsKey(start) && tmp.get(start).containsKey(jump)){
                return tmp.get(start).get(jump);
            } else {
                if(!tmp.containsKey(start))tmp.put(start, new HashMap<>());
                int t = start == k ? 1 : 0;
                if(start <= k)t += ways(start + ((int)Math.pow(2, jump)), k, jump + 1, false);
                if(start != 0)t += ways(start -1, k, jump, true);
                tmp.get(start).put(jump, t);
            }
            return tmp.get(start).get(jump);
        }

    }

    public static void main(String[] args) {
        System.out.println(new L3154().waysToReachStair(0));
    }
}
