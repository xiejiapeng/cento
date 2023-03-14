package solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class L321 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = new int[k];
        for(int left = 0; left <= nums1.length; left++){
            int right = k - left;
            if(right < 0 || right > nums2.length)continue;
            else {
                int[] x = choose(nums1, left);
                int[] y = choose(nums2, right);
                int[] ans = merge(x,y);
                if(larger(ans,max))max = ans;
            }
        }
        return max;
    }

    public boolean larger(int[] a, int[] b){
        for(int i = 0; i < a.length; i++){
            if(a[i] == b[i])continue;
            else if(a[i] > b[i])return true;
            else return false;
        }
        return true;
    }

    public int[] merge(int[] a, int[] b){
        if(a == null)return b;
        else if(b == null)return a;
        else {
            int t = a.length + b.length;
            int[] ans = new int[t];
            int i = 0;
            int j = 0;
            int s = 0;
            while (i < a.length || j < b.length) {
                if(i == a.length)ans[s++] = b[j++];
                else if(j == b.length)ans[s++] = a[i++];
                else {
                    if(a[i] > b[j])ans[s++] = a[i++];
                    else if(a[i] < b[j]) ans[s++] = b[j++];
                    else {
                        int ii = i;
                        int jj = j;
                        while (a[ii] == b[jj]){
                            ans[s++] = a[ii++];
                            jj++;
                        }
                        if(a[ii] > b[jj]){
                            ans[s++] = a[ii++];
                            i = ii;
                        } else {
                            ans[s++] = b[jj++];
                            j = jj;
                        }
                    }
                }
            }
            return ans;
        }
    }

    public int[] choose(int[] nums, int k){
        if(k == 0)return null;
        Stack<Integer> stack = new Stack<>();
        int remain = nums.length;
        for(int i = 0; i < nums.length; i++){
            while (!stack.isEmpty() && stack.peek() < nums[i] && stack.size() + remain > k){
                stack.pop();
            }
            stack.add(nums[i]);
            remain--;
        }
        while (stack.size() > k){
            stack.pop();
        }
        int t = stack.size();
        int[] ans = new int[t];
        while (!stack.isEmpty()) {
            ans[--t] = stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> min = new PriorityQueue<>((o1, o2) -> o1 - o2);
        System.out.println(Arrays.toString(new L321().maxNumber(new int[]{2,1}, new int[]{2,6}, 4)));
    }
}
