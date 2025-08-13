package linshen.structure.queue;

/*
给定一个整数数组 nums 和一个整数 k。

子数组 被称为 质数间隔平衡，如果：

其包含 至少两个质数，并且
该 子数组 中 最大 和 最小 质数的差小于或等于 k。
返回 nums 中质数间隔平衡子数组的数量。

提示：

1 <= nums.length <= 5 * 104
1 <= nums[i] <= 5 * 104
0 <= k <= 5 * 104
 */

import java.util.LinkedList;

public class L3589 {
    //todo hhhhhh 这题能过挺意外的；里面涉及到太多知识点：双指针（其实是三指针），左右边界，（带举例限制的）子数组个数统计，（双）单调栈，素数判定
    public int primeSubarray(int[] nums, int k) {
        int n = nums.length;
        boolean[] p = new boolean[n];
        for (int i = 0; i < n; i++){
            p[i] = prime(nums[i]);
        }
        LinkedList<Integer> large = new LinkedList<>();
        LinkedList<Integer> small = new LinkedList<>();
        int cnt = 0;
        int ans = 0;
        for (int left = 0, right = 0, two = 0; right < n; right++) {
            if(p[right]){
                cnt++;
                while (!large.isEmpty() && nums[large.getLast()] <= nums[right]) {
                    large.pollLast();
                }
                large.addLast(right);
                while (!small.isEmpty() && nums[small.getLast()] >= nums[right]) {
                    small.pollLast();
                }
                small.addLast(right);
                while (nums[large.getFirst()] - nums[small.getFirst()] > k) {
                    if(large.getFirst() == left)large.removeFirst();
                    if(small.getFirst() == left)small.removeFirst();
                    left++;
                }
                while (cnt >= 2) {
                    if(p[two])cnt--;
                    two++;
                }
                ans += (two - left);
            } else {
                ans += (two - left);
            }
        }
        return ans;
    }

    private boolean prime(int x) {
        if(x == 1)return false;
        for (int i = 2; i <= (int)(Math.sqrt(x)); i++){
            if(x % i == 0)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L3589().primeSubarray(new int[]{2,3,5,7}, 3));
    }
}
