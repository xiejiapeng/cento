package solutions;

import java.util.*;

public class L218 {
    class Node {
        int x;
        int y;
        int z;

        public Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeMap<Integer,List<Node>> m = new TreeMap();
        for(int[] building : buildings) {
            int a = building[0];
            int b = building[1];
            int c = building[2];

            Node in = new Node(a,c,0);
            Node out = new Node(b,c,1);
            m.putIfAbsent(a,new ArrayList());
            m.get(a).add(in);
            m.putIfAbsent(b,new ArrayList());
            m.get(b).add(out);
        }

        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1,o2));
        List<List<Integer>> ans = new ArrayList();

        while(!m.isEmpty()) {
            if(q.isEmpty()){
                Map.Entry<Integer,List<Node>> e = m.pollFirstEntry();
                int pos = e.getKey();
                List<Node> l = e.getValue();
                for(Node node : l){
                    if(node.z == 0)q.add(node.y);
                }
                int h = q.peek();
                ans.add(Arrays.asList(pos,h));
            } else {
                int pre = q.peek();
                Map.Entry<Integer,List<Node>> e = m.pollFirstEntry();
                int pos = e.getKey();
                List<Node> l = e.getValue();
                for(Node node : l){
                    if(node.z == 0)q.add(node.y);
                    if(node.z == 1)q.remove(node.y);
                }
                int after = q.isEmpty() ? 0 : q.peek();
                if(pre != after){
                    ans.add(Arrays.asList(pos, after));
                }
            }


        }

        return ans;
    }
}
