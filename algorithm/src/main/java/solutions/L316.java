package solutions;

import java.util.Stack;

public class L316 {
    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i)-'a']++;
        }
        Stack<Character> stack = new Stack();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(stack.contains(c)){
                count[c-'a']--;
                continue;
            }
            while(!stack.isEmpty() && stack.peek() >= c && count[stack.peek()-'a']>0) {
                stack.pop();
            }
            stack.add(c);
            count[c-'a']--;
        }
        String ans = "";
        while(!stack.isEmpty()){
            char c = stack.pop();
            ans = c + ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L316().removeDuplicateLetters("edebbed"));
    }
}
