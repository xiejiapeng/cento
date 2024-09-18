package sulwish;

/*
如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，
那么该字符串就是一个「快乐字符串」。

给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：

s 是一个尽可能长的快乐字符串。
s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
s 中只含有 'a'、'b' 、'c' 三种字母。
如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 */
public class L1405 {
    public String longestDiverseString(int a, int b, int c) {
        String l = "";
        String ll = "";
        StringBuilder sb = new StringBuilder();
        while (a > 0 || b > 0 || c > 0) {
            if(l.equals("a") && ll.equals("a")) {
                if(b >= c && b > 0) {
                    sb.append("b");
                    b--;
                    l = ll;
                    ll = "b";
                } else if(c >= b && c > 0) {
                    sb.append("c");
                    c--;
                    l = ll;
                    ll = "c";
                } else {
                    return "";
                }
            } else if(l.equals("b") && ll.equals("c")) {
                if(a >= c && a > 0) {
                    sb.append("a");
                    a--;
                    l = ll;
                    ll = "a";
                } else {
                    sb.append("c");
                    c--;
                    l = ll;
                    ll = "c";
                }
            } else if(l.equals("c") && ll.equals("c")) {
                if(a >= b) {
                    sb.append("a");
                    a--;
                    l = ll;
                    ll = "a";
                } else {
                    sb.append("b");
                    b--;
                    l = ll;
                    ll = "b";
                }
            } else {
                if(a >= b && a >= c && a > 0){
                    sb.append("a");
                    a--;
                    l = ll;
                    ll = "a";
                } else if(b >= a && b >= c && b > 0) {
                    sb.append("b");
                    b--;
                    l = ll;
                    ll = "b";
                } else if(c >= a && c >= b && c > 0) {
                    sb.append("c");
                    c--;
                    l = ll;
                    ll = "c";
                } else {
                    return "";
                }
            }
        }
    }
}
