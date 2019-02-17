package com.hwx.daydayup;

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
     * k个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;

        ListNode check = head;
        int canProceed = 0;
        int count = 0;
        while(canProceed < k && check != null){
            check = check.next;
            canProceed++;
        }

        if(canProceed == k){
            while(count < k && cur != null){
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                count++;
            }
            if(next != null){
                //head为链表翻转后的尾结点
                head.next = reverseKGroup(next, k);
            }

            //prev为翻转后的头结点
            return prev;
        }else{
            return head;
        }
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

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }

        //如果链表中没有环,fast指针的next会指向null
        if(fast == null || fast.next == null){
            return null;
        }

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * 移除链表元素
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        head = sentinel;

        while(head.next != null){
            if(head.next.val == val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }

        return sentinel.next;
    }

    /**
     * 旋转链表[本质上是将尾部向前数第K个元素作为头，原来的头接到原来的尾上]
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        int length = 0;
        ListNode cur = head;
        ListNode tail = null;
        while (cur != null) {
            length++;
            if (cur.next == null) {
                tail = cur;
            }
            cur = cur.next;
        }

        //把链表变成一个环
        tail.next = head;
        int n = k % length;
        for (int i = 0;i < length - n;i++) {
            tail = tail.next;
        }
        cur = tail.next;
        tail.next = null;

        return cur;
    }

    /**
     * 删除链表的倒数第N个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        ListNode first = sentinel;
        ListNode second = sentinel;
        //第一个指针先走n+1步
        for(int i = 1;i <= n + 1;i++){
            first = first.next;
        }

        while(first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return sentinel.next;
    }

    /**
     * 参考：Sort List——经典（链表中的归并排序） https://www.cnblogs.com/qiaozhoulin/p/4585401.html
     *
     * 归并排序法：在动手之前一直觉得空间复杂度为常量不太可能，因为原来使用归并时，都是 O(N)的，
     * 需要复制出相等的空间来进行赋值归并。对于链表，实际上是可以实现常数空间占用的（链表的归并
     * 排序不需要额外的空间）。利用归并的思想，递归地将当前链表分为两段，然后merge，分两段的方
     * 法是使用 fast-slow 法，用两个指针，一个每次走两步，一个走一步，知道快的走到了末尾，然后
     * 慢的所在位置就是中间位置，这样就分成了两段。merge时，把两段头部节点值比较，用一个 p 指向
     * 较小的，且记录第一个节点，然后 两段的头一步一步向后走，p也一直向后走，总是指向较小节点，
     * 直至其中一个头为NULL，处理剩下的元素。最后返回记录的头即可。
     *
     * 主要考察3个知识点，
     * 知识点1：归并排序的整体思想
     * 知识点2：找到一个链表的中间节点的方法
     * 知识点3：合并两个已排好序的链表为一个新的有序链表
     */
    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(p);
        return merge(l, r);
    }

    ListNode merge(ListNode l, ListNode r) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if (l != null) {
            cur.next = l;
        }
        if (r != null) {
            cur.next = r;
        }
        return dummyHead.next;
    }

    class ListNode{
        private int val;
        private ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

}
