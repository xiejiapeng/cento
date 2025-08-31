package linshen.greedy;

//给你一个下标从 0 开始的整数数组 nums 。每次操作中，你可以将数组中任何一个元素替换为 任意两个 和为该元素的数字。
//
//
// 比方说，nums = [5,6,7] 。一次操作中，我们可以将 nums[1] 替换成 2 和 4 ，将 nums 转变成 [5,2,4,7] 。
//
//
// 请你执行上述操作，将数组变成元素按 非递减 顺序排列的数组，并返回所需的最少操作次数。

/*
a > b
a = x + y
b, ...,
b-1, ...

x+y=bt+s

(b-1)*u+b*v=b*(u+v)-u=b(u+v-1)+(b-u)
 */


public class L2366 {
    public long minimumReplacement(int[] nums) {
        int n = nums.length;
        int last = nums[n-1];
        long ans = 0;
        for (int i = n - 2; i > -1; i--){
            if(nums[i] > last) {
                int t = nums[i] / last;
                int s = nums[i] % last;
                if(s == 0) {
                    ans += t - 1;
                } else {
                    //todo hhhhh 这里的ceil和下面更新last用到的floor；不要想复杂了
                    int cnt = (int)Math.ceil((double)nums[i] / last);
                    ans += (cnt - 1);
                    last = nums[i] / cnt;
                }
            } else {
                last = nums[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L2366().minimumReplacement(new int[]{12,9,7,6}));
    }
}
