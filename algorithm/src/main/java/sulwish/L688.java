package sulwish;

import java.util.Arrays;

public class L688 {
    int[][] dirs = {{-2,-1},{-2,1},{2,-1},{2,1},{-1,-2},{1,-2},{-1,2},{1,2}};
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] p = new double[k+1][n][n];

        for(int s = 0; s <= k; s++){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if(s == 0) {
                        p[s][i][j] = 1;
                    } else if(s == 1) {
                        double stay = 0;
                        for (int[] dir : dirs) {
                            int x = i + dir[0];
                            int y = j + dir[1];
                            if(x >= 0 && x < n && y >= 0 && y < n){
                                stay++;
                            }
                        }
                        p[s][i][j] = stay / 8.0;
                    } else {
                        for (int[] dir : dirs) {
                            int x = i + dir[0];
                            int y = j + dir[1];
                            if(x >= 0 && x < n && y >= 0 && y < n){
                                p[s][i][j] += p[s-1][x][y] / 8;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++){
            System.out.println(Arrays.toString(p[2][i]));
        }
        return p[k][row][column];
    }

    public static void main(String[] args) {
        System.out.println(new L688().knightProbability(3,2,0,0));
    }
}
