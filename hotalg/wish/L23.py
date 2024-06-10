# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

"""
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。
"""
import heapq

class ListNode(object):
    def __init__(self, val = 0, next = None):
        self.val = val
        self.next = next


class Solution(object):
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        queue = []
        l = len(lists)
        for i in range(0,l):
            if lists[i]:
                heapq.heappush(queue, (lists[i].val, i))
                lists[i] = lists[i].next


        dummy = ListNode()
        tail = dummy
        while len(queue) > 0:
            x,i = heapq.heappop(queue)

            tail.next = ListNode(x)
            tail = tail.next

            if lists[i] != None:
                heapq.heappush(queue, (lists[i].val, i))
                lists[i] = lists[i].next
        return dummy.next

if __name__ == '__main__':
    l1 = ListNode(1)
    l2 = ListNode(3)
    l3 = ListNode(2)
    l1.next = l2
    l = Solution().mergeKLists([l1, l3])
    print(l.val)

