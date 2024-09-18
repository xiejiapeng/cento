package sulwish;

import java.util.LinkedList;
import java.util.List;

/*
在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。

如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；

而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 */

public class L1104 {
    public static void main(String[] args) {
        System.out.println(new L1104().pathInZigZagTree(26));
    }

    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> l = new LinkedList<>();
        int height = height(label);
        while (label != 0) {
            l.addFirst(label);
            int r = logicToReal(label, height);
            int last = r / 2;
            label = realToLogic(last, height - 1);

            height--;
        }
        return l;
    }

    private int logicToReal(int x, int height) {
        if (height == 0) return 0;
        if (height % 2 != 0) {
            return x;
        } else {
            int end = (int) Math.pow(2, height) - 1;
            int start = end / 2 + 1;
            return start + end - x;
        }
    }

    private int realToLogic(int x, int height) {
        if (height == 0) return 0;
        if (height % 2 != 0) {
            return x;
        } else {
            int end = (int) Math.pow(2, height) - 1;
            int start = end / 2 + 1;
            return start + end - x;
        }
    }

    private int height(int x) {
        int t = 0;
        while (x != 0) {
            x /= 2;
            t++;
        }
        return t;
    }
}
