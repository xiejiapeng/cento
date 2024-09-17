package sulwish;

/*
给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。

注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
57882
58278

6872
7268
 */
public class L556 {
    public int nextGreaterElement(int n) {
        String s = String.valueOf(n);
        int i = s.length() - 1;
        while (true) {
            if(i == 0)return -1;
            else {
                if(s.charAt(i-1) >=s.charAt(i))i--;
                else break;
            }
        }
        int j = i - 1;
        String prefix = j == 0 ? "" : s.substring(0,j);
        for(int k = s.length() - 1; k >= j; k--){
            if(s.charAt(k) <= s.charAt(j))continue;
            else {
                prefix += s.charAt(k);
                for (int l = s.length() - 1; l > j; l--){
                    if(l != k){
                        prefix += s.charAt(l);
                    } else {
                        prefix += s.charAt(j);
                    }
                }
                long x = Long.parseLong(prefix);
                if(x > Integer.MAX_VALUE)return -1;
                else return (int)x;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        System.out.println(new L556().nextGreaterElement(2147483486));
    }
}
