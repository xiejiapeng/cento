package solutions;

/*
给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
1~9 1*9
10~99 2*90
100~999 3*900

n
[1,dig] => 0
[dig+1,dig*2] => 1
[...,dig*num] => num-1

n-1
[0,dig) => 0
[dig,dig*2) => 1

第x个数，第y位
(x-1)*dig+y = n
 */
public class L400 {
    public int findNthDigit(int n) {
        long last = 9;
        int dig = 1;
        while(n > last * dig) {
            n -= last * dig;
            last *= 10;
            dig += 1;
        }


        int num = (n-1) / dig;
        long t = (long)(Math.pow(10,dig-1)) + num;
        String s = String.valueOf(t);
        return s.charAt((n-1) % dig) - '0';

    }

    public static void main(String[] args) {
        System.out.println(new L400().findNthDigit(1000000000));
    }
}
