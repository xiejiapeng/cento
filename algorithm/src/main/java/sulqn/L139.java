package sulqn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s,0,"",new HashSet(wordDict));
    }

    private boolean dfs(String s, int idx, String cur, Set<String> wordDict) {
        if(idx == s.length()){
            if(cur.equalsIgnoreCase("bc")) {
                System.out.println("hh");
            }
            if(cur.length() == 0 || wordDict.contains(cur))return true;
            else return false;
        } else {
            boolean a = dfs(s,idx+1,cur+s.charAt(idx),wordDict);
            if(a)return true;
            else {
                if(wordDict.contains(cur)) {
                    boolean b = dfs(s,idx+1,s.charAt(idx)+"",wordDict);
                    if(b)return true;
                }

            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new L139().wordBreak("abc", Arrays.asList("a","bc")));

    }
}
