package sulwish;
/*
给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。

注意 这个数列必须是 严格 递增的。
 */

import java.util.Arrays;

public class L673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] cnt = new int[n];
        int max = 1;
        int maxCnt = 0;
        for (int i = 0; i < nums.length; i++){
            ans[i] = 1;
            cnt[i] = 1;

            for (int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    if(1 + ans[j] > ans[i]){
                        ans[i] = 1 + ans[j];
                        cnt[i] = cnt[j];
                    } else if(1 + ans[j] == ans[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if(ans[i] > max){
                max = ans[i];
                maxCnt = cnt[i];
            } else if(ans[i] == max){
                maxCnt += cnt[i];
            }

        }
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(cnt));
        return maxCnt;
    }

    public static void main(String[] args) {
        System.out.println(new L673().findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
    }
}
