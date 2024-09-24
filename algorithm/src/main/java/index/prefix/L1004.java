package index.prefix;

/*
给定一个二进制数组 nums 和一个整数 k，
如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 */
public class L1004 {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++){
            nums[i] = 1 - nums[i];
        }

        int s = 0;
        int l = 0;
        int len = 0;
        //longest subarray with sum <= k
        for(int i = 0, j = -1; i < n; i++){
            s += nums[i];
            while (j < i && s-l>k) {
                j++;
                l += nums[j];
            }
            if(j != i) {
                len = Math.max(len, i - j);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(new L1004().longestOnes(new int[]{1,1,1,0}, 2));
    }
}
