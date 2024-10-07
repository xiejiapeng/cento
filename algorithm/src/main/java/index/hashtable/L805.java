package index.hashtable;

/*
给定你一个整数数组 nums

我们要将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，并且 average(A) == average(B) 。

如果可以完成则返回true ， 否则返回 false  。

注意：对于数组 arr ,  average(arr) 是 arr 的所有元素的和除以 arr 长度。

a/m = b/n
a/m = (s-a)/n
na=ms-ma
(m+n)a=ms
a/m=s/(m+n)
 */

public class L805 {
    public boolean splitArraySameAverage(int[] nums) {
        //todo 先搜索前一半，后一半遍历时借助前一半的结果，称为折半搜索
        return false;
    }
}
