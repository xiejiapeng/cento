package index.monostack;

/*
给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。

如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。

n == nums.length
1 <= n <= 2 * 105
-109 <= nums[i] <= 109
 */


import java.util.*;

public class L456 {

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++){
            left[i] = Math.min(left[i-1], nums[i-1]);
        }
        int[] right = new int[n];
        right[n-1] = Integer.MIN_VALUE;
        TreeSet<int[]> tree = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0])return Integer.compare(o1[1], o2[1]);
                else return Integer.compare(o1[0], o2[0]);
            }
        });
        tree.add(new int[]{nums[n-1], n-1});
        for (int i = n - 2; i > -1; i--){
            int[] x = tree.floor(new int[]{nums[i], i});
            if(x == null)right[i] = Integer.MIN_VALUE;
            else right[i] = x[0];
            tree.add(new int[]{nums[i], i});
        }

        for (int i = 1; i < n - 1; i++){
            if(left[i] < right[i] && right[i] < nums[i])return true;
        }
        return false;
    }
    public boolean find132pattern2(int[] nums) {
        int n = nums.length;
        LinkedList<Integer> l = new LinkedList<>();
        int[] small = new int[n];
        for (int i = 0; i < n; i++){
            if(l.isEmpty()) {
                small[i] = -1;
            } else {
                small[i] = findSmall(l, nums, 0, l.size()-1, nums[i]);
            }
            if(l.isEmpty() || nums[l.getLast()] > nums[i]) {
                l.addLast(i);
            }
        }

        int[] large = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                large[i] = -1;
            } else {
                large[i] = stack.peek();
            }
            stack.add(i);
        }

        for (int i = 0; i < n; i++){
            if(small[i] != -1 && large[i] != -1 && small[i] < large[i])return true;
        }
        return false;
    }

    //find first i small than nums[l.get(i)] < x
    private int findSmall(List<Integer> l, int[] nums, int left, int right, int x) {
        if(left == right) {
            if(nums[l.get(left)] < x)return l.get(left);
            else return -1;
        } else if(left == right - 1) {
            if(nums[l.get(left)] < x)return l.get(left);
            else if(nums[l.get(right)] < x)return l.get(right);
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(nums[l.get(mid)] < x) {
                return findSmall(l, nums, left, mid, x);
            } else {
                return findSmall(l, nums, mid + 1, right, x);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L456().find132pattern(new int[]{3,1,4,2}));
    }
}
