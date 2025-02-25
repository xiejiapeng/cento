package index.findrule;

/*
给你一个整数数组 distance。

从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，
向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。

要么每次超过
如果某次没有超过，后面都不能超过
 */
public class L335 {
    public boolean isSelfCrossing(int[] distance) {
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        int x = 0;
        int y = 0;
        boolean calm = false;
        for (int i = 0; i < distance.length; i++){
            if(i % 4 == 0) {
                //up
                int tx = x;
                int ty = y + distance[i];
                if(calm) {
                    if(ty >= up)return true;
                    else up = ty;
                } else {
                    if(ty > up) {
                        up = ty;
                    } else {
                        calm = true;
                        up = ty;
                        
                    }
                }

                x = tx;
                y = ty;
            } else if(i % 4 == 1) {
                //left
                int tx = x - distance[i];
                int ty = y;
                if(calm) {
                    if(tx <= left)return true;
                    else left = tx;
                } else {
                    if(tx < left) {
                        left = tx;
                    } else {
                        calm = true;
                        left = tx;
                    }
                }

                x = tx;
                y = ty;
            } else if(i % 4 == 2) {
                //down
                int tx = x;
                int ty = y - distance[i];
                if(calm) {
                    if(ty <= down)return true;
                    else down = ty;
                } else {
                    if(ty < down) {
                        down = ty;
                    } else {
                        calm = true;
                        down = ty;
                    }
                }

                x = tx;
                y = ty;
            } else {
                //right
                int tx = x + distance[i];
                int ty = y;
                if(calm) {
                    if(tx >= right)return true;
                    else right = tx;
                } else {
                    if(tx > right) {
                        right = tx;
                    } else {
                        calm = true;
                        right = tx;
                    }
                }

                x = tx;
                y = ty;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new L335().isSelfCrossing(new int[]{1,1,2,1,1}));
    }
}
