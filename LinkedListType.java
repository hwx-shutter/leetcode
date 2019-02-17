package com.hwx.daydayup;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by houwanxing1 on 2019/2/15
 */
public class LinkedListType {

    /**
     * 反转链表
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode reverseHead = null;
        ListNode cur = head;
        ListNode previous = null;

        while(cur != null){
            ListNode next = cur.next;
            //如果已经遍历到尾结点
            if(next == null){
                reverseHead = head;
            }

            cur.next = previous;
            previous = cur;
            cur = next;
        }

        return reverseHead;
    }

    /**
     * 交换链表中相邻两个节点[添加哨兵节点可以简化操作]
     * @param head
     * @return
     */
    private ListNode swapPairs(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        //添加哨兵节点
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        head = sentinel;

        while(head.next != null && head.next.next != null){
            ListNode node1 = head.next;
            ListNode node2 = head.next.next;

            //第二个节点作为头结点
            head.next = node2;
            //第一个节点的下一个节点指向原node2的下一个节点
            node1.next = node2.next;
            //新的第一个节点指向原来第二个
            node2.next = node1;

            head = node1;
        }

        return sentinel.next;
    }

    /**
     * 判断链表中是否有环存在
     * @param head
     * @return
     */
    private boolean hasCircle(ListNode head){
        if(head == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }

        return false;
    }

    class ListNode{
        private int val;
        private ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

}
