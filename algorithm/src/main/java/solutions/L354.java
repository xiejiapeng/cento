package solutions;

import java.util.Arrays;
import java.util.Comparator;

public class L354 {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            if(o1[0] == o2[0])return Integer.compare(o2[1],o1[1]);
            else return Integer.compare(o1[0],o2[0]);
        });

        int n = envelopes.length;
        int[] f = new int[n];
        int len = 0;
        f[len] = envelopes[0][1];
        //找到第一个>=e[i][1]的元素
        for(int i = 1; i < n; i++){
            int t = find(f, len, envelopes[i][1]);
            if(t != -1)f[t] = envelopes[i][1];
            else f[++len] = envelopes[i][1];
        }
        return len  + 1;
    }

    private int find(int[] f, int len, int x){
        int left = 0;
        int right = len;
        while (left <= right){
            if (left == right) {
                if(f[left] >= x)return left;
                else return -1;
            } else if(left == right - 1) {
                if(f[left] >= x)return left;
                else if(f[right] >= x)return right;
                else return -1;
            } else {
                int mid = (left + right) / 2;
                if(f[mid] >= x){
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
        return -1;
    }
}
