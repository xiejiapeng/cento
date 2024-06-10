# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

"""
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
"""
class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        dummy = ListNode()
        dummy.next = head
        p = dummy
        q = dummy
        for i in range(0, n+1):
            q = q.next
        while q != None:
            p = p.next
            q = q.next

        p.next = p.next.next
        return dummy.next
