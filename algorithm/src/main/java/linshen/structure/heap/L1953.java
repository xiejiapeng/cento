package linshen.structure.heap;

/*
给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。

你可以按下面两个规则参与项目中的工作：

每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
一旦所有项目中的全部阶段任务都完成，或者执行仅剩的一个阶段任务将会导致你违反上面的规则，
你将 停止工作。注意，由于这些条件的限制，你可能无法完成所有阶段任务。

返回在不违反上面规则的情况下你 最多 能工作多少周。

提示：

n == milestones.length
1 <= n <= 105
1 <= milestones[i] <= 109
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L1953 {
    public long numberOfWeeks(int[] milestones) {
        //todo hhhhh 记住，只要关心最大的那一个，否则可以依次遍历每一个项目；不要去想最大的与次大的差值，这样将问题想复杂了
        long max = -1;
        long sum = 0;
        for (int m : milestones) {
            if(m > max)max = m;
            sum += m;
        }
        long rest = sum - max;
        if(max <= rest + 1)return sum;
        else return 2*rest+1;
    }
}
