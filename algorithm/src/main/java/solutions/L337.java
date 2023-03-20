package solutions;

import java.util.HashMap;
import java.util.Map;

public class L337 {
    Map<TreeNode,int[]> record = new HashMap<>();
    public int rob(TreeNode root) {
        return rob(root, 0);
    }

    private int rob(TreeNode root, int parentRobot){
        if(root == null)return 0;
        record.putIfAbsent(root, new int[]{-1,-1});
        if(record.get(root)[parentRobot] != -1)return record.get(root)[parentRobot];
        if(parentRobot == 1) {
            int x = rob(root.left,0) + rob(root.right,0);
            record.get(root)[parentRobot] = x;
            return x;
        } else {
            //rob root
            int x = root.val + rob(root.left, 1) + rob(root.right, 1);
            int y = rob(root.left, 0) + rob(root.right, 0);
            int z = Math.max(x,y);
            record.get(root)[parentRobot] = z;
            return z;
        }
    }
}
