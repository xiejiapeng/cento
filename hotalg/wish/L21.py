# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

"""
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
"""

class Solution(object):
    def mergeTwoLists(self, list1, list2):
        """
        :type list1: Optional[ListNode]
        :type list2: Optional[ListNode]
        :rtype: Optional[ListNode]
        """
        dummy = ListNode()
        tail = dummy
        p = list1
        q = list2
        while p != None or q != None:
            if p == None:
                tail.next = q
                q = q.next
            elif q == None:
                tail.next = p
                p = p.next
            else:
                if p.val <= q.val:
                    tail.next = p
                    p = p.next
                else:
                    taitjl.next = q
                    q = q.next

            tail = tail.next
        return dummy.next

