package sulqn;

import java.util.*;

public class L40 {
    Set<List<Integer>> ans = new HashSet<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Map<Integer,Integer> count = new HashMap();
        Set<Integer> used = new HashSet<>();
        for(int x : candidates){
            int c = count.getOrDefault(x, 0);
            count.put(x, c + 1);
        }
        int t = count.size();
        int[] all = new int[t];
        int[] ca = new int[t];
        int i = 0;
        for(int x : count.keySet()){
            all[i] = x;
            ca[i] = count.get(x);
            i++;
        }
        dfs(all,ca,0,target,0,new LinkedList());
        return new ArrayList(ans);
    }

    private void dfs(int[] all, int[] ca, int idx, int target, int sum, LinkedList<Integer> cur) {
        if(idx == all.length){
            if(sum == target) {
                ans.add(new ArrayList(cur));
            }
        } else {
            dfs(all,ca,idx+1,target,sum,cur);

            int c = Math.min(ca[idx], (target - sum) / all[idx]);
            for(int i = 1; i <= c; i++){
                cur.addLast(all[idx]);
                dfs(all,ca,idx+1,target,sum+i*all[idx],cur);
            }
            for(int i =1; i <= c; i++){
                cur.removeLast();
            }
        }
    }
    //[10,1,2,7,6,1,5]
    //8

    public static void main(String[] args) {
        System.out.println(new L40().combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }
}
