package linshen.greedy;

/*
假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，
请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
[1,1,2,4] -> [1,2,2,3] -> [2,2,2,2]
[1,2,4,1] -> [2,2,3,1] -> [2,2,2,2]

[a,b,c,d,e] -> [
 */

public class L517 {
    //todo hhh 贪心题切记，不要在构思或者证明的时候就思考对不对！！不要在构思的时候思考太多临界或边界情况，比如这里某个数为0的情况，不要想的太复杂，有大致的思路后直接贪心
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int[] sum = new int[n+1];
        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + machines[i];
        }
        if(sum[n] % n != 0)return -1;
        int target = sum[n] / n;
        int max = -1;
        for (int i = 0; i < n; i++){
            int t = 0;
            if(sum[i] < i * target) {
                t += i * target - sum[i];
            }
            if((n-i-1) * target > (sum[n] - sum[i+1])) {
                t += (n-i-1) * target - (sum[n] - sum[i+1]);
            }
            max = Math.max(max, t);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L517().findMinMoves(new int[]{0,3,0}));
    }
}
