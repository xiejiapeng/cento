package sulwish;

/*
对于一个整数数组 nums，逆序对是一对满足 0 <= i < j < nums.length
且 nums[i] > nums[j]的整数对 [i, j] 。

给你两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个 逆序对 的不同的数组的个数。
由于答案可能很大，只需要返回对 109 + 7 取余的结果。
 */

import java.util.Arrays;

public class L629 {
    int mod = (int)(1e9+7);
    public int kInversePairs(int n, int k) {
        long[][] f = new long[n+1][k+1];
        long[] s = new long[k+1];
        s[0] = 0;
        //[1,i]
        for (int i = 1; i <= n; i++){
            long[] s2 = new long[k+1];
            for (int j = 0; j <= k; j++){
                if(j == 0)f[i][j] = 1l;
                else if(i == 1)f[i][j] = 0l;
                else {
                    //将最大的元素i放置在t处,1...t-1,t,t+1,...,i
//                    for (int t = Math.max(1,i-j); t <= i; t++){
//                        f[i][j] += f[i-1][j-(i-t)];
//                          f[i][j] = f[i-1][start]+...+f[i-1][j]
//                        f[i][j] %= mod;
//                    }

                    int start = j-(i-Math.max(1, i-j));
//                    System.out.println("i="+i+",j="+j+",start="+start);
                    f[i][j] = s[j]-(start==0?0:s[start-1]);
                    f[i][j] %= mod;
                    if (f[i][j] < 0) {
                        System.out.println("i="+i+",j="+j+",s[j]="+s[j]);
                    }

                }
                s2[j] = (j-1>=0?s2[j-1]:0) + f[i][j];

//                s2[j] %= mod;
                if(s2[j] < 0){
                    System.out.println("i="+i+",j="+j+",f[i][j]="+f[i][j]);
                }



            }
            System.out.println(Arrays.toString(s2));
            s = s2;
        }
        return (int)(f[n][k] % mod);
    }

    public static void main(String[] args) {
        //123 132 213 231 312 321
        //0 1 1 2 2 3
        System.out.println(new L629().kInversePairs(999,999));
    }
}
