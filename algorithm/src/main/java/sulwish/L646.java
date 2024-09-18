package sulwish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/*
给你一个由 n 个数对组成的数对数组 pairs ，其中 pairs[i] = [lefti, righti] 且 lefti < righti 。

现在，我们定义一种 跟随 关系，当且仅当 b < c 时，数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面。我们用这种形式来构造 数对链 。

找出并返回能够形成的 最长数对链的长度 。

你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 */
public class L646 {
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, Comparator.comparingInt(o -> o[1]));
        int[] ans = new int[n];
        ans[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if(pairs[j][1] < pairs[i][0]){
                    ans[i] = Math.max(ans[i], 1 + ans[j]);
                }
            }

            max = Math.max(max, ans[i]);
        }
        return max;

    }



    public static void main(String[] args) {
        int[][] pairs = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
        System.out.println(new L646().findLongestChain(pairs));
    }
}
