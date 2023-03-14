package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L229 {
    class Node {
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public List<Integer> majorityElement(int[] nums) {
        Node[] a = new Node[2];
        int notUsed = Integer.MIN_VALUE;
        for(int i = 0; i < 2; i++){
            a[i] = new Node(notUsed,notUsed);
        }

        for(int x : nums) {
            Arrays.sort(a, (u, v) -> -1 * Integer.compare(u.y, v.y));

            boolean find = false;
            for(Node node : a){
                if(node.x == x){
                    node.y++;
                    find = true;
                    break;
                }
                if(node.y == notUsed){
                    node.x = x;
                    node.y = 1;
                    find = true;
                    break;
                }
            }

            if(!find){
                for(Node rm : a){
                    rm.y--;
                    if(rm.y == 0) {
                        rm.x = notUsed;
                        rm.y = notUsed;
                    }
                }

            }

        }

        List<Integer> ans = new ArrayList();
        int n = nums.length;
        for(Node x : a){
            int c = 0;
            for(int y : nums){
                if(x.x == y)c++;
            }
            if(c > n / 3)ans.add(x.x);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L229().majorityElement(new int[]{2,1,1,3,1,4,5,6}));
    }
}
