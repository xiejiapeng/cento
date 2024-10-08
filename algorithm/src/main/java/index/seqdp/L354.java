package index.seqdp;

/*
给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。

当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

注意：不允许旋转信封。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L354 {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            if(o1[0] != o2[0])return Integer.compare(o1[0], o2[0]);
            else {
                return -1 * Integer.compare(o1[1], o2[1]);
            }
        });
        int n = envelopes.length;
        int[] x = new int[n];
        for (int i = 0; i < n; i++){
            x[i] = envelopes[i][1];
        }
        return lis(x);
    }

    private int lis(int[] x) {
        int n = x.length;
        int[] ans = new int[n];
        int next = 0;
        for (int i = 0; i < n; i++){
            if(next == 0) {
                ans[next++] = x[i];
            } else {
                int id = find(ans, 0, next - 1, x[i]);
                if(id == next - 1) {
                    ans[next++] = x[i];
                } else {
                    ans[id+1] = x[i];
                }
            }
        }
        return next;
    }

    //find the largest index that less than x, -1 if all large than x
    private int find(int[] ans, int left, int right, int x) {
        if(left == right) {
            if(ans[left] < x)return left;
            else return -1;
        } else if(left == right - 1) {
            if(ans[right] < x)return right;
            else if(ans[left] < x)return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(ans[mid] < x){
                return find(ans, mid, right, x);
            } else {
                return find(ans, left, mid - 1, x);
            }
        }
    }
}
