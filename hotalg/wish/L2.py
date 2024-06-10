# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

"""
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
"""
class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        radix = 0
        p = l1
        q = l2
        dummy = ListNode()
        tail = dummy

        while p != None or q != None:
            if p == None:
                x = radix + q.val
                node = ListNode(x % 10)
                radix = x / 10
                q = q.next
            elif q == None:
                x = radix + p.val
                node = ListNode(x % 10)
                radix = x / 10
                p = p.next
            else:
                x = radix + p.val + q.val
                node = ListNode(x % 10)
                radix = x / 10
                p = p.next
                q = q.next

            tail.next = node
            tail = node

        if radix != 0:
            node = ListNode(radix)
            tail.next = node

        return dummy.next




