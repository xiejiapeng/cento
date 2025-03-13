package linshen.zhizhen;

/*
给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。

你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。

todo 滑动窗口的特征：连续，对区间的限制存在单调性，求最（大）值
 */
public class L2516 {
    public int takeCharacters(String s, int k) {
        int n = s.length();
        int[] left = new int[3];
        for (int i = 0; i < n; i++){
            left[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 3; i++){
            if(left[i] < k)return -1;
            left[i] -= k;
        }
        int[] cnt = new int[3];
        int max = 0;
        for(int l = 0, r = 0; r < n; r++){
            int id = s.charAt(r) - 'a';
            cnt[id]++;
            while (cnt[id] > left[id]) {
                int lid = s.charAt(l) - 'a';
                cnt[lid]--;
                l++;
            }
            max = Math.max(max, r - l + 1);
        }
        return n - max;
    }
}
