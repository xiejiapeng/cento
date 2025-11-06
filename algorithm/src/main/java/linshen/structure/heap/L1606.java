package linshen.structure.heap;

/*
你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则如下：

第 i （序号从 0 开始）个请求到达。
如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。
比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。
你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。

请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
提示：

1 <= k <= 105
1 <= arrival.length, load.length <= 105
arrival.length == load.length
1 <= arrival[i], load[i] <= 109
arrival 保证 严格递增 。
 */

import java.util.*;

public class L1606 {
    public static void main(String[] args) {
        System.out.println(new L1606().busiestServers(3, new int[]{1, 2, 3, 4, 5}, new int[]{5, 2, 3, 3, 3}));
    }

    //todo hhhhh 两个队列的模板题（这里演化了，首先用treeset代替满足条件的元素的队列，busy和原来的一样代表不满足条件的队列）
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] w = new int[k];
        TreeSet<Integer> ids = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            ids.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int i = 0; i < arrival.length; i++) {
            int arr = arrival[i];
            while (!busy.isEmpty() && busy.peek()[1] <= arr) {
                int[] t = busy.poll();
                ids.add(t[0]);
            }
            int target = i % k;
            if (ids.isEmpty()) continue;
            Integer x = ids.ceiling(target);
            if (x == null) {
                x = ids.ceiling(0);
            }
            busy.add(new int[]{x, arr + load[i]});
            ids.remove(x);
            w[x]++;
        }
        int max = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (w[i] > max) {
                max = w[i];
                ans = new ArrayList<>();
                ans.add(i);
            } else if (w[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}
