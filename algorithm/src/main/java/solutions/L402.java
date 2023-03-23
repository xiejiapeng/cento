package solutions;

import java.util.Stack;

public class L402 {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < num.length(); i++){
            while (!stack.isEmpty() && stack.peek() > num.charAt(i) && (stack.size() - 1 + num.length() - i) >= num.length() - k) {
                stack.pop();
            }
            stack.add(num.charAt(i));
        }

        while (stack.size() > num.length() - k) stack.pop();
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String ans = sb.reverse().toString();
        for (int i = 0; i < ans.length(); i++){
            if(ans.charAt(i) != '0')return ans.substring(i);
        }
        return "0";
    }
}
