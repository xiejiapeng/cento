package solutions;

public class L59 {
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        System.out.println(new L59().generateMatrix(3));
    }

    public int[][] generateMatrix(int n) {
        int up = 0;
        int down = n;
        int left = -1;
        int right = n;

        int curx = 0;
        int cury = 0;
        int cur = 1;
        int d = 0;

        int[][] ans = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            System.out.println("curx=" + curx + ",cury=" + cury + ",d=" + d);
            if (cur == 8) {
                System.out.println("a");
            }
            ans[curx][cury] = cur;

            int x = curx + dir[d % 4][0];
            int y = cury + dir[d % 4][1];
            if (d % 4 == 3 && x == up) {
                up = curx;
                d++;
            } else if (d % 4 == 1 && x == down) {
                down = curx;
                d++;
            } else if (d % 4 == 2 && y == left) {
                left = cury;
                d++;
            } else if (d % 4 == 0 && y == right) {
                right = cury;
                d++;
            }
            curx += dir[d % 4][0];
            cury += dir[d % 4][1];

            cur++;
        }

        return ans;
    }
}
