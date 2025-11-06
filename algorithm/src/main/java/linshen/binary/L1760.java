package linshen.binary;
/*
给你一个整数数组 nums ，其中 nums[i] 表示第 i 个袋子里球的数目。同时给你一个整数 maxOperations 。

你可以进行如下操作至多 maxOperations 次：

选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有 正整数 个球。
<ul>
	<li>比方说，一个袋子里有 <code>5</code> 个球，你可以把它们分到两个新袋子里，分别有 <code>1</code> 个和 <code>4</code> 个球，或者分别有 <code>2</code> 个和 <code>3</code> 个球。</li>
</ul>
</li>
你的开销是单个袋子里球数目的 最大值 ，你想要 最小化 开销。

请你返回进行上述操作后的最小开销。
 */

import java.util.Arrays;

public class L1760 {
    public int minimumSize(int[] nums, int maxOperations) {
        int m = Arrays.stream(nums).max().getAsInt();
        return findLeast(nums, maxOperations, 1, m);
    }

    private int findLeast(int[] nums, int maxOperations, int left, int right) {
        if(left == right)return left;
        else if(left == right - 1) {
            if(check(nums, maxOperations, left))return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(check(nums, maxOperations, mid))return findLeast(nums, maxOperations, left, mid);
            else return findLeast(nums, maxOperations, mid + 1, right);
        }
    }

    private boolean check(int[] nums, int maxOperations, int target) {
        int cnt = 0;
        for (int x : nums) {
            //todo 记得减1
            int u = (x + target - 1) / target - 1;
            cnt += u;
            if(cnt > maxOperations)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L1760().minimumSize(new int[]{9}, 2));
    }
}
