package index.hashtable;

/*
给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。

如果小数部分为循环小数，则将循环的部分括在括号内。

如果存在多个答案，只需返回 任意一个 。

对于所有给定的输入，保证 答案字符串的长度小于 104 。
4 333
0 0 1 2 0 1 2
 */

import java.util.HashMap;
import java.util.Map;

public class L166 {
    public static void main(String[] args) {
        System.out.println(new L166().fractionToDecimal(4, 3330));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        return cal((long) numerator, (long) denominator);
    }

    public String cal(long numerator, long denominator) {
        if (numerator == 0) return "0";
        if (numerator > 0 && denominator < 0) return "-" + cal(numerator, -denominator);
        if (numerator < 0 && denominator > 0) return "-" + cal(-numerator, denominator);

        StringBuilder sb = new StringBuilder("");
        String prefix = "";
        if (numerator < denominator) {
            prefix = "0.";
            numerator *= 10;
        }

        Map<Long, Integer> see = new HashMap<>();
        int index = -1;

        while (numerator != 0) {
            if (!see.containsKey(numerator)) {
                see.put(numerator, sb.length());
                if (numerator < denominator) {
                    numerator *= 10;
                    sb.append("0");
                } else {
                    sb.append(numerator / denominator);
                    numerator = (numerator % denominator) * 10;
                }
            } else {
                index = see.get(numerator);
                System.out.println(index);
                break;
            }

        }
        if (index == -1) {
            return prefix + sb.toString();
        } else {
            for (int i = 0; i < sb.length(); i++) {
                if (i != index) {
                    prefix = prefix + sb.charAt(i);
                } else {
                    prefix = prefix + "(";
                    prefix = prefix + sb.charAt(i);
                }
            }
            prefix = prefix + ")";
            return prefix;
        }
    }
}
