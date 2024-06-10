# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

"""
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
"""
class Solution(object):
    def sortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head == None or head.next == None:
            return head
        else:
            p = head
            q = head.next
            while q != None and q.next != None:
                q = q.next.next
                p = p.next

            q = p.next
            p.next = None
            print(p.val)
            x = self.sortList(head)
            y = self.sortList(q)
            return self.merge(x, y)

    def merge(self, a, b):
        if a == None:
            return b
        elif b == None:
            return a
        else:
            if a.val <= b.val:
                c = a.next
                a.next = None
                x = self.merge(c, b)
                a.next = x
                return a
            else:
                c = b.next
                b.next = None
                x = self.merge(a, c)
                b.next = x
                return b


