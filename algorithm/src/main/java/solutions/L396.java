package solutions;

import java.util.Arrays;

public class L396 {
    /*
        nums[0],...,nums[n-1]
        0 * a0 + 1 * a1 + ... + (n-1)an-1
        0 * a1 + ... + (n-2)an-1 + (n-1)a0

        0 * a2 + ... + (n-3)an-1 + (n-2)a0 + (n-1)a1

        (n-1)a1 - (a0 + a2 + ... + an-1)
        =na1 - sum

        (n-1)a0 - (a1,...,an-1)
        na0 - sum

        sum
        na0
        na1
        ...
        nan-2

        1 2
        0 * 1 + 1 * 2 = 2
        0 * 2 + 1 * 0 = 0
        sum=3
     */
    public int maxRotateFunction(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int last = 0;
        for(int i = 0; i < nums.length; i++){
            last += i * nums[i];
        }
        int max = last;
        for(int i = 1; i < nums.length; i++){
            int cur = last + nums.length * nums[i-1] - sum;
            max = Math.max(max, cur);
            last = cur;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L396().maxRotateFunction(new int[]{1,2,3,4,5,6,7,8,9,10}));
    }
}
