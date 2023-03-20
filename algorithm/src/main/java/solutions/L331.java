package solutions;

import java.util.Stack;

public class L331 {
    public boolean isValidSerialization(String preorder) {
        String[] s = preorder.split(",");
        Stack<Integer> stack = new Stack();
        for(int i = 0; i < s.length; i++){
            String x = s[i];
            if(x.equals("#")){
                if(stack.isEmpty())return i == s.length - 1;
                else {
                    while(!stack.isEmpty()) {
                        int peek = stack.pop();
                        if(peek == 2){
                            stack.add(1);
                            break;
                        } else{
                            continue;
                        }
                    }
                    if(stack.isEmpty())return i == s.length - 1;
                }
            } else {
                stack.add(2);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new L331().isValidSerialization("9,3,4,#,#,1,#,#,#,2,#,6,#,#"));
    }
}
