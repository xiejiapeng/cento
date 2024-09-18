package sulqn;

import java.util.ArrayList;
import java.util.List;

public class L22 {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(n, "", n, n, 0, 0);
        return ans;
    }

    private void dfs(int n, String sb, int leftRemaining, int rightRemaining, int total, int score) {
        if (total == 2 * n) {
            if (score == 0) {
                ans.add(sb);
            }
        } else {
            if (leftRemaining > 0) {
                dfs(n, sb + "(", leftRemaining - 1, rightRemaining, total + 1, score + 1);
            }
            if (rightRemaining > 0 && score > 0) {
                dfs(n, sb + ")", leftRemaining, rightRemaining - 1, total + 1, score - 1);
            }
        }
    }
}
