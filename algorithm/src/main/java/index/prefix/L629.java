package index.prefix;

/*
对于一个整数数组 nums，逆序对是一对满足 0 <= i < j < nums.length 且 nums[i] > nums[j]
的整数对 [i, j] 。

给你两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个 逆序对
的不同的数组的个数。由于答案可能很大，只需要返回对 109 + 7 取余的结果。

1 <= n <= 1000
0 <= k <= 1000
 */

import java.util.Arrays;

public class L629 {
    int mod = (int)(1e9+7);
    public int kInversePairs(int n, int k) {
        long[][] f = new long[n+1][k+1];

        long[] sum = new long[k+2];

        for(int j = 1; j <= n; j++){
            long[] ns = new long[k+2];
            //n n-1 .. 3 2 1
            for (int i = 0; i <= k; i++){
                if(i > (n-1)*(n-2) / 2) {
                    f[j][i] = 0;
                } else if(j == (n-1) *(n-2)/2) {
                    f[j][i] =1;
                } else if(i == 0){
                    f[j][i] = 1;
                } else {
                    //f[j-1][0] + ...+f[j-1][i]
                    f[j][i] = sum[i+1];

                }
                ns[i+1] = f[j][i] + ns[i];
            }
            sum = ns;
        }

        for (int i = 1; i <= n; i++){
//            System.out.println(Arrays.toString(f[i]));
        }

        return (int)(f[n][k] % mod);
    }

    public static void main(String[] args) {
        System.out.println(new L629().kInversePairs(45, 67));
    }
}
