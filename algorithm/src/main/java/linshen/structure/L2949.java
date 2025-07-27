package linshen.structure;

/*
给你一个字符串 s 和一个正整数 k 。

用 vowels 和 consonants 分别表示字符串中元音字母和辅音字母的数量。

如果某个字符串满足以下条件，则称其为 美丽字符串 ：

vowels == consonants，即元音字母和辅音字母的数量相等。
(vowels * consonants) % k == 0，即元音字母和辅音字母的数量的乘积能被 k 整除。
返回字符串 s 中 非空美丽子字符串 的数量。
提示：

1 <= s.length <= 5 * 104
1 <= k <= 1000
s 仅由小写英文字母组成。

子字符串是字符串中的一个连续字符序列。

英语中的 元音字母 为 'a'、'e'、'i'、'o' 和 'u' 。

英语中的 辅音字母 为除了元音字母之外的所有字母。

1,-1 <=> s=0,len^2/4%k==0
1,0 <=> s=len/2,s^2%k==0

 */

public class L2949 {
    public long beautifulSubstrings(String s, int k) {
        return 0L;
    }
}
