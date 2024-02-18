package sulwish;

import java.util.Arrays;

public class L75 {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 2, 1};
        new L75().sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sortColors(int[] nums) {
        int len = nums.length;
        int red = 0;
        int white = 0;
        for (int i = 0; i < len; i++) {
            int blue = i - red - white;
            int cur = nums[i];
            if (cur == 0) {
                if (white == 0 || blue == 0) {
                    int x = nums[red];
                    nums[red] = 0;
                    nums[i] = x;
                } else {
                    nums[red] = 0;
                    nums[red + white] = 1;
                    nums[i] = 2;
                }
                red++;
            } else if (cur == 1) {
                int tmp = nums[red + white];
                nums[red + white] = 1;
                nums[i] = tmp;
                white++;
            } else if (cur == 2) {
                blue++;
            }
        }
    }
}
