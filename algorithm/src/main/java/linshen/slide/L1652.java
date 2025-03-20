package linshen.slide;

/*
你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。

为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。

如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
如果 k == 0 ，将第 i 个数字用 0 替换。
由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。

给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
 */

import java.util.Arrays;

public class L1652 {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if(k > 0) {
            for (int i = 0; i < k; i++) {
                int j = (1 + i) % n;
                ans[0] += code[j];
            }
        } else if(k < 0) {
            for (int i = 0; i < -k; i++) {
                int j = (-1 - i + n) % n;
                ans[0] += code[j];
            }
        } else {
            ans[0] = 0;
        }

        for (int i = 1; i < n; i++) {
            if(k > 0) {
                ans[i] = ans[i-1] - code[i] + code[(i+k)%n];
            } else if(k < 0) {
                ans[i] = ans[(i-1+n)%n] + code[i] - code[(i-1-k+n)%n];
            } else {
                ans[i] = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L1652().decrypt(new int[]{5,7,1,4}, -2)));
    }
}
