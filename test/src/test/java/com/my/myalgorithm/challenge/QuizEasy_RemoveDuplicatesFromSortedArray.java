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

import java.util.Stack;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * Reference:
 * <p/>
 * LeetCode: https://leetcode.com/problems/merge-two-sorted-lists/description/
 */
public class QuizEasy_MergeTwoSortedLists {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(numToListNode(1, 3, 4, 5, 8, 10, 18),
                            mergeTwoLists(numToListNode(1, 8, 10, 18),
                                          numToListNode(3, 4, 5)));
        Assert.assertEquals(numToListNode(1, 3, 5, 7, 10, 13, 18),
                            mergeTwoLists(numToListNode(1, 7, 10, 18),
                                          numToListNode(3, 5, 13)));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 49.23% on LeetCode.
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Essentially is the merge part of "insertion sort".
        final ListNode mergedHead = new ListNode(0);

        // Time complexity: O(m + n);
        ListNode merged = mergedHead;
        ListNode i = l1;
        ListNode j = l2;
        while (i != null || j != null) {
            if (i == null) {
                merged.next = j;
                break;
            } else if (j == null) {
                merged.next = i;
                break;
            } else {
                if (i.val < j.val) {
                    // Take and go to next.
                    merged.next = i;
                    merged = merged.next;
                    i = i.next;
                } else {
                    // Take and go to next.
                    merged.next = j;
                    merged = merged.next;
                    j = j.next;
                }
            }
        }

        return mergedHead.next;
    }

    private ListNode numToListNode(int... nums) {
        if (nums == null) return null;

        final ListNode head = new ListNode(0);

        ListNode node = head;
        for (int num : nums) {
            node.next = new ListNode(num);
            node = node.next;
        }

        return head.next;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ListNode node = (ListNode) o;

            if (val != node.val) return false;
            return next != null ? next.equals(node.next) : node.next == null;

        }

        @Override
        public int hashCode() {
            int result = val;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }
    }
}
