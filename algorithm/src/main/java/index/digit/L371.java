package index.digit;

/*
给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
 */

public class L371 {
    public static void main(String[] args) {
        /*
        01
        10
         */
        System.out.println(new L371().getSum(1, 2));
    }

    public int getSum(int a, int b) {
        int mask = 1;
        int ans = 0;
        int r = 0;
        for (int i = 0; i < 32; i++) {
            int x = a & mask;
            int y = b & mask;
            if (x == 0) {
                if (y == 0) {
                    if (r != 0) {
                        ans |= mask;
                        r = 0;
                    }
                } else {
                    if (r == 0) {
                        ans |= mask;
                    } else {
                        r = 1;
                    }
                }
            } else {
                if (y == 0) {
                    if (r == 0) {
                        ans |= mask;
                    } else {
                        r = 1;
                    }
                } else {
                    if (r == 0) {
                        r = 1;
                    } else {
                        ans |= mask;
                        r = 1;
                    }
                }
            }
            mask <<= 1;
        }
        return ans;
    }
}
