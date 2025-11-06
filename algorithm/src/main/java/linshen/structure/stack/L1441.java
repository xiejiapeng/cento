package linshen.structure.stack;

import java.util.ArrayList;
import java.util.List;

/*
给你一个数组 target 和一个整数 n。

给你一个空栈和两种操作：

"Push"：将一个整数加到栈顶。
"Pop"：从栈顶删除一个整数。
同时给定一个范围 [1, n] 中的整数流。

使用两个栈操作使栈中的数字（从底部到顶部）等于 target。你应该遵循以下规则：

如果整数流不为空，从流中选取下一个整数并将其推送到栈顶。
如果栈不为空，弹出栈顶的整数。
如果，在任何时刻，栈中的元素（从底部到顶部）等于 target，则不要从流中读取新的整数，也不要对栈进行更多操作。
请返回遵循上述规则构建 target 所用的操作序列。如果存在多个合法答案，返回 任一 即可。
 */

public class L1441 {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int last = 1;
        for (int i = 0; i < target.length; i++){
            while (last != target[i]) {
                ans.add("Push");
                ans.add("Pop");
                last++;
            }
            ans.add("Push");
            last++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L1441().buildArray(new int[]{1,3}, 3));
    }
}
