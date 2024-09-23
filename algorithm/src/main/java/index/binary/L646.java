package index.binary;

/*
给你一个由 n 个数对组成的数对数组 pairs ，其中 pairs[i] = [lefti, righti] 且 lefti < righti 。

现在，我们定义一种 跟随 关系，当且仅当 b < c 时，数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面。我们用这种形式来构造 数对链 。

找出并返回能够形成的 最长数对链的长度 。

你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L646 {
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        int[][] ps = new int[n][3];
        for(int i = 0; i < n; i++){
            ps[i] = new int[]{pairs[i][0], pairs[i][1], i};
        }
        Arrays.sort(ps, Comparator.comparingInt(o -> o[1]));
        int left = 0;
        int ans = 1;
        while (left < n) {
            int right = left + 1;
            while (right < n && ps[right][0] <= ps[left][1]) {
                right++;
            }
            if(right < n) {
                ans++;
                left = right;
            } else {
                break;
            }
        }
        return ans;
    }
}
