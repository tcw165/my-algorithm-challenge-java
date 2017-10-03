package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * <p>
 * For example,
 * <pre>
 *   Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *   Output: 7 -> 0 -> 8
 * </pre>
 * <p>
 * LeetCode: https://leetcode.com/problems/add-two-numbers/description/
 */
public class Quiz_AddTwoNumbers {

    @Test
    public void testEquals() {
        ListNode nodeA = new ListNode(5);
        nodeA.next = new ListNode(0);

        ListNode nodeB = new ListNode(5);
        nodeB.next = new ListNode(0);

        Assert.assertEquals(nodeB, nodeA);
    }

    @Test
    public void test342Plus465_shouldEquals807_sol1() {
        ListNode nodeA = new ListNode(2);
        nodeA.next = new ListNode(4);
        nodeA.next.next = new ListNode(3);

        ListNode nodeB = new ListNode(5);
        nodeB.next = new ListNode(6);
        nodeB.next.next = new ListNode(4);

        ListNode sum = new ListNode(7);
        sum.next = new ListNode(0);
        sum.next.next = new ListNode(8);

        Assert.assertEquals(sum, addTwoNumbers_sol1(nodeA, nodeB));
    }

    @Test
    public void test0Plus0_shouldEquals0_sol1() {
        ListNode nodeA = new ListNode(0);

        ListNode nodeB = new ListNode(0);

        ListNode sum = new ListNode(0);

        Assert.assertEquals(sum, addTwoNumbers_sol1(nodeA, nodeB));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 46.67% on Leetcode.
     */
    private ListNode addTwoNumbers_sol1(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode sumList = new ListNode(0);
        ListNode sumNode = sumList;

        // Time complexity: O(n)
        while (n1 != null || n2 != null) {
            int v1 = n1 != null ? n1.val : 0;
            int v2 = n2 != null ? n2.val : 0;

            int sum = v1 + v2 + carry;
            sumNode.next = new ListNode(sum % 10);
            sumNode = sumNode.next;
            carry = sum / 10;

            if (n1 != null) n1 = n1.next;
            if (n2 != null) n2 = n2.next;
        }

        if (carry > 0) {
            sumNode.next = new ListNode(carry);
        }

        return sumList.next;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int x) {
            val = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ListNode listNode = (ListNode) o;

            if (val != listNode.val) return false;
            return next != null ? next.equals(listNode.next) : listNode.next == null;
        }

        @Override
        public int hashCode() {
            int result = val;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }
    }
}
