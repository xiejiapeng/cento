package sulwish;

/*
在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。
一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。
现给定起始字符串start和结束字符串end，
请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。

双指针
根据题意，我们每次移动要么是将 XL 变为 LX，要么是将 RX 变为 XR，而该两者操作可分别看做将 L 越过多个 X 向左移动，将 R 越过多个 X 向右移动。

因此在 start 和 end 中序号相同的 L 和 R 必然满足坐标性质：

序号相同的 L : start 的下标不小于 end 的下标（即 L 不能往右移动）
序号相同的 R : start 的下标不大于 end 的下标（即 R 不能往左移动）
其中「序号」是指在 LR 字符串中出现的相对顺序。

代码：

Java
class Solution {
    public boolean canTransform(String start, String end) {
        int n = start.length(), i = 0, j = 0;
        while (i < n || j < n) {
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && end.charAt(j) == 'X') j++;
            if (i == n || j == n) return i == j;
            if (start.charAt(i) != end.charAt(j)) return false;
            if (start.charAt(i) == 'L' && i < j) return false;
            if (start.charAt(i) == 'R' && i > j) return false;
            i++; j++;
        }
        return i == j;
    }
}
时间复杂度：O(n)O(n)O(n)
空间复杂度：O(1)O(1)O(1)

作者：宫水三叶
链接：https://leetcode.cn/problems/swap-adjacent-in-lr-string/solutions/1863778/by-ac_oier-ye71/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class L777 {
    // L < X < R
    public boolean canTransform(String start, String end) {
        int n = start.length();
        boolean[][] can = new boolean[n][n];
        for (int len = 1; len <= n; len++){
            for (int i = 0; i + len - 1 < n; i++){
                int j = i + len - 1;
                if(len == 1){
                    can[i][j] = start.charAt(i) == end.charAt(i);
                } else {
                    if(start.charAt(i) == 'L') {
                        if(end.charAt(i) == 'L') can[i][j] = can[i+1][j];
                        else can[i][j] = false;
                    } else if(start.charAt(i) == 'X') {
                        can[i][j] = (can[i+1][j] && end.charAt(i) == 'X');
                        for (int k = i + 1; k <= j; k++){
                            if(start.charAt(k) == 'L'){
                                can[i][j] |= (end.charAt(i) == 'L' && (i+1>k-1||can[i+1][k-1]) && (k+1>j||can[k+1][j]) && (end.charAt(k) == 'X'));
                            } else {
                                break;
                            }
                        }
                    } else {
                        can[i][j] = (can[i+1][j] && end.charAt(i) == 'R');
                        for (int k = i + 1; k <= j; k++){
                            if(start.charAt(k) == 'X'){
                                can[i][j] |= (end.charAt(i) == 'X' && (i+1>k-1||can[i+1][k-1]) && (k+1>j||can[k+1][j]) && (end.charAt(k) == 'R'));
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }

        return can[0][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new L777().canTransform("XXL","LXX"));
    }
}
