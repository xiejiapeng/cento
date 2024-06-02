# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

"""
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
"""
class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        p = headA
        q = headB
        while p != None and q != None:
            p = p.next
            q = q.next

        if p == None:
            q2 = headB
            while q != None:
                q = q.next
                q2 = q2.next
            p2 = headA
            while p2 != None:
                if p2 == q2:
                    return p2
                else:
                    p2 = p2.next
                    q2 = q2.next

            return None
        else:
            p2 = headA
            while p != None:
                p = p.next
                p2 = p2.next
            q2 = headB
            while q2 != None:
                if p2 == q2:
                    return p2
                else:
                    p2 = p2.next
                    q2 = q2.next

            return None


