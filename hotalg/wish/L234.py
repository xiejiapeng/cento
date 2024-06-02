# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
"""
给你一个单链表的头节点 head ，请你判断该链表是否为
回文链表
。如果是，返回 true ；否则，返回 false 。
"""
class Solution(object):
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        hr = self.reverse(head)
        while head != None:
            if hr.val != head.val:
                return False
            else:
                hr = hr.next
                head = head.next

        return True

    def reverse(self, head):
        if head == None or head.next == None:
            return head
        else:
            p = head
            h = None
            while p != None:
                t = ListNode(val=p.val)
                t.next = h
                h = t
                p = p.next
            return h
