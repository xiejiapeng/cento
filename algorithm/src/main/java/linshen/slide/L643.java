package linshen.slide;

/*
给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。

请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。

任何误差小于 10-5 的答案都将被视为正确答案。
 */

public class L643 {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;
        double max = -100000;
        int cnt = 0;
        double sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            cnt += 1;
            while (cnt > k) {
                sum -= nums[left];
                cnt -= 1;
                //todo m: 记得减1
                left++;
            }
            //todo m: 记得这个判断
            if(cnt == k) {
                double avg = sum / cnt;
//                if(avg > max) System.out.println("right=" + right + " ,left="+left +", sum="+sum+",cnt="+cnt + ",avg=" + avg);
                max = Math.max(max, avg);
            }

        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L643().findMaxAverage(new int[]{4,0,4,3,3}, 5));
    }
}
