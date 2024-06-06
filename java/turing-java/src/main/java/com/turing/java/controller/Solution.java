package com.turing.java.controller;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode h = head;

        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int i = list.size() - n - 1;
        if (i < 0) {
            if (list.size() == 1) {
                return null;
            }
            return h.next;
        }
        ListNode listNode = list.get(i);
        listNode.next = listNode.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        for (int i = 2; i < 6; i++) {
            ListNode next = new ListNode(i);
            next.next = l1;
            l1 = next;
        }
        ListNode listNode = removeNthFromEnd(l1, 1);
        System.out.println("");

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode(1);
        ListNode tail = head;
        int index = 0;
        while (l1 != null || l2 != null) {
            int i1 = l1 == null ? 0 : l1.val;
            int i2 = l2 == null ? 0 : l2.val;
            int i = i1 + i2 + index;
            index = i / 10;
            ListNode cur = new ListNode(i % 10);
            tail.next = cur;
            tail = cur;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (tail.val == 0 && index == 1) {
            tail.next = new ListNode(1);
        }
        return head.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode tail = head;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                tail.next = list2;
                tail = tail.next;
                list2 = list2.next;
            } else {
                tail.next = list1;
                tail = tail.next;
                list1 = list1.next;
            }
        }
        tail.next = list1 == null ? list2 : list1;
        return head.next;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
