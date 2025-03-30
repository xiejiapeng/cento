package linshen.binary;

/*
给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。

同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。

请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。

n == spells.length
m == potions.length
1 <= n, m <= 105
1 <= spells[i], potions[i] <= 105
1 <= success <= 1010
 */

import java.util.Arrays;

public class L2300 {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++){
            int t = findFirstLarger(potions, (long)(spells[i]), success, 0, m - 1);
            if(t == -1)ans[i] = 0;
            else ans[i] = (m - t);
        }
        return ans;
    }

    private int findFirstLarger(int[] potions, long spell, long success, int left, int right) {
        if(left > right) return -1;
        if(left == right) {
            if(spell * potions[left] >= success)return left;
            else return -1;
        } else if(left == right - 1) {
            if(spell * potions[left] >= success)return left;
            else if(spell * potions[right] >= success) return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(spell * potions[mid] >= success) return findFirstLarger(potions, spell, success, left, mid);
            else return findFirstLarger(potions, spell, success, mid + 1, right);
        }
    }

    public static void main(String[] args) {

    }
}
