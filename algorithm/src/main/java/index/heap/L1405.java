package index.heap;

/*
如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。

给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：

s 是一个尽可能长的快乐字符串。
s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
s 中只含有 'a'、'b' 、'c' 三种字母。
如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 */

public class L1405 {
    public String longestDiverseString(int a, int b, int c) {
        String s = "";
        while (a > 0 || b > 0 || c > 0) {
            if (a >= b && a >= c) {
                if (s.endsWith("aa")) {
                    if (b >= c) {
                        if (b > 0) {
                            s = s + "b";
                            b--;
                        } else {
                            break;
                        }
                    } else {
                        if (c > 0) {
                            s = s + "c";
                            c--;
                        } else {
                            break;
                        }
                    }
                } else {
                    s += "a";
                    a--;
                }
            } else if (b >= a && b >= c) {
                if (s.endsWith("bb")) {
                    if (a >= c) {
                        if (a > 0) {
                            s = s + "a";
                            a--;
                        } else {
                            break;
                        }
                    } else {
                        if (c > 0) {
                            s = s + "c";
                            c--;
                        } else {
                            break;
                        }
                    }
                } else {
                    s += "b";
                    b--;
                }
            } else if (c >= a && c >= b) {
                if (s.endsWith("cc")) {
                    if (a >= b) {
                        if (a > 0) {
                            s = s + "a";
                            a--;
                        } else {
                            break;
                        }
                    } else {
                        if (b > 0) {
                            s = s + "b";
                            b--;
                        } else {
                            break;
                        }
                    }
                } else {
                    s += "c";
                    c--;
                }
            }
        }
        return s;
    }
}
