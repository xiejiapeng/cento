"""
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
"""
class Solution(object):
    def canFinish(self, numCourses, prerequisites):
        """
        :type numCourses: int
        :type prerequisites: List[List[int]]
        :rtype: bool
        """
        cnt = numCourses
        source = set(range(0, cnt))
        outage = {}
        queue = []
        see = set()
        input = {}
        for pre in prerequisites:
            y = pre[0]
            x = pre[1]
            outage[x] = outage.get(x, list())
            outage[x].append(y)
            input[y] = input.get(y,0) + 1
            if y in source:
                source.discard(y)
        queue += [x for x in source]
        while len(queue) > 0:
            u = queue.pop(0)
            see.add(u)
            if u in outage:
                for y in outage[u]:
                    input[y] = input[y] - 1
                    if y not in see and input[y] == 0:
                        see.add(y)
                        queue.append(y)
        if len(see) == cnt:
            return True
        else:
            return False

if __name__ == '__main__':
    print(Solution().canFinish(3, [[1,0],[1,2],[0,1]]))

