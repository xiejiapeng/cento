# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

"""
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
"""
class Solution(object):
    def reverseKGroup(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        len = 0
        p = head
        while p != None:
            len += 1
            p = p.next

        dummy = ListNode()
        p = dummy
        q = head
        dummy.next = head
        while len >= k:
            x,y,z = self.reverse(q, k)
            p.next = x
            p = y
            q = z
            len -= k
        if len > 0:
            p.next = q
        return dummy.next


    def reverse(self, x, k):
        if k == 1:
            return (x, x, x.next)
        dummy = ListNode()
        dummy.next = x
        if x == None or x.next == None:
            return
        else:
            y = x.next
            x.next = None
            u,v,w = self.reverse(y, k-1)
            y.next = x
            return (u,x,w)
