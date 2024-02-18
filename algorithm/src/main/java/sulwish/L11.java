package sulwish;

public class L11 {
    public static void main(String[] args) {
        int[] h = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new L11().maxArea(h));
    }

    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int max = 0;
        while (left <= right) {
            int cur = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, cur);
            if (height[left] <= height[right]) left++;
            else right--;
        }
        return max;
    }
}
