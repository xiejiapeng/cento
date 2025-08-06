package linshen.structure.stack;

import java.util.LinkedList;
import java.util.List;
/*
给你一个整数数组 nums 。请你对数组执行下述操作：

从 nums 中找出 任意 两个 相邻 的 非互质 数。
如果不存在这样的数，终止 这一过程。
否则，删除这两个数，并 替换 为它们的 最小公倍数（Least Common Multiple，LCM）。
只要还能找出两个相邻的非互质数就继续 重复 这一过程。
返回修改后得到的 最终 数组。可以证明的是，以 任意 顺序替换相邻的非互质数都可以得到相同的结果。

生成的测试用例可以保证最终数组中的值 小于或者等于 108 。

提示：

1 <= nums.length <= 105
1 <= nums[i] <= 105
生成的测试用例可以保证最终数组中的值 小于或者等于 108 。

两个数字 x 和 y 满足 非互质数 的条件是：GCD(x, y) > 1 ，其中 GCD(x, y) 是 x 和 y 的 最大公约数 。
 */

public class L2197 {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> all = new LinkedList<>();
        for (int x : nums) {
            if(all.isEmpty() || gcd(all.getLast(), x) == 1) {
                all.addLast(x);
            } else {
                while (!all.isEmpty() && gcd(all.getLast(), x) != 1) {
                    int y = all.removeLast();
                    x = lcm(x, y);
                }
                all.addLast(x);
            }
        }
        return all;
    }

    private int gcd(int a, int b) {
        if(a < b)return gcd(b, a);
        if(a == b)return a;
        else {
            if(b == 1)return 1;
            if(a % b == 0)return b;
            return gcd(a-(a/b)*b, b);
        }
    }

    private int lcm(int a, int b) {
        int t = gcd(a, b);
        return t * (a / t) * (b / t);
    }
}
