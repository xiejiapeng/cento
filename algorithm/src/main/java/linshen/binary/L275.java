package linshen.binary;

/*
给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照 升序排列 。计算并返回该研究者的 h 指数。

h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）至少 有 h 篇论文分别被引用了至少 h 次。

请你设计并实现对数时间复杂度的算法解决此问题。
 */

public class L275 {
    public int hIndex(int[] citations) {
        return findLargest(citations, 0, citations.length);
    }

    private int findLargest(int[] citations, int left, int right) {
        if(left == right)return left;
        else if(left == right - 1) {
            if(check(citations, right))return right;
            else return left;
        } else {
            int mid = (left + right) / 2;
            if(check(citations, mid))return findLargest(citations, mid, right);
            else return findLargest(citations, left, mid - 1);
        }
    }

    private boolean check(int[] citations, int h) {

        int t = findLeast(citations, h, 0, citations.length - 1);
        if(t == -1)return false;
        return citations.length - t >= h;
    }

    private int findLeast(int[] citations, int h, int left, int right) {
        if(left > right)return -1;
        else if(left == right) {
            if(citations[left] >= h)return left;
            else return -1;
        } else if(left == right - 1) {
            if(citations[left]>= h)return left;
            else if(citations[right]>=h)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(citations[mid] >= h)return findLeast(citations, h, left, mid);
            else return findLeast(citations, h, mid+1, right);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L275().hIndex(new int[]{100}));
    }
}
