package index.binary;

/*
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */

public class L215 {
    /*
    ..., left] => <= pivot
    [right, ... => > pivot
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return find(nums, n-k+1, 0, n - 1);
    }

    private int find(int[] nums, int k, int a, int b) {
        int left = a + 1;
        int right = b;
        int pivot = nums[a];
        int x = -1;
        while (left < right) {
            while (nums[left] <= pivot && left < right) {
                nums[left-1] = nums[left];
                left++;
            }
            if(left == right) {
                if(nums[left] <= pivot) {
                    nums[left-1] = nums[left];
                    nums[left] = pivot;
                    x = left;
                } else {
                    nums[left-1] = pivot;
                    x = left-1;
                }
                break;
            } else {
                while (nums[right] > pivot && right > left) {
                    right--;
                }
                if(right == left) {
                    nums[left-1] = pivot;
                    x=left-1;
                    break;
                } else {
                    nums[left-1] = nums[right];
                    nums[right] = nums[left];
                    if(left == right - 1) {
                        nums[left] = pivot;
                        x = left;
                        break;
                    }
                    left++;
                    right--;

                }
            }
        }
        if(x - a + 1 == k)return nums[x];
        else if(x - a + 1 < k)return find(nums, k-(x-a+1), x+1,b);
        else return find(nums,k,a,x);
    }

    int quickselect2(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) return quickselect2(nums, l, j, k);
        else return quickselect2(nums, j + 1, r, k);
    }
    public int findKthLargest2(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect2(_nums, 0, n - 1, n - k);
    }

    public static void main(String[] args) {
        System.out.println(new L215().findKthLargest(new int[]{5,6,4}, 2));
    }
}
