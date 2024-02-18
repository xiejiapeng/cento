package sulqn;

import java.util.LinkedList;

public class L45 {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = 0;

        LinkedList<Integer> l = new LinkedList();
        l.addFirst(n-1);
        for(int i = n - 2; i > -1; i--){
            dp[i] = 10001;
            for(int t = 0; t < l.size(); t++){
                int j = l.get(t);
                if(j-i > nums[i])break;
                else dp[i] = Math.min(dp[i],1+dp[j]);
            }
            while(!l.isEmpty() && dp[l.peek()]>=dp[i])l.pop();
            l.addFirst(i);
        }

        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new L45().jump(new int[]{2,3,1,1,4}));
    }
}
