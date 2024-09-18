package index.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。

生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 */

public class L241 {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        int cur = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                cur = cur * 10 + (expression.charAt(i) - '0');
            } else {
                nums.add(cur);
                ops.add(expression.charAt(i));
                cur = 0;
            }
        }
        nums.add(cur);
        return new ArrayList<>(dfs(nums, ops, 0, nums.size() - 1));
    }

    private List<Integer> dfs(List<Integer> nums, List<Character> ops, int left, int right) {
        if (left > right) {
            return new ArrayList<>();
        } else if (left == right) {
            return Arrays.asList(nums.get(left));
        } else {
            List<Integer> ans = new ArrayList<>();
            for (int j = left + 1; j <= right; j++) {
                List<Integer> la = dfs(nums, ops, left, j - 1);
                List<Integer> ra = dfs(nums, ops, j, right);
                Character op = ops.get(j - 1);
                for (Integer x : la) {
                    for (Integer y : ra) {
                        ans.add(op(x, y, op));
                    }
                }
            }
            return ans;
        }
    }

    private Integer op(Integer a, Integer b, Character op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            return a / b;
        }
    }
}
