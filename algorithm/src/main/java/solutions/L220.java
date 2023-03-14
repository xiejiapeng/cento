package solutions;

import java.util.Stack;
import java.util.TreeMap;

public class L220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        Stack<Integer> stack = new Stack<>();

        TreeMap<Integer,Integer> m = new TreeMap<>();
        for(int i = 0; i < nums.length; i++){
            int remove = i - indexDiff - 1;
            if(remove >= 0){
                int c = m.get(nums[remove]);
                if(c == 1)m.remove(nums[remove]);
                else m.put(nums[remove], c-1);
            }
            if(!m.isEmpty()){
                if(m.floorKey(nums[i]) != null){
                    int floor = m.floorKey(nums[i]);
                    if(nums[i] - floor <= valueDiff)return true;
                }
                if(m.ceilingKey(nums[i]) != null){
                    int celling = m.ceilingKey(nums[i]);
                    if(celling - nums[i] <= valueDiff)return true;
                }
            }
            int c = m.getOrDefault(nums[i],0);
            m.put(nums[i],c+1);

        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new L220().containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
    }
}
