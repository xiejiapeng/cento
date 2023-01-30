package solutions;

public class L32 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        //从i开始，提前给定j的量
        //dp[i][j] = dp[i+1][j+1]
        int[] dp = new int[n+1];
        int max = 0;

        for(int i = n - 1; i > -1; i--){
            int[] tmp = new int[n+1];
            for(int j = 0; j < n; j++){
                if(i == n - 1){
                    if(s.charAt(i) == '(')tmp[j] = 0;
                    else if(j == 1) tmp[j] = 1;
                    else tmp[j] = 0;
                } else {
                    if(s.charAt(i) == '(')tmp[j] = dp[j+1] > 0 ? 1 + dp[j+1] : 0;
                    else if(j == 0) tmp[j] = 0;
                    else{
                        if(j == 1)tmp[j] = 1;
                        if(dp[j-1] > 0)tmp[j] = Math.max(tmp[j],1 + dp[j-1]);
                    }

                }
            }
            max = Math.max(max, tmp[0]);
            dp = tmp;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L32().longestValidParentheses("()(()"));
    }
}
