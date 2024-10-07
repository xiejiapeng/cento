package index.hashtable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> all = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n];
        for (int i = n - 1; i > -1; i--) {
            if (i == n - 1) {
                String sig = s.substring(i);
                if (all.contains(sig)) dp[i] = true;
            } else {
                if (all.contains(s.substring(i))) dp[i] = true;
                else {
                    for (int j = i + 1; j < n; j++) {
                        String x = s.substring(i, j);
                        if (all.contains(x) && dp[j]) dp[i] = true;
                    }
                }

            }
        }
//        System.out.println(Arrays.toString(dp));
        return dp[0];
    }
}
