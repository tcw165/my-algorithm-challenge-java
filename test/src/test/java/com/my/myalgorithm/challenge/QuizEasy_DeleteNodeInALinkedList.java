// Copyright (c) 2016-present boyw165
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.my.myalgorithm.challenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * Write a function to delete a node (except the tail) in a singly linked list,
 * given only access to that node.
 * <p/>
 * Example:
 * <pre>
 *     Given 1 -> 2 -> 3 -> 4, remove 3
 *     become 1 -> 2 -> 4
 * </pre>
 * Reference:
 * <ul>
 * <li>https://leetcode.com/problems/delete-node-in-a-linked-list/description/</li>
 * </ul>
 */
public class QuizEasy_DeleteNodeInALinkedList {

    @Test
    public void test() throws Exception {
        final ListNode originalNode = generateLinkedList(1, 2, 3, 4);
        deleteNode(findNode(originalNode, 3));

        Assert.assertEquals(generateLinkedList(1, 2, 4), originalNode);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private void deleteNode(ListNode node) {
        if (node.next == null) {
            throw new IllegalArgumentException("Cannot delete the tail node.");
        }

        // Since we couldn't enter the preceding node, we can not delete the
        // given node. We can just copy the next node to the given node and
        // delete the next one.
        node.val = node.next.val;
        node.next = node.next.next;
    }

    private ListNode findNode(ListNode node, int val) {
        while (node != null) {
            if (node.val == val) {
                return node;
            }

            node = node.next;
        }

        return null;
    }

    private ListNode generateLinkedList(int... vals) {
        final ListNode head = new ListNode(0);
        ListNode node = head;

        for (int val : vals) {
            node.next = new ListNode(val);
            node = node.next;
        }

        return head.next;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class ListNode {
        int val;
        ListNode next;

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
