package solutions;

import java.util.HashSet;
import java.util.Set;

/*
给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n
 */
public class L421 {
    public int findMaximumXOR2(int[] nums) {
        int ans = 0;
        for (int i = 30; i > -1; i--) {
            int can = (ans << 1) + 1;
            Set<Integer> see = new HashSet<>();

            boolean find = false;
            for (int k = 0; k < nums.length; k++) {
                if(see.contains(can ^ (nums[k] >> i))){
                    find = true;
                    break;
                } else {
                    see.add(nums[k] >> i);
                }
            }
            if(find) ans = can;
            else ans = ans * 2;
        }
        return ans;
    }
    public int findMaximumXOR(int[] nums) {
        //x能不能拥有前缀prefix
        int prefix = 0;
        int mask = 1<<30;
        int pm = 1<<30;
        for(int i = 0; i <= 30; i++){
            int tmp = prefix | mask;
            Set<Integer> px = new HashSet<>();
            for(int x : nums){
                px.add(x & pm);
            }
            boolean find = false;
            for(int x : nums){
                if(px.contains((x ^ tmp)&pm))find = true;
            }
            if(find)prefix = tmp;

            mask >>= 1;
            pm = pm | mask;

        }
        return prefix;
    }

    public static void main(String[] args) {
        System.out.println(new L421().findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70}));
    }

    public static String print(int x){
        int mask = 1 << 31;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 32; i++){
            int u = x & mask;
            if(u == 0)sb.append("0");
            else sb.append("1");

            mask >>>= 1;
        }
        return sb.toString();
    }
}
