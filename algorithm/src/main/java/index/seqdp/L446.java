package index.seqdp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。

如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。

例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
再例如，[1, 1, 2, 5, 7] 不是等差序列。
数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。

例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
题目数据保证答案是一个 32-bit 整数。

1  <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
 */
public class L446 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long,Long>[] seqs = new HashMap[n];
        Map<Long, Long>[] all = new HashMap[n];
        int ans = 0;
        for (int i = 0; i < n; i++){
            seqs[i] = new HashMap<>();
            all[i] = new HashMap<>();
            for (int j = i - 1; j > -1; j--) {
                long diff = (long)nums[i] - (long)nums[j];
                long target = (long)nums[j] - diff;
                long cnt = all[j].getOrDefault(target, 0l);
                if(cnt > 0) {
                    ans += cnt;
                    seqs[i].put(diff, seqs[i].getOrDefault(diff,0l) + cnt);
                }
                long t = seqs[j].getOrDefault(diff, 0l);
                if(t > 0){
                    ans += t;
                    seqs[i].put(diff, seqs[i].getOrDefault(diff, 0l) + t);
                }
            }
            if(i != 0) {
                all[i].putAll(all[i-1]);
                all[i].put((long)(nums[i-1]), all[i].getOrDefault((long)nums[i-1], 0l) + 1);
            }

        }
//        System.out.println(Arrays.toString(nums));
//        System.out.println(Arrays.toString(all));
//        System.out.println(Arrays.toString(seqs));

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L446().numberOfArithmeticSlices(new int[]{0,2000000000,-294967296}));
    }
}
