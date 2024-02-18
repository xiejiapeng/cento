package sulwish;

public class L45 {
    public static void main(String[] args) {
        System.out.println(new L45().jump(new int[]{2, 3, 1, 1, 4}));
    }

    public int jump(int[] nums) {
        int len = nums.length;
        if (len == 1) return 0;
        int left = 0;
        int right = 0;
        int max = nums[left];
        int step = 0;
        while (true) {
            for (int i = left; i <= right; i++) {
                max = Math.max(max, i + nums[i]);
            }
            if (max >= len - 1) return step + 1;
            else {
                left = right + 1;
                right = max;
                step++;
            }
        }
    }
}
