package index.binarytree;

/*
m*n 的二维数组 plants 记录了园林景观的植物排布情况，具有以下特性：

每行中，每棵植物的右侧相邻植物不矮于该植物；
每列中，每棵植物的下侧相邻植物不矮于该植物。


请判断 plants 中是否存在目标高度值 target。
 */

public class LCR121 {
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        if (plants == null || plants.length == 0 || plants[0].length == 0) return false;
        return find(plants, target, 0, 0, plants.length - 1, plants[0].length - 1);
    }

    //(a,b)
    // (c,d)
    private boolean find(int[][] plants, int target, int a, int b, int c, int d) {
        if (a > c || b > d) return false;
        if (a == c && b == d) {
            if (plants[a][b] == target) return true;
            else return false;
        } else {
            if (plants[a][d] == target) return true;
            else if (plants[a][d] < target) return find(plants, target, a + 1, b, c, d);
            else return find(plants, target, a, b, c, d - 1);
        }
    }
}
