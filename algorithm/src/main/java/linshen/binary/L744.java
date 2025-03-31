package linshen.binary;

/*
给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。

返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
 */

public class L744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int a = find(letters, 0, letters.length, target);
        if(a == -1) {
            return letters[0];
        } else {
            return letters[a];
        }
    }

    private int find(char[] letters, int left, int right, char target) {
        if(left > right)return -1;
        if(left == right) {
            if(letters[left] > target)return left;
            else return -1;
        } else if(left == right - 1) {
            if(letters[left] > target)return left;
            else if(letters[right] > target)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(letters[mid] > target) return find(letters, left, mid, target);
            else return find(letters, mid + 1, right, target);
        }
    }
}
