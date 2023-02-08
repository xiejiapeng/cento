package solutions;

/*
    [2,1,0]

    [1,0]

 */
public class L41 {
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            int cur = i;
            int num = nums[cur];
            while(num > 0 && num - 1 < nums.length && nums[num-1] != num){
                int tmp = nums[num-1];
                nums[num-1] = num;
                num = tmp;
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i+1)return i+1;
        }
        return nums.length+1;
    }

    public int firstMissingPositive2(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            if(nums[i] == i+1)continue;
            else{
                int num = nums[i];
                while (num > 0 && num - 1 < nums.length && nums[num-1] != num){
                    int tmp = nums[num-1];
                    nums[num-1] = num;
                    num = tmp;
                }
            }
        }
        for (int i = 0; i < nums.length; i++){
            if(nums[i] != i+1)return i+1;
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(new L41().firstMissingPositive(new int[]{2,1}));
    }
}
