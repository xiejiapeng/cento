package linshen.slide;

/*
有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。

假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。

给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。

你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。

请返回待替换子串的最小可能长度。


如果原字符串自身就是一个平衡字符串，则返回 0。
 */

public class L1234 {
    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;
        int min = Integer.MAX_VALUE;
        int[] all = new int[4];
        for (int i = 0; i < n; i++){
            all[index(s, i)]++;
        }
        for (int left = 0, right = 0; right < n; right++){
            all[index(s, right)]--;
            if(!meet(all, target))continue;
            while (left <= right && meet(mute(all, s, left), target)) {
                all = mute(all, s, left);
                left++;
            }
            min = Math.min(min, right - left + 1);
        }
        return min;
    }

    private int[] mute(int[] a, String s, int i) {
        int[] tmp = a.clone();
        tmp[index(s, i)]++;
        return tmp;
    }

    int index(String s, int j) {
        char c = s.charAt(j);
        if(c == 'Q')return 0;
        if(c == 'W')return 1;
        if(c == 'E')return 2;
        return 3;
    }

    boolean meet(int[] all, int target) {
        for (int i = 0; i < 4; i++){
            if(all[i] > target)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L1234().balancedString("QQQW"));
    }
}
