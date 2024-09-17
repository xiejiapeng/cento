package sulwish;

/*
n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。

每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。

如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。

就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。

给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：

dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
返回表示最终状态的字符串。

.L.R...LR..L..
LL.RR.LLRRLL..

 */

import org.apache.spark.sql.sources.In;

import java.util.Arrays;

public class L838 {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] leftR = new int[n];
        int[] rightL = new int[n];
        Arrays.fill(leftR, Integer.MAX_VALUE);
        Arrays.fill(rightL, Integer.MAX_VALUE);
        int id = -1;
        for (int i = 0; i < n; i++){
            if(dominoes.charAt(i) == 'R') {
                id = i;
            } else if(dominoes.charAt(i) == 'L'){
                id = -1;
            }
            if(id != -1)leftR[i] = i - id;
        }

        id = -1;
        for (int i = n - 1; i > -1; i--){
            if(dominoes.charAt(i) == 'L') {
                id = i;
            } else if(dominoes.charAt(i) == 'R'){
                id = -1;
            }
            if(id != -1)rightL[i] = id - i;
        }

        System.out.println(Arrays.toString(leftR));
        System.out.println(Arrays.toString(rightL));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            if(leftR[i] == rightL[i])sb.append('.');
            else if(leftR[i] < rightL[i])sb.append('R');
            else sb.append('L');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new L838().pushDominoes(".L.R...LR..L.."));
    }
}
