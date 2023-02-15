package solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
s,t
ADOBECODEBANC
ABC
 */

public class L76 {
    public String minWindow(String s, String t) {
        Map<Character,Integer> tc = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            tc.put(t.charAt(i), tc.getOrDefault(t.charAt(i),0)+1);
        }
        Map<Character,Integer> cur = new HashMap<>();
        int min = Integer.MAX_VALUE;
        String ans = "";
        for(int left = 0, right = 0; left < s.length(); left++){
            while(!satisfy(cur,tc) && right < s.length()){
                cur.put(s.charAt(right),cur.getOrDefault(s.charAt(right),0) + 1);
                right++;
            }
            if(!satisfy(cur,tc))break;
            if(right - left + 1 <= min){
                min = right - left;
                ans = s.substring(left,right);
            }
            min = Math.min(min, right - left + 1);
            if(cur.get(s.charAt(left)) == 0)cur.remove(s.charAt(left));
            else cur.put(s.charAt(left),cur.get(s.charAt(left))-1);
        }
        return ans;
    }

    private boolean satisfy(Map<Character,Integer> a, Map<Character,Integer> b){
        for(char c : b.keySet()){
            if(!a.containsKey(c) || a.get(c) < b.get(c))return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L76().minWindow("ADOBECODEBANC", "ABC"));
    }
}
