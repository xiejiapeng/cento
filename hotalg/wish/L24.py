# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

"""
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
"""
class Solution(object):
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        dummy = ListNode()
        dummy.next = head
        p = dummy
        q = head
        while q != None and q.next != None:
            # p -> q -> q.next ...
            x = q.next.next
            p.next = q.next
            q.next.next = q
            q.next = x

            p = q
            q = x
        return dummy.next
