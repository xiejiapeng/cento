package index.binary;

/*
几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第 k 小的数字吗？

乘法表是大小为 m x n 的一个整数矩阵，其中 mat[i][j] == i * j（下标从 1 开始）。

给你三个整数 m、n 和 k，请你在大小为 m x n 的乘法表中，找出并返回第 k 小的数字。

1 <= m, n <= 3 * 104
1 <= k <= m * n
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L668 {
    public int findKthNumber(int m, int n, int k) {
        return find(1, m * n, m, n, k);
    }

    /*
    1 2 2 3 3 4 6 6 9
     */
    private boolean canK(int m, int n, int x, int k) {
        int small = small(m, n, x);
        int large = large(m, n, x);
        return k > small && k <= m * n - large;
    }

    private int find(int left, int right, int m, int n, int k) {
        if(left == right) {
            if(canK(m, n, left, k))return left;
            else return -1;
        } else if(left == right - 1) {
            if(canK(m, n, left, k) && left % m == 0)return left;
            else if(canK(m, n, right, k) && right % m == 0)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(mid % m != 0)mid = mid / m * m;
            if(canK(m, n, mid, k))return mid;
            else {
                int sm = small(m, n, mid);
                if(sm >= k) return find(left, mid - 1, m, n, k);
                else return find(mid + 1, right, m, n, k);
            }
        }
    }

    //[.., x,x,x, ...]
    private int small(int m, int n, int x) {
        int ans = 0;
        for(int i = 1; i <= m; i++) {
            if(x % i == 0) {
                ans += Math.min(n, x / i - 1);
            } else {
                ans += Math.min(n, x / i);
            }
        }
        return ans;
    }

    private int large(int m, int n, int x) {
        int ans = 0;
        for(int i = 1; i <= m; i++){
            if(x % i == 0) {
                ans += Math.max(0, n - x / i);
            } else {
                ans += Math.max(0, n - x / i);
            }
        }
        return ans;
    }

    /*
    1 2 2 3 3 4 6 6 9
     */
    public static void main(String[] args) {
        System.out.println(new L668().findKthNumber(3, 3, 5));
    }

}
