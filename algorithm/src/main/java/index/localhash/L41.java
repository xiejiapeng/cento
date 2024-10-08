package index.localhash;

/*
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
 */
public class L41 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //nums[i] should be i + 1
        for (int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                int x = nums[i];
                while (x != i + 1 && x - 1 >= 0 && x - 1 < n) {
                    int tmp = nums[x-1];
                    //todo pay attention to the break condition
                    if(x == tmp)break;
                    nums[x-1] = x;
                    x = tmp;
                }
                nums[i] = x;
            }
        }
        for (int i = 0; i < n; i++){
            if(nums[i] != i + 1)return i + 1;
        }
        return n + 1;
    }

    public static void main(String[] args) {
        System.out.println(new L41().firstMissingPositive(new int[]{3,4,3,1}));
    }
}
