package linshen.greedy;

//给你一个下标从 0 开始的二维整数数组 transactions，其中transactions[i] = [costi, cashbacki] 。
//
// 数组描述了若干笔交易。其中每笔交易必须以 某种顺序 恰好完成一次。在任意一个时刻，你有一定数目的钱 money ，为了完成交易 i ，money >=
//costi 这个条件必须为真。执行交易后，你的钱数 money 变成 money - costi + cashbacki 。
//
// 请你返回 任意一种 交易顺序下，你都能完成所有交易的最少钱数 money 是多少。
/*

[co1,ch1],[co2,ch2]
b >= co1, b + ch1 -co1 >= co2, b + ch1-co1+ch2-co2
b >= co1,
b + ch1-co1 >= co2 => b >= co1+co2-ch1
b + ch1-co1+ch2-co2 >= co3 => b >= co1+co2+co3-ch1-ch2




// 1 <= transactions.length <= 10⁵
// transactions[i].length == 2
// 0 <= costi, cashbacki <= 10⁹
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L2412 {
    //todo hhhhh 脑子真的转不过来
    public long minimumMoney(int[][] transactions) {
        return -1;
    }
}
