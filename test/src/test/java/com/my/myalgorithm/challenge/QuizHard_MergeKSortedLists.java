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

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * <p/>
 * Reference:
 * <p/>
 * LeetCode: https://leetcode.com/problems/merge-k-sorted-lists/description/
 * LeetCode: https://leetcode.com/problems/merge-k-sorted-lists/solution/
 */
public class QuizHard_MergeKSortedLists {

    @Test
    public void test_Sol3() throws Exception {
        Assert.assertNull(mergeKLists_Sol3(new ListNode[]{null, null}));
        Assert.assertEquals(numToListNode(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                            mergeKLists_Sol3(new ListNode[]{
                                numToListNode(3, 6),
                                numToListNode(1, 5, 7),
                                numToListNode(2, 4, 8, 9),
                                numToListNode(10)
                            }));
        Assert.assertEquals(numToListNode(-1, 2, 3, 4, 5, 7, 8, 10, 11, 13),
                            mergeKLists_Sol3(new ListNode[]{
                                null,
                                numToListNode(-1, 5, 11),
                                null,
                                numToListNode(2),
                                numToListNode(10),
                                null,
                                numToListNode(3, 7),
                                numToListNode(4, 8, 13),
                                null,
                                }));
        Assert.assertEquals(numToListNode(1, 2, 3, 4, 5, 7, 8, 9, 10, 13, 18),
                            mergeKLists_Sol3(new ListNode[]{
                                numToListNode(1, 8, 10, 18),
                                numToListNode(2, 7, 9, 13),
                                numToListNode(3, 4, 5)
                            }));
    }

    @Test
    public void test_Sol2() throws Exception {
        Assert.assertNull(mergeKLists_Sol2(new ListNode[]{null, null}));
        Assert.assertEquals(numToListNode(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                            mergeKLists_Sol2(new ListNode[]{
                                numToListNode(3, 6),
                                numToListNode(1, 5, 7),
                                numToListNode(2, 4, 8, 9),
                                numToListNode(10)
                            }));
        Assert.assertEquals(numToListNode(-1, 2, 3, 4, 5, 7, 8, 10, 11, 13),
                            mergeKLists_Sol2(new ListNode[]{
                                null,
                                numToListNode(-1, 5, 11),
                                null,
                                numToListNode(2),
                                numToListNode(10),
                                null,
                                numToListNode(3, 7),
                                numToListNode(4, 8, 13),
                                null,
                                }));
        Assert.assertEquals(numToListNode(1, 2, 3, 4, 5, 7, 8, 9, 10, 13, 18),
                            mergeKLists_Sol2(new ListNode[]{
                                numToListNode(1, 8, 10, 18),
                                numToListNode(2, 7, 9, 13),
                                numToListNode(3, 4, 5)
                            }));
    }

    @Test
    public void test_Sol1() throws Exception {
        Assert.assertNull(mergeKLists_Sol1(new ListNode[]{null, null}));
        Assert.assertEquals(numToListNode(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                            mergeKLists_Sol1(new ListNode[]{
                                numToListNode(3, 6),
                                numToListNode(1, 5, 7),
                                numToListNode(2, 4, 8, 9),
                                numToListNode(10)
                            }));
        Assert.assertEquals(numToListNode(-1, 2, 3, 4, 5, 7, 8, 10, 11, 13),
                            mergeKLists_Sol1(new ListNode[]{
                                null,
                                numToListNode(-1, 5, 11),
                                null,
                                numToListNode(2),
                                numToListNode(10),
                                null,
                                numToListNode(3, 7),
                                numToListNode(4, 8, 13),
                                null,
                                }));
        Assert.assertEquals(numToListNode(1, 2, 3, 4, 5, 7, 8, 9, 10, 13, 18),
                            mergeKLists_Sol1(new ListNode[]{
                                numToListNode(1, 8, 10, 18),
                                numToListNode(2, 7, 9, 13),
                                numToListNode(3, 4, 5)
                            }));
    }

    @Test
    public void testInternal() throws Exception {
        Assert.assertEquals(numToListNode(1, 3, 4, 5, 8, 10, 18),
                            mergeTwoLists(numToListNode(1, 8, 10, 18),
                                          numToListNode(3, 4, 5)));
        Assert.assertEquals(numToListNode(1, 3, 5, 7, 10, 13, 18),
                            mergeTwoLists(numToListNode(1, 7, 10, 18),
                                          numToListNode(3, 5, 13)));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #3 ////////////////////////////////////////////////////////////

    /**
     * Beats 77.78% on LeetCode (couldn't pass the time limit).
     */
    private ListNode mergeKLists_Sol3(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        // Use divide-and-conquer strategy to avoid from repeatedly going through
        // the k lists to get the minimum node in order to construct the new
        // list.
        // Time complexity: O(N * log(k)), where k is the number of linked lists;
        // Space complexity: O(1).
        // Find the first NOT null element.
        int left = 0;
        while (lists[left] == null) {
            if (++left >= lists.length) {
                return null;
            }
        }
        int right = lists.length - 1;
        while (left < right) {
            int i = left;
            int j = right;

            // In each inner loop, merge sub-list so that amount becomes 1/2 as
            // much as previous amount.
            while (i < j) {
                if (lists[i] == null) {
                    ++i;
                } else if (lists[j] == null) {
                    --j;
                } else {
                    lists[i] = mergeTwoLists(lists[i], lists[j]);
                    lists[j] = null;

                    ++i;
                    --j;
                }
            }

            right = j;
        }

        return lists[left];
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        final ListNode head = new ListNode(0);
        ListNode node = head;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                node.next = l2;
                break;
            } else if (l2 == null) {
                node.next = l1;
                break;
            } else {
                if (l1.val < l2.val) {
                    node.next = l1;
                    node = node.next;

                    l1 = l1.next;
                } else {
                    node.next = l2;
                    node = node.next;

                    l2 = l2.next;
                }
            }
        }

        return head.next;
    }

    // Solution #2 ////////////////////////////////////////////////////////////

    /**
     * Beats 43% on LeetCode.
     */
    private ListNode mergeKLists_Sol2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(
            lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val == o2.val)
                    return 0;
                else
                    return 1;
            }
        });

        final ListNode head = new ListNode(0);
        ListNode node = head;

        for (ListNode n : lists) {
            if (n != null) {
                queue.add(n);
            }
        }

        while (!queue.isEmpty()) {
            node.next = queue.poll();
            node = node.next;

            if (node.next != null)
                queue.add(node.next);
        }

        return head.next;
    }


    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Could not pass time limit on LeetCode.
     */
    private ListNode mergeKLists_Sol1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        final ListNode head = new ListNode(0);

        ListNode node = head;
        while (true) {
            // Find the first NOT null list.
            int i = 0;
            while (lists[i] == null) {
                if (++i >= lists.length) {
                    // Already visit every single elements in the given list.
                    return head.next;
                }
            }

            // Go through the list to find the minimum value.
            ListNode min = lists[i];
            for (int j = 1; j < lists.length; ++j) {
                if (lists[j] == null) continue;
                if (min.val > lists[j].val) {
                    min = lists[j];
                    i = j;
                }
            }

            // Advance the list.
            lists[i] = lists[i].next;

            // Construct the new list.
            node.next = min;
            node = node.next;
        }
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
