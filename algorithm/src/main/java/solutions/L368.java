package solutions;

import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer>[] ans = new List[n];
        for(int i = 0; i < n; i++){
            ans[i] = new ArrayList<>();
        }

        int max = 0;
        List<Integer> aa = new ArrayList<>();

        for (int i = 0; i < n; i++){
            if(i == 0){
                ans[i].add(nums[0]);
            } else {
                ans[i].add(nums[i]);
                for(int j = i - 1; j > -1; j--){
                    if(nums[i] % nums[j] == 0){
                        if(ans[j].size() + 1 > ans[i].size()){
                            ans[i] = new ArrayList<>(ans[j]);
                            ans[i].add(nums[i]);
                        }
                    }
                }
            }
            if(ans[i].size() > max){
                max = ans[i].size();
                aa = ans[i];
            }
        }
        return aa;
    }

    public static void main(String[] args) {
        System.out.println(new L368().largestDivisibleSubset(new int[]{3,4,16,8}));
    }
}
