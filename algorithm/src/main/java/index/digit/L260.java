package index.digit;

/*
给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。

你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 */

import java.util.Arrays;

public class L260 {
    public static void main(String[] args) {
        /*
            10011110010100111010010010010110
            10011110010100111010010010010110
            10000011110001110110100001011000
            10000011110001110110100001011000
            00111111010111100110001101001001
            00001001100110000111101111011011
         */
        System.out.println(Integer.toBinaryString(-1638685546));
        System.out.println(Integer.toBinaryString(-2084083624));
        System.out.println(Integer.toBinaryString(1063150409));
        System.out.println(Integer.toBinaryString(160988123));
        System.out.println(Arrays.toString(new L260().singleNumber(new int[]{-1638685546, -2084083624, -1638685546, -2084083624, 1063150409, 160988123})));
    }

    public int[] singleNumber(int[] nums) {
        int mask = 1;
        boolean find = false;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int n : nums) {
                cnt += ((n & mask) == 0 ? 0 : 1);
            }
            System.out.println("cnt is " + cnt);
            if (cnt % 2 != 0) {
                find = true;
            }
            if (find) break;
            mask <<= 1;

        }


        System.out.println(mask);

        int left = 0;
        int right = 0;
        for (int n : nums) {
            if ((n & mask) == 0) {
//                System.out.println("left:" + n);
                left ^= n;
            } else {
//                System.out.println("right:" + n);
                right ^= n;
            }
        }
        return new int[]{left, right};
    }
}
