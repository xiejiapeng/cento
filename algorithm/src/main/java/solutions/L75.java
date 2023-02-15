package solutions;

import java.util.Arrays;

public class L75 {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int nextr = 0;
        int nextb = n - 1;
        int i = 0;
        while(i < n && i <= nextb){
            if(nums[i] == 0){
                int tmp = nums[nextr];
                nums[nextr] = 0;
                nums[i] = tmp;
                nextr++;
            } else if(nums[i] == 2){
                int tmp = nums[nextb];
                nums[nextb] = 2;
                nums[i] = tmp;
                nextb--;

            }
            i++;
        }

        for(int k = nextr; k <= nextb; k++)nums[k] = 1;
    }

    public static void main(String[] args) {
        int[] a = {2,0,1,1};
        new L75().sortColors(a);
        System.out.println(Arrays.toString(a));
    }
}
