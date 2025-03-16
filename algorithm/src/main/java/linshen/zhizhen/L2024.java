package linshen.zhizhen;

/*
一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）
或者 false （用 'F' 表示）。老师想增加学生对自己做出答案的不确定性，
方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。

给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。
除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：

每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。

todo 不超过...的情况下，最长、最大题型考虑使用不定长滑动窗口

 */

public class L2024 {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        //全变成t和f的情况
        int maxT = 0;
        int maxF = 0;
        int cost = 0;
        for(int left = 0, right = 0; right < n; right++){
            if(answerKey.charAt(right) == 'F') {
                cost += 1;
                while (cost > k) {
                    if(answerKey.charAt(left) == 'F') cost--;
                    left++;
                }
            }
            maxT = Math.max(maxT, right - left + 1);
        }

        cost = 0;
        for(int left = 0, right = 0; right < n; right++){
            if(answerKey.charAt(right) == 'T') {
                cost += 1;
                while (cost > k) {
                    if(answerKey.charAt(left) == 'T') cost--;
                    left++;
                }
            }
            maxF = Math.max(maxF, right - left + 1);
        }
        return Math.max(maxT, maxF);
    }
}
