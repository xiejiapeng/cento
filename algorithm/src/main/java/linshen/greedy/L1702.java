package linshen.greedy;

/*
给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：

操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
你返回执行上述操作任意次以后能得到的 最大二进制字符串 。如果二进制字符串 x 对应的十进制
数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
010
-> 001 -> 101

1010 1001 1101
1010

11011011101
11101111101
11110111111


11011011100

000110
100110
110110
111011

    001001
    101001
    110101
    111011

1x -> 1y
<= x -> y
 */
public class L1702 {
    //todo hhh 把这种变化看做一种规律变化，不要仅仅盯着数值，要发现变化背后的图形意义
    //最终结果只会有一个0，变换是把第一个0往后“拉”
    public String maximumBinaryString(String binary) {
        int cnt = 0;
        int first = -1;
        char[] cs = binary.toCharArray();
        for (int i = 0; i < cs.length; i++){
            if(cs[i] == '0'){
                if (first == -1)first = i;
                cnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length; i++) {
            if(first != -1 && i == first + cnt - 1)sb.append('0');
            else sb.append('1');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new L1702().maximumBinaryString("000110"));
    }
}
