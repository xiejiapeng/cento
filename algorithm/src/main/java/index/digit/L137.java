package index.digit;

/*
给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。

你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
10
10
10
11
 */

public class L137 {
    public static void main(String[] args) {
        /*
            11111111111111111111111111111110 => -2
            00000000000000000000000000000001 => 1
            00000000000000000000000000000100 => 4

            11111111111111111111111111111100 => -4
         */
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(-4));
        System.out.println(new L137().singleNumber(new int[]{-2, -2, 1, 1, 4, 1, 4, 4, -4, -2}));
    }

    public int singleNumber(int[] nums) {
        int mask = 1;
        int ans = 0;
        for (int i = 0; i < 32; i++) {

            int m = 0;
            for (int n : nums) {
                m += ((n & mask) == 0 ? 0 : 1);
            }
            if (m % 3 != 0) {
                ans |= mask;
            }

            mask <<= 1;
        }
        return ans;
    }
}
