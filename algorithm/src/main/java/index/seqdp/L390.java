package index.seqdp;

/*
列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：

从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
给你整数 n ，返回 arr 最后剩下的数字。
* # * # *
 */

public class L390 {
    public int lastRemaining(int n) {
        if(n == 1)return 1;
        else {
            if(n % 2 == 0) {
                int t = lastRemaining(n / 2);
                return n - 2 * t + 2;
            } else {
                int t = lastRemaining(n / 2);
                return n - 2 * t + 1;
            }
        }
    }
}
