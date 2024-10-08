package index.dualpointer;

/*
给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。

每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处d
，你可以跳转到任意 nums[i + j] 处:

0 <= j <= nums[i]
i + j < n
返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。


 */

public class L45 {
    public int jump(int[] nums) {
        int n = nums.length;
        int maxId = nums[0];
        if(n == 1)return 0;
        if(maxId >= n - 1)return 1;
        int cur = 0;
        int cnt = 1;
        while (maxId < n) {
            int can = maxId;
            for (int i = cur; i <= maxId; i++){
                can = Math.max(can, i + nums[i]);
            }
            cur = maxId;
            maxId = can;
            cnt++;
            if(maxId == n - 1)break;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L45().jump(new int[]{2,3,1}));
    }
}
