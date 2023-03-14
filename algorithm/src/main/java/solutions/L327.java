package solutions;

/*
给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。

区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/count-of-range-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L327 {
    int ans = 0;
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        //s[i,...,j] = sum[j+1] - sum[i]
        //找出sum中i,j,满足sum[j] - sum[i]在区间中
        mergeSort(sum, 0, n, lower,upper);
        return ans;
    }

    int[] mergeSort(int[] s, int left, int right, int low, int high) {
        if(left == right)return s;
        int mid = (left + right) / 2;
        int[] l = mergeSort(s, left, mid, low, high);
        int[] r = mergeSort(s, mid + 1, right, low, high);
        return merge(l,r, low, high);
    }

    int[] merge(int[] left, int[] right, int low, int high) {
        int[] a = new int[left.length + right.length];
        int next = 0;
        for(int l = 0, r1 = 0, r2 = 0; l < left.length; l++){
            while(right[r1] <left[l] + low)r1++;
            while (!(right[r2] > left[l] + high))r2++;
            ans += (r2 - r1);
        }
        int l = 0;
        int r = 0;
        while (l < left.length || r < right.length) {
            if(l == left.length)a[next++] = right[r++];
            else if(r == right.length)a[next++] = left[l++];
            else {
                if(left[l] <= right[r])a[next++] = left[l++];
                else a[next++] = right[r++];
            }
        }
        return a;
    }
}
