package linshen.dp;

/*
在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，
先使得累计整数和 达到或超过 100 的玩家，即为胜者。

如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？

例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。

给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），
若先出手的玩家能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。

提示:

1 <= maxChoosableInteger <= 20
0 <= desiredTotal <= 300
 */

import java.util.*;

public class L464 {
    //todo hhhhhhhhhh 最高优先级
    /*
    1. 定义res不要用嵌套map，状态太细会很慢，比如state和剩下的desired可以互相推断，重复定义在map中会冗余
    2. ones方法实际不需要，会带来复杂度的略微增加
    3. 判断能不能win，不要考虑太复杂（比如自己先决策，别人再决策，自己在canwin，太复杂了），站在自己的角度，别人不能win就是自己win，这是二元的，这也是canwin的定义与本质
     */
    Map<Integer, Boolean> res = new HashMap<>();
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int state = 0;
        for (int i = 1; i <= maxChoosableInteger; i++){
            state |= (1 << (i-1));
        }
        int totalSum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if (totalSum < desiredTotal) return false;
        return canIWin(maxChoosableInteger, desiredTotal, state);
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal, int state) {
        if(res.containsKey(state)) {
            return res.get(state);
        }
        List<Integer> ones = ones(state);
        boolean ans;
        boolean can = false;
        for (int x : ones) {
            if(x >= desiredTotal) {
                can = true;
                break;
            } else {
                int ns = state & (~(1 << (x-1)));
                if(!canIWin(maxChoosableInteger, desiredTotal - x, ns))can = true;
            }
        }
        ans = can;
        res.put(state, ans);
        return ans;
    }


    public List<Integer> ones(int state) {
        List<Integer> os = new ArrayList<>();
        for (int i = 0; i < 32; i++){
            if((state & (1 << i)) != 0) {
                os.add(i + 1);
            }
        }
        return os;
    }

    public static void main(String[] args) {
        System.out.println(new L464().canIWin(20, 209));
    }

    class Solution {
        Map<Integer, Boolean> memo = new HashMap<>();

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            int total = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
            if (total < desiredTotal) return false; // 无论怎么选都不够
            if (desiredTotal <= 0) return true;      // 马上赢

            return dfs(maxChoosableInteger, desiredTotal, 0);
        }

        // state 是一个 bitmask，1 表示该数字已被选
        private boolean dfs(int maxChoosableInteger, int desiredTotal, int state) {
            if (memo.containsKey(state)) return memo.get(state);

            for (int i = 0; i < maxChoosableInteger; i++) {
                // 检查第 i+1 个数是否已被选
                if ((state & (1 << i)) != 0) continue;

                int chosen = i + 1;
                // 如果我选这个数就赢，或者对方无论怎么选都输
                if (chosen >= desiredTotal || !dfs(maxChoosableInteger, desiredTotal - chosen, state | (1 << i))) {
                    memo.put(state, true);
                    return true;
                }
            }

            memo.put(state, false);
            return false;
        }
    }
}
