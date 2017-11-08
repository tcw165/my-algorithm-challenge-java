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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two sorted linked-list and return it as a new list. The new list should
 * be made by splicing together the nodes of the first two lists.
 * <p/>
 * Example:
 * <pre>
 * Input:   1->4->5->7 and
 *          2->3->6->10
 * Output:  1->2->3->4->5->6->7->10
 * </pre>
 * Reference:
 * <ul>
 * <li>https://www.careercup.com/question?id=5187906612232192</li>
 * </ul>
 */
public class QuizEasy_MergeTwoLinkedList {

    @Test
    public void test() throws Exception {
        Assert.assertArrayEquals(
            new int[]{1, 2, 3, 4, 5, 6, 7, 10},
            toIntArray(merge(toLinkedList(new int[]{1, 4, 5, 7}),
                             toLinkedList(new int[]{2, 3, 6, 10}))));
        Assert.assertArrayEquals(
            new int[]{1, 2, 3, 4, 5, 6, 7, 12, 15, 23, 33, 99},
            toIntArray(merge(toLinkedList(new int[]{1, 4, 5, 7, 99}),
                             toLinkedList(new int[]{2, 3, 6, 12, 15, 23, 33}))));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Question from the Facebook coding interview.
     */
    private List<Integer> merge(LinkedList<Integer> list1,
                                LinkedList<Integer> list2) {
        // The Java LinkedList is Doubly-linked list implementation of the List
        // and Deque interfaces. Operations that index into the list will traverse
        // the list from the beginning or the end, whichever is closer to the
        // specified index.

        // For example:
        // list1:   1 4 5 7
        //          +>i
        // list2:   2 3 6 10
        //          +>j
        // buffer1: nil
        // buffer2: 2
        // merged:  1
        //
        // list1:   1 4 5 7
        //            +>i
        // list2:   2 3 6 10
        //            j
        // buffer1: 4
        // buffer2: nil
        // merged:  1 2
        //
        // list1:   1 4 5 7
        //              i
        // list2:   2 3 6 10
        //            +>j
        // buffer1: 4
        // buffer2: nil
        // merged:  1 2 3
        //
        // list1:   1 4 5 7
        //              i
        // list2:   2 3 6 10
        //              +>j
        // buffer1: nil
        // buffer2: 6
        // merged:  1 2 3 4
        //
        // list1:   1 4 5 7
        //              +>i
        // list2:   2 3 6 10
        //                j
        // buffer1: nil
        // buffer2: 6
        // merged:  1 2 3 4 5
        //
        // list1:   1 4 5 7
        //                +>i (trick here, we need a boolean check to make sure
        // list2:   2 3 6 10   the one of the two lists is completely exhausted)
        //                j
        // buffer1: 7
        // buffer2: nil
        // merged:  1 2 3 4 5 6
        //
        // list1:   1 4 5 7
        //                  i
        // list2:   2 3 6 10
        //                +->j
        // buffer1: nil
        // buffer2: 10
        // merged:  1 2 3 4 5 6 7
        //
        // list1:   1 4 5 7
        //                  i
        // list2:   2 3 6 10
        //                   j
        // buffer1: nil
        // buffer2: nil
        // merged:  1 2 3 4 5 6 7 10 <-- the answer!!!

        List<Integer> merged = new ArrayList<>(list1.size() + list2.size());
        Iterator<Integer> it1 = list1.iterator();
        Iterator<Integer> it2 = list2.iterator();

        // Because the iterator.next() returns the value and also advance one
        // step, we need two auxiliary buffers to prevent iterators from
        // advancing unnecessarily.
        boolean hasBuffer1 = false;
        int buffer1 = 0;
        boolean hasBuffer2 = false;
        int buffer2 = 0;

        // To exhaust one of the two lists.
        while ((it1.hasNext() || hasBuffer1) &&
               (it2.hasNext() || hasBuffer2)) {
            int val1 = hasBuffer1 ? buffer1 : it1.next();
            int val2 = hasBuffer2 ? buffer2 : it2.next();

            if (val1 < val2) {
                merged.add(val1);

                hasBuffer1 = false;
                hasBuffer2 = true;

                buffer2 = val2;
            } else {
                merged.add(val2);

                hasBuffer1 = true;
                hasBuffer2 = false;

                buffer1 = val1;
            }
        }

        // Exhaust the remaining list1.
        while (it1.hasNext() || hasBuffer1) {
            int val = hasBuffer1 ? buffer1 : it1.next();
            merged.add(val);

            hasBuffer1 = false;
        }
        // Exhaust the remaining list2.
        while (it2.hasNext() || hasBuffer2) {
            int val = hasBuffer2 ? buffer2 : it2.next();
            merged.add(val);

            hasBuffer2 = false;
        }

        return merged;
    }

    /**
     * Utils method.
     */
    private LinkedList<Integer> toLinkedList(int[] vals) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int val : vals) {
            list.add(val);
        }

        return list;
    }

    /**
     * Utils method.
     */
    private int[] toIntArray(List<Integer> list) {
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); ++i) {
            array[i] = list.get(i);
        }

        return array;
    }
}
