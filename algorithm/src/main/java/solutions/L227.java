package solutions;

import java.util.Stack;

public class L227 {
    public int calculate(String s) {
        s = s + "+";
        Stack<Integer> stack = new Stack();
        Stack<Character> op = new Stack();
        int cur = 0;
        char last = '+';
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' ')continue;
            else if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                cur = cur * 10 + (s.charAt(i) - '0');
            } else {
                if(last == '*' || last == '/') {
                    int x = stack.pop();
                    if(last == '*'){
                        stack.add(x * cur);
                    } else {
                        stack.add(x / cur);
                    }
                } else {
                    if(last == '-') {
                        stack.add(-1 * cur);
                    } else {
                        stack.add(cur);
                    }
                }

                cur = 0;
                last = s.charAt(i);
            }
        }

        int sum = 0;
        while(!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new L227().calculate("3+2*2"));
    }
}
