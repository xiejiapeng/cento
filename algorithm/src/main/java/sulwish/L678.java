package sulwish;

/*
给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。

有效 字符串符合如下规则：

任何左括号 '(' 必须有相应的右括号 ')'。
任何右括号 ')' 必须有相应的左括号 '(' 。
左括号 '(' 必须在对应的右括号之前 ')'。
'*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
 */

public class L678 {
    public boolean checkValidString(String s) {
        int min = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ')') {
                min = Math.max(min - 1, 0);
                max--;
                if(max < 0)return false;
            } else if(s.charAt(i) == '(') {
                min++;
                max++;
            } else {
                min = Math.max(min - 1, 0);
                max++;
            }
        }
        System.out.println("min="+min+",max="+max);
        return min == 0;
    }

    public static void main(String[] args) {
        System.out.println(new L678().checkValidString("(((((()*)(*)*))())())(()())())))((**)))))(()())()"));
    }
}
