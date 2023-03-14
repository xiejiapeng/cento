package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class L315 {
    static class Node{
        int i;
        int val;
        int count = 0;

        public Node(int i, int val) {
            this.i = i;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "i=" + i +
                    ", val=" + val +
                    ", count=" + count +
                    '}';
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++){
            nodes[i] = new Node(i,nums[i]);
        }
        Node[] m = mergeSort(nodes,0,nodes.length-1);
        Arrays.sort(m, Comparator.comparingInt(o -> o.i));
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < m.length; i++){
            ans.add(m[i].count);
        }
        return ans;
    }

    private Node[] mergeSort(Node[] nums, int left, int right) {
        if(left == right)return new Node[]{nums[left]};
        else {
            int mid = (right + left) / 2;
            Node[] l = mergeSort(nums,left,mid);
            Node[] r = mergeSort(nums,mid+1,right);
            return merge(l,r);
        }
    }

    private Node[] merge(Node[] a, Node[] b){
        Node[] res = new Node[a.length + b.length];
        int idx = 0;
        int i = 0;
        int j = 0;
        int add = 0;
        while (i < a.length || j < b.length) {
            if(i >= a.length){
                res[idx++] = b[j];
                add++;
                j++;
            } else if(j >= b.length){
                res[idx++] = a[i];
                a[i].count += add;
                i++;
            } else {
                if(a[i].val <= b[j].val){
                    res[idx++] = a[i];
                    a[i].count+=add;
                    i++;
                } else {
                    res[idx++] = b[j];
                    add++;
                    j++;
                }
            }
        }
        return res;
    }
}
