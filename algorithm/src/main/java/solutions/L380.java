package solutions;

import java.util.*;

public class L380 {
    static class RandomizedSet {
        class Node {
            int val;
            Node prev;
            Node next;

            public Node(int val){
                this.val = val;
            }
        }

        Node head;
        Node tail;
        Random r;

        List<Node> nodes;
        Map<Integer,Integer> elems;
        int size = 0;

        public RandomizedSet() {
            nodes = new ArrayList();
            elems = new HashMap();
            r = new Random();
        }

        public boolean insert(int val) {
            if(elems.containsKey(val))return false;
            else {
                size++;
                Node node = new Node(val);

                if(head == null){
                    head = node;
                    tail = node;

                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }

                nodes.add(node);
                elems.put(val, size-1);

                return true;
            }
        }

        public boolean remove(int val) {
            if(elems.containsKey(val)){
                int idx = elems.get(val);
                Node node = nodes.get(idx);
                if(nodes.size() == 1){
                    head = null;
                    tail = null;
                    nodes.clear();
                    elems.clear();
                } else {
                    Node n = tail;
                    Node ntail = tail.prev;

                    Node prev = node.prev;
                    Node next = node.next;

                    if(prev != null)prev.next = tail;
                    if(next != null)next.prev = tail;
                    tail.prev = prev;
                    tail.next = next;
                    elems.put(tail.val, idx);
                    nodes.set(idx, tail);
                    ntail.next = null;
                    tail = ntail;
                }
                elems.remove(val);
                size--;
                return true;
            } else {
                return false;
            }
        }

        public int getRandom() {
            int random = r.nextInt(size);
            return nodes.get(random).val;
        }
    }

    public static void main(String[] args) {
        RandomizedSet r= new RandomizedSet();
        r.insert(0);
        r.insert(1);
        r.remove(0);
        r.insert(2);
        r.remove(1);
        System.out.println(r.getRandom());
        Random random = new Random();
        random.nextInt(100);

    }
}
