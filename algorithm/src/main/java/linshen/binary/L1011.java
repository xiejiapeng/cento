package linshen.binary;

/*
传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。

传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500

n / (x / 500) + 1 == days
 */

public class L1011 {
    public int shipWithinDays(int[] weights, int days) {
        int n = weights.length;
        int upper = (n / days + 1) * 500;
        return findSmallest(1, upper, days, weights);
    }

    private int findSmallest(int left, int right, int threshold, int[] nums) {
        if(left == right) return left;
        else if(left == right - 1) {
            if(check(nums, left, threshold))return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(check(nums, mid, threshold))return findSmallest(left, mid, threshold, nums);
            else return findSmallest(mid + 1, right, threshold, nums);
        }
    }

    private boolean check(int[] nums, int t, int threshold) {
        if(t == 2){
            System.out.println("fuc");
        }
        int day = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++){
            if(cur + nums[i] > t) {
                cur = 0;
                day++;
            }
            if(nums[i] > t)return false;
            cur += nums[i];
        }
        if(cur > 0)day++;
        return day <= threshold;
    }

    public static void main(String[] args) {
        System.out.println(new L1011().shipWithinDays(new int[]{1,2,3,1,1}, 4));
    }
}
