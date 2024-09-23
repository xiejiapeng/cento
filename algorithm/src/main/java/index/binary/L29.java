package index.binary;
/*
给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。

整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。

返回被除数 dividend 除以除数 divisor 得到的 商 。

注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−231,  231 − 1] 。本题中，如果商 严格大于 231 − 1 ，
则返回 231 − 1 ；如果商 严格小于 -231 ，则返回 -231 。

+ + -
+ - +
- + +
- - -
 */

public class L29 {
    public int divide(int a, int b) {
        long dividend = (long)a;
        long divisor = (long)b;
        if(dividend == 0)return 0;
        long cnt = 0;
        boolean positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        if(positive) {
            long initial = divisor;
            long round = 1;
            if(dividend > 0) {
                while (dividend - initial >= 0) {
                    dividend -= initial;
                    cnt += round;
                    initial = initial + initial;
                    round = round + round;
                    if(dividend - initial < 0) {
                        initial = divisor;
                        round = 1;
                    }
                }
            } else {

                while (dividend - initial <= 0) {
                    dividend -= initial;
                    cnt += round;
                    initial = initial + initial;
                    round = round + round;
                    if(dividend - initial > 0) {
                        initial = divisor;
                        round = 1;
                    }
                }
            }
            if(cnt >= Integer.MAX_VALUE)return Integer.MAX_VALUE;
        } else {
            long initial = divisor;
            long round = 1;
            //8 / -3
            if(dividend > 0) {
                while (dividend + initial >= 0) {
                    dividend += initial;
                    cnt -= round;
                    initial = initial + initial;
                    round = round + round;
                    if(dividend + initial < 0) {
                        initial = divisor;
                        round = 1;
                    }
                }
            } else {
                while (dividend + initial <= 0) {
                    dividend += initial;
                    cnt -= round;
                    initial = initial + initial;
                    round = round + round;
                    if(dividend + initial > 0) {
                        initial = divisor;
                        round = 1;
                    }
                }

            }
            if(cnt <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int)cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L29().divide(10, 3));
        System.out.println(new L29().divide(10, -3));
        System.out.println(new L29().divide(-10, 3));
        System.out.println(new L29().divide(-10, -3));

        System.out.println(new L29().divide(9, 3));
        System.out.println(new L29().divide(9, -3));
        System.out.println(new L29().divide(-9, 3));
        System.out.println(new L29().divide(-9, -3));

        System.out.println(new L29().divide(-2147483648, -2147483648));
        System.out.println(new L29().divide(-2147483648, -1));
    }
}
