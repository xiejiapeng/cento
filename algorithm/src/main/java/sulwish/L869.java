package sulwish;

/*
给定正整数 n ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 */

import java.util.HashSet;
import java.util.Set;

public class L869 {
    Set<String> all = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(new L869().reorderedPowerOf2(254432353));
    }

    public boolean reorderedPowerOf2(int n) {
        Set<Integer> ps = new HashSet<>();
        for (int i = 0; Math.pow(2, i) <= 10000000000l; i++) {
            ps.add((int) Math.pow(2, i));
        }

        dfs(String.valueOf(n), "", new HashSet<>());
        for (String s : all) {
            if (ps.contains(Integer.valueOf(s))) return true;
        }
        return false;
    }

    private void dfs(String n, String tmp, Set<Integer> used) {
        if (used.size() == n.length()) {
            all.add(tmp);
        } else {
            Set<Character> see = new HashSet<>();
            for (int i = 0; i < n.length(); i++) {
                if (used.contains(i)) continue;
                else if (see.contains(n.charAt(i))) continue;
                else if (tmp.length() > 0 || n.charAt(i) != '0') {
                    see.add(n.charAt(i));
                    used.add(i);
                    dfs(n, tmp + n.charAt(i), used);
                    used.remove(i);
                }
            }
        }
    }


}
