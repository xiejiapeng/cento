package sulwish;

/*
给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。

每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:

0 <= j <= nums[i]
i + j < n
返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 */

public class L45v2 {
    public int jump(int[] nums) {
        int n = nums.length;
        if(n == 1)return 0;
        int steps = 0;
        int cur = 0;
        int limit = cur + nums[cur];
        while (limit < n - 1) {
            int maxLimit = limit;
            int next = cur;
            for (int i = cur; i <= limit; i++){
                if(i+nums[i] > maxLimit){
                    next = i;
                    maxLimit = i + nums[i];
                }
            }
            cur = next;
            limit = maxLimit;
            steps++;
            System.out.println("next pos is " + cur + ", next limit is " + limit);
        }
        return steps + 1;
    }

    public static void main(String[] args) {
        System.out.println(new L45v2().jump(new int[]{2,3,1,1,4}));
    }
}
