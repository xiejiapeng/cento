package sulwish;

/*
给定一个二叉树：

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。

初始状态下，所有 next 指针都被设置为 NULL 。
 */

import java.util.LinkedList;
import java.util.Queue;

public class L117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if(root == null)return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0){
                Node x = queue.poll();
                if(!queue.isEmpty() && size > 0) {
                    x.next = queue.peek();
                }
                if(x.left != null){
                    queue.add(x.left);
                }
                if(x.right != null){
                    queue.add(x.right);
                }
            }
        }
        return root;
    }
}
