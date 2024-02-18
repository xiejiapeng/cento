package sulqn;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    int size;
    Map<Integer,Node> data;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        int size = 0;
        this.capacity = capacity;
        this.data = new HashMap<>();
    }

    public int get(int key) {
        if(data.containsKey(key)){
            Node node = data.get(key);
            Node prev = node.prev;
            Node next = node.next;
            if(prev != null) {
                prev.next = next;
                if(next != null)next.prev = prev;
                node.next = head;
                node.prev = null;

                head.prev = node;
                head = node;
                if(node == tail)tail = prev;
            }
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(data.containsKey(key)){
            Node node = data.get(key);
            node.value = value;
            get(key);
        } else {
            Node node = new Node(key, value);
            data.put(key, node);
            if(head == null){
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
                get(key);
            }
            size++;
            if(size > capacity){
                Node prev = tail.prev;
                prev.next = null;
                data.remove(tail.key);
                tail = prev;
            }
        }
    }
}
