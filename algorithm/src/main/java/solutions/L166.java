package solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class L166 {
    public static void main(String[] args) {
        System.out.println(new L166().fractionToDecimal(4, 333));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        String sign = sign(numerator, denominator);
        long a = Math.abs(numerator);
        long b = Math.abs(denominator);
        long x = a / b;
        String y = fractionToDecimal(a % b, b);
        return sign + x + "." + y;
    }

    private String sign(int a, int b) {
        if (a == 0) return "";
        else {
            if ((a > 0 && b > 0) || (a < 0 && b < 0)) return "";
            else return "-";
        }
    }

    public String fractionToDecimal(long a, long b) {
        StringBuilder ret = new StringBuilder();
        Map<Long, Integer> pos = new HashMap();
        int i = 0;
        int start = -1;
        while (a != 0) {
            if (pos.containsKey(a)) {
                start = pos.get(a);
                break;
            }

            long c = a * 10;
            long d = c / b;
            pos.put(a, i);
            a = c % b;
            ret.append(d);
        }

        String r = ret.toString();
        StringBuilder ans = new StringBuilder();
        for (int j = 0; j < r.length(); j++) {
            if (j != start) {
                ans.append(r.charAt(i));
            } else {
                ans.append("(");
                ans.append(r.charAt(i));
            }
        }
        if (start != -1) ans.append(")");
        return ans.toString();
    }

    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ns = new String[n];
        for (int i = 0; i < nums.length; i++) {
            ns[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(ns, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(0) != o2.charAt(0)) {
                    return -1 * Integer.compare(o1.charAt(0) - '0', o2.charAt(0) - '0');
                } else {
                    String a = o1 + o2;
                    String b = o2 + o1;
                    return -1 * a.compareTo(b);
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        boolean prefix = false;
        for (int i = 0; i < ns.length; i++) {
            String x = ns[i];
            if (!x.equals("0")) {
                sb.append(x);
                prefix = true;
            } else {
                if (prefix) sb.append(x);
                else {
                    if (i == ns.length - 1) {
                        sb.append(x);
                        prefix = true;
                    } else continue;
                }
            }
        }
        return sb.toString();


    }
}
