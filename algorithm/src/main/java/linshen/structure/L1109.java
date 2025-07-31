package linshen.structure;

/*
这里有 n 个航班，它们分别从 1 到 n 进行编号。

有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味
着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。

请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 */

public class L1109 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        int[] diff = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int f = bookings[i][0];
            int l = bookings[i][1];
            int s = bookings[i][2];
            diff[f - 1] += s;
            if (l < n) diff[l] -= s;
        }
        int x = 0;
        for (int i = 0; i < n; i++) {
            x += diff[i];
            res[i] = x;
        }
        return res;
    }
}
