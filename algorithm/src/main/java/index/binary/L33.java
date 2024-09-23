package index.binary;

/*
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */

public class L33 {
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length-1);
    }

    public int search(int[] nums, int target ,int left, int right) {
        if(left == right) {
            return nums[left] == target ? left : -1;
        } else if(left == right - 1) {
            if(nums[left] == target)return left;
            else if(nums[right] == target) return right;
            else return -1;
        } else {
            //on different branches
            if(nums[left] > nums[right]) {
                int mid = (left + right) / 2;
                if(nums[mid] == target)return mid;
                else if(nums[mid] > target) {
                    if(nums[mid] > nums[left]) {
                        if(nums[left] <= target)return search(nums,target,left,mid-1);
                        else return search(nums,target,mid+1,right);
                    } else {
                        return search(nums, target, left, mid - 1);
                    }

                } else {
                    if(nums[mid] > nums[left]) {
                        return search(nums,target,mid+1,right);
                    } else {
                        if(nums[right] < target)return search(nums,target,left,mid-1);
                        else return search(nums,target,mid+1,right);
                    }
                }
            } else {
                int mid = (left + right) / 2;
                if(nums[mid] == target)return mid;
                else if(nums[mid] < target) {
                    return search(nums, target, mid + 1, right);
                } else {
                    return search(nums, target, left, mid - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L33().search(new int[]{5,1,3}, 3));
    }
}
