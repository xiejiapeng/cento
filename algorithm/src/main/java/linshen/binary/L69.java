package linshen.binary;

/*
给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。

0 <= x <= 231 - 1
t*t <= x
x % t == 0, t <= x / t
x = tm+n, t2m2 + 2mnt + n2 <= x
 */

public class L69 {
    public int mySqrt(int x) {
        long a = findLargest(x, 0, x);
        return (int)a;
    }

    private long findLargest(long x, long left, long right) {
        if(left == right)return left;
        else if(left == right - 1) {
            if(right * right <= x)return right;
            else return left;
        } else {
            long mid = (left + right) / 2;
            if(mid * mid <= x)return findLargest(x, mid, right);
            else return findLargest(x, left, mid - 1);
        }
    }
}
