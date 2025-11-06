package linshen.greedy;
/*
给你两个字符串 word1 和 word2 。你需要按下述方式构造一个新字符串 merge ：
如果 word1 或 word2 非空，选择 下面选项之一 继续操作：

如果 word1 非空，将 word1 中的第一个字符附加到 merge 的末尾，并将其从 word1 中移除。
如果 word2 非空，将 word2 中的第一个字符附加到 merge 的末尾，并将其从 word2 中移除。
返回你可以构造的字典序 最大 的合并字符串 merge 。

长度相同的两个字符串 a 和 b 比较字典序大小，如果在 a 和 b 出现不同的第一个位置，a
中字符在字母表中的出现顺序位于 b 中相应字符之后，就认为字符串 a 按字典序比字符串 b 更大。
例如，"abcd" 按字典序比 "abcc" 更大，
因为两个字符串出现不同的第一个位置是第四个字符，而 d 在字母表中的出现顺序位于 c 之后。
提示：
1 <= word1.length, word2.length <= 3000
word1 和 word2 仅由小写英文组成
 */
public class L1754 {
    //todo hh
    public String largestMerge(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        while (!word1.isEmpty() || !word2.isEmpty()) {
            if(word1.isEmpty()) {
                sb.append(word2);
                word2 = "";
            } else if(word2.isEmpty()) {
                sb.append(word1);
                word1 = "";
            } else {
                if(word1.compareTo(word2) <= 0) {
                    char c = word2.charAt(0);
                    sb.append(c);
                    word2 = word2.substring(1);
                } else {
                    char c = word1.charAt(0);
                    sb.append(c);
                    word1 = word1.substring(1);
                }
            }
        }
        return sb.toString();
    }
}
