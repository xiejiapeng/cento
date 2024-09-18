package sulwish;

import java.util.Arrays;
import java.util.List;

/*
给定一个字符串数组 arr，字符串 s 是将 arr 的含有 不同字母 的 子序列 字符串 连接 所得的字符串。

请返回所有可行解 s 中最长长度。

子序列 是一种可以从另一个数组派生而来的数组，通过删除某些元素或不删除元素而不改变其余元素的顺序。
 */

public class L1239 {
    int maxLen = -1;

    public static void main(String[] args) {
        System.out.println(new L1239().maxLength(Arrays.asList("aa", "bb")));
    }

    public int maxLength(List<String> arr) {
        int n = arr.size();
        int[][] arr2 = new int[n][26];
        for (int i = 0; i < n; i++) {
            arr2[i] = al(arr.get(i));
        }
        dfs(arr, arr2, 0, new int[26], 0);
        return maxLen;
    }

    private void dfs(List<String> ss, int[][] arr, int cur, int[] tmp, int total) {
        if (cur == arr.length) {
            maxLen = Math.max(maxLen, total);
        } else {
            //add cur
            if (canAdd(tmp, arr[cur])) {
                int[] add = add(tmp, arr[cur]);
                dfs(ss, arr, cur + 1, add, total + ss.get(cur).length());
            }

            //don't add
            dfs(ss, arr, cur + 1, tmp, total);
        }
    }

    public int[] al(String s) {
        int[] a = new int[26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
        }
        return a;
    }

    public int[] add(int[] a, int[] b) {
        int[] c = new int[26];
        for (int i = 0; i < 26; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public boolean canAdd(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (b[i] > 1) return false;
            if (a[i] > 0 && b[i] > 0) return false;
        }
        return true;
    }
}
