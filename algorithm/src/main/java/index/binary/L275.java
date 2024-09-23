package index.binary;

/*
给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照 升序排列 。计算并返回该研究者的 h 指数。

h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）至少 有 h 篇论文分别被引用了至少 h 次。

请你设计并实现对数时间复杂度的算法解决此问题。
 */

public class L275 {
    public int hIndex(int[] citations) {
        return search(citations, 1, citations.length);
    }

    private int search(int[] citations, int left, int right) {
        int n = citations.length;
        if(left == right) {
            if(citations[n-left]>=left)return left;
            return 0;
        } else if(left == right - 1) {
            if(citations[n-right] >= right)return right;
            else if(citations[n-left] >= left)return left;
            else return 0;
        } else {
            //find larget id, where 1 <= id <= n, ci[n-id] >= id
            int mid = (left + right) / 2;
            if(citations[n-mid] >= mid) {
                return search(citations, mid, right);
            } else {
                return search(citations, left, mid);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L275().hIndex(new int[]{0, 2, 3}));
    }
}
