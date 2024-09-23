package index.dfs;

/*
我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。

您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。

返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。

注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
 */

public class L691 {
    int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new L691().minStickers(new String[]{"with", "example", "science"}, "thehat"));
    }

    public int minStickers(String[] stickers, String target) {
        dfs(stickers, sign(target), 0, new int[26], 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(String[] stickers, int[] target, int i, int[] have, int cnt) {
        if (cnt > ans) return;
        if (i == stickers.length) {
            if (can(target, have)) {
                ans = Math.min(ans, cnt);
            }
        } else {
            int[] sign = sign(stickers[i]);
            //choose
            int max = maxChoose(target, sign);
            for (int j = 1; j <= max; j++) {
                dfs(stickers, target, i + 1, add(sign, have, j), cnt + j);
            }

            //skip
            dfs(stickers, target, i + 1, have, cnt);
        }
    }

    private boolean can(int[] target, int[] have) {
        for (int i = 0; i < 26; i++) {
            if (have[i] < target[i]) return false;
        }
        return true;
    }

    private int[] sign(String s) {
        int[] x = new int[26];
        for (char c : s.toCharArray()) {
            x[c - 'a']++;
        }
        return x;
    }

    private int[] add(int[] a, int[] b, int cnt) {
        int[] x = new int[26];
        for (int i = 0; i < 26; i++) {
            x[i] = a[i] * cnt + b[i];
        }
        return x;
    }

    private int maxChoose(int[] a, int[] b) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 26; i++) {
            if (b[i] != 0) {
                max = Math.max(max, a[i] % b[i] == 0 ? a[i] / b[i] : (a[i] / b[i] + 1));
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
