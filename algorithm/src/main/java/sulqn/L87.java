package sulqn;

public class L87 {
    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length())return false;

        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n+1];
        for(int len = 1; len <= n; len++){
            for(int i = 0; i + len - 1 < n; i++){
                for(int j = 0; j + len - 1 < n; j++){
                    if(len == 1){
                        if(s1.charAt(i) == s2.charAt(j))dp[i][j][len] = true;
                        else dp[i][j][len] = false;
                    } else {
                        if(!same(s1.substring(i,i+len),s2.substring(j,j+len)))dp[i][j][len] = false;
                        else {
                            for(int k = 1; k < len; k++){
                                dp[i][j][len] |= (dp[i][j][k] && dp[i+k][j+k][len-k]);
                                dp[i][j][len] |= (dp[i][j+len-k][k] && dp[i+k][j][len-k]);
                            }
                        }
                    }

                }
            }
        }
        return dp[0][0][n];
    }

    private boolean same(String a, String b){
        int[] as = new int[26];
        int[] bs = new int[26];
        for(int i = 0; i < a.length(); i++){
            as[a.charAt(i)-'a']++;
            bs[b.charAt(i)-'a']++;
        }
        for (int i = 0; i < 26; i++){
            if(as[i] != bs[i])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L87().isScramble("abb","bba"));
    }
}
