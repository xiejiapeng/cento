package sulwish;

/*
我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。

您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。

返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。

注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
 */

public class L691 {
    int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new L691().minStickers(new String[]{"search", "win", "field", "if"}, "awd"));
    }

    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] all = new int[n][26];
        for (int i = 0; i < n; i++) {
            all[i] = alberts(stickers[i]);
        }
        dfs(all, alberts(target), 0, new int[26], 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void dfs(int[][] all, int[] target, int cur, int[] have, int cnt) {
        if (cur == all.length) {
            boolean can = true;
            for (int i = 0; i < 26; i++) {
                if (have[i] < target[i]) can = false;
            }
            if (can) {
                min = Math.min(min, cnt);
                System.out.println(min);
            }
            return;
        } else {
            int[] ca = all[cur];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < 26; i++) {
                if (ca[i] == 0 || have[i] >= target[i]) continue;
                int should = (target[i] - have[i]) % ca[i] == 0 ? (target[i] - have[i]) / ca[i] : ((target[i] - have[i]) / ca[i] + 1);
                max = Math.max(max, should);
            }
            if (max == Integer.MIN_VALUE) {
                dfs(all, target, cur + 1, have, cnt);
            } else {
                for (int i = 0; i <= max; i++) {
                    for (int j = 0; j < 26; j++) {
                        have[j] += i * ca[j];
                    }
                    dfs(all, target, cur + 1, have, cnt + i);
                    for (int j = 0; j < 26; j++) {
                        have[j] -= i * ca[j];
                    }
                }
            }

        }
    }

    public int[] alberts(String s) {
        int[] a = new int[26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
        }
        return a;
    }
}
