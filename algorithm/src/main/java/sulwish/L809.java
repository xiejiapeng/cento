package sulwish;

/*
有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。

对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母 c ），然后往其中添加相同的字母 c 使其长度达到 3 或以上。

例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于 3。此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得 "helllllooo"。如果 s = "helllllooo"，那么查询词 "hello" 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" -> "helllllooo" = s。

输入一组查询单词，输出其中可扩张的单词数量。
 */

import java.util.ArrayList;
import java.util.List;

public class L809 {
    public int expressiveWords(String s, String[] words) {
        List<int[]> gs = group(s);
        int cnt = 0;
        for (String w : words){
            List<int[]> wg = group(w);
            if(match(gs, wg))cnt++;
        }
        return cnt;
    }

    public List<int[]> group(String s){
        List<int[]> ans = new ArrayList<>();
        int last = s.charAt(0);
        int cnt = 1;
        for (int i = 1; i < s.length(); i++){
            if(s.charAt(i) != last){
                ans.add(new int[]{last, cnt});
                last = s.charAt(i);
                cnt = 1;
            } else {
                cnt++;
            }
        }
        ans.add(new int[]{last, cnt});
        return ans;
    }

    public boolean match(List<int[]> a, List<int[]> b) {
        if(a.size() != b.size())return false;
        else {
            for (int i = 0; i < a.size(); i++){
                int[] ag = a.get(i);
                int[] bg = b.get(i);
                if(ag[0] != bg[0])return false;
                else {
                    if(ag[1] != bg[1] && (ag[1] < 3 || ag[1] < bg[1]))return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new L809().expressiveWords("heeellooo",new String[]{"hello"}));
    }
}
