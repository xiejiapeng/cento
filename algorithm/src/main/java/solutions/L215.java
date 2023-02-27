package solutions;

public class L215 {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,k);
    }

    //start,..,x <= pivot
    //x+1,...,end > pivot
    public int findKthLargest(int[] nums, int start, int end, int k) {
        int x = pivot(nums, start, end);
        if(end - x == k - 1)return nums[x];
        if(end - x < k - 1)return findKthLargest(nums,start,x-1,k-(end-x)-1);
        else return findKthLargest(nums,x+1,end,k);
    }

    //[pivot=start,start+1,...,end]
    // 3 5 2 1 6 -> 3 1 2 5 6, 5 2 ->
    private int pivot(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start;
        int right = end;
        while (left < right){
            while (left < right && nums[left] <= pivot)left++;
            while (left < right && nums[right] > pivot)right--;
            if(left == right)break;
            else{
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }


        int x = nums[left] <= pivot ? left : left - 1;

        nums[start] = nums[x];
        nums[x] = pivot;
        return x;
    }

    public static void main(String[] args) {
        System.out.println(new L215().findKthLargest(new int[]{2,1}, 1));
    }
}
