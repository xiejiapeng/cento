package sulwish;

import java.util.ArrayList;
import java.util.List;

/*
给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。

生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 */
public class L241 {
    public static void main(String[] args) {
        System.out.println(new L241().diffWaysToCompute("2-1-1"));
    }

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        int cur = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (!isNum(expression.charAt(i))) {
                nums.add(cur);
                ops.add(expression.charAt(i));
                cur = 0;
            } else {
                cur = cur * 10 + (expression.charAt(i) - '0');
            }
        }
        nums.add(cur);

        int n = nums.size();
        List<Integer>[][] dp = new List[n][n];
        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;
                if (len == 1) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums.get(left));
                    dp[left][right] = l;
                } else {
                    List<Integer> l = new ArrayList<>();
                    for (int k = left; k < right; k++) {
                        List<Integer> l1 = dp[left][k];
                        List<Integer> l2 = dp[k + 1][right];
                        for (int x : l1) {
                            for (int y : l2) {
                                if (ops.get(k) == '+') l.add(x + y);
                                else if (ops.get(k) == '-') l.add(x - y);
                                else l.add(x * y);
                            }
                        }
                    }
                    dp[left][right] = l;
                }
            }
        }
        return dp[0][n - 1];
    }

    public boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }
}
