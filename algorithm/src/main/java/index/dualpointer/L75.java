package index.dualpointer;

/*
给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，
并按照红色、白色、蓝色顺序排列。

我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

必须在不使用库内置的 sort 函数的情况下解决这个问题。
 */

public class L75 {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int left = 0;
        for (int i = 0; i < n; i++){
            if(nums[i] == 0){
                int tmp = nums[left];
                nums[left] = 0;
                nums[i] = tmp;
                left++;
            }
        }
        int right = left;
        for (int j = left; j < n; j++){
            if(nums[j] == 1) {
                int tmp = nums[right];
                nums[right] = 1;
                nums[j] = tmp;
                right++;
            }
        }
    }
}