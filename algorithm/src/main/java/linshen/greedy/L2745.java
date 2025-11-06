package linshen.greedy;

/*
给你三个整数 x ，y 和 z 。

这三个整数表示你有 x 个 "AA" 字符串，y 个 "BB" 字符串，和 z 个 "AB" 字符串。
你需要选择这些字符串中的部分字符串（可以全部选择也可以一个都不选择），
将它们按顺序连接得到一个新的字符串。新字符串不能包含子字符串 "AAA" 或者 "BBB" 。

请你返回 新字符串的最大可能长度。

1 39 14

BBAABBABABABAB = 2 + 4 + 14*2

子字符串 是一个字符串中一段连续 非空 的字符序列。
提示：

1 <= x, y, z <= 50
 */

public class L2745 {
    int[][][] a = new int[51][51][51];
    int[][][] b = new int[51][51][51];
    public int longestString(int x, int y, int z) {
        for (int i = 0; i <= 50; i++){
            for (int j = 0; j <= 50; j++){
                for (int k = 0; k <= 50; k++){
                    a[i][j][k] = -1;
                    b[i][j][k] = -1;
                }
            }
        }
        return Math.max(findA(x,y,z), findB(x,y,z));
    }

    private int findA(int x, int y, int z) {
        if(a[x][y][z] != -1)return a[x][y][z];
        //start with AA
        int u = 0;
        if(x > 0) {
            if(y > 0)u = 4 + findA(x-1,y-1,z);
            else u = 2;
        }
        //start with AB
        int v = 0;
        if(z > 0) {
            v = 2 + findA(x,y,z-1);
        }
        int ans = Math.max(u,v);
        a[x][y][z] = ans;
        return ans;
    }

    private int findB(int x, int y, int z){
        if(y > 0) {
            return 2 + findA(x, y-1, z);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new L2745().longestString(34,47,38));
    }
}
