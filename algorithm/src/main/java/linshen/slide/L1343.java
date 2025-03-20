package linshen.slide;

/*
给你一个整数数组 arr 和两个整数 k 和 threshold 。

请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
 */

public class L1343 {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int ans = 0;
        int left = 0;
        double sum = 0;
        int cnt = 0;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            cnt++;
            while (cnt > k) {
                sum -= arr[left];
                cnt -= 1;
                left++;
            }
            if(cnt == k) {
                double avg = sum / cnt;
                if(avg >= threshold) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
