package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L95 {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0)return new ArrayList();
        if(n == 1){
            TreeNode t = new TreeNode(1);
            return Arrays.asList(t);
        } else {
            int[] dict = new int[n];
            for(int i = 0; i < n; i++)dict[i] = i + 1;
            List<TreeNode> ans = new ArrayList();
            for(int i = 0; i < n; i++){
                int left = i;
                int right = n - i - 1;
                TreeNode root = new TreeNode(1);
                List<TreeNode> lt = generateTrees(left);
                List<TreeNode> rt = generateTrees(right);
                if(lt.size() == 0 && rt.size() == 0){
                    ans.add(rename(root,dict,i,i));
                } else if(lt.size() == 0){
                    for(TreeNode rtt : rt){
                        TreeNode r = rename(rtt, dict, i + 1, n - 1);
                        TreeNode rootr = rename(root,dict, i, i);
                        rootr.right = r;
                        ans.add(rootr);
                    }
                } else if(rt.size() == 0) {
                    for(TreeNode ltt : lt){
                        TreeNode l = rename(ltt, dict, 0, i - 1);
                        TreeNode rootr = rename(root,dict, i, i);
                        rootr.left = l;
                        ans.add(rootr);
                    }
                } else {
                    for(TreeNode rtt : rt){
                        for(TreeNode ltt : lt){
                            TreeNode l = rename(ltt, dict, 0, i - 1);
                            TreeNode r = rename(rtt, dict, i + 1, n - 1);
                            TreeNode rootr = rename(root,dict, i, i);
                            rootr.left = l;
                            rootr.right = r;
                            ans.add(rootr);
                        }
                    }
                }
            }
            return ans;
        }
    }

    public TreeNode rename(TreeNode root, int[] dict, int left, int right) {
        System.out.println(root.val + ", dict=" + Arrays.toString(dict) + ",left="+left+",right="+right);
        int idx = root.val;
        int val = dict[left + idx - 1];
        TreeNode nr = new TreeNode(val);
        if(root.left != null)nr.left = rename(root.left, dict, left, right);
        if(root.right != null)nr.right = rename(root.right,dict,left,right);
        return nr;
    }

    public static void main(String[] args) {
        System.out.println(new L95().generateTrees(2));
    }
}
