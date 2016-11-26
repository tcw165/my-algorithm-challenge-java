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

import java.util.Comparator;
import java.util.List;

/**
 * Insertion sort is a simple sorting algorithm that builds the final sorted
 * array (or list) one item at a time. It is much less efficient on large lists
 * than more advanced algorithms such as quicksort, heapsort, or merge sort.
 * <br/>
 * <br/>
 * Complexity
 * <br/>
 * Time: worst O(n^2), best O(n), average O(n^2)
 * <br/>
 * Space: worst O(1) auxiliary.
 * <br/>
 * <br/>
 * Wiki: https://en.wikipedia.org/wiki/Insertion_sort
 */
public class InsertionSort {

    public static <T> void sort(List<T> list,
                                Comparator<? super T> c) {
        if (list.size() < 2) return;

        // Anchor an index, and compare every elements before the index to the
        // anchor element. Switch them one by one if necessary until elements
        // before are all sorted.
        // e.g.
        //     5,2,4,6,1,3
        //       ^
        // =>  2,5,4,6,1,3
        //       ^
        // =>  2,5,4,6,1,3
        //         ^
        // =>  2,4,5,6,1,3
        //         ^
        // =>  2,4,5,6,1,3
        //           ^
        // =>  2,4,5,6,1,3
        //           ^
        // =>  2,4,5,6,1,3
        //             ^
        // =>  1,2,4,5,6,3
        //             ^
        // =>  1,2,4,5,6,3
        //               ^
        // =>  1,2,3,4,5,6
        //               ^
        for (int i = 1; i < list.size(); ++i) {
            int j = i - 1;
            T current = list.get(i);
            T before = list.get(j);
            while (j >= 0 &&
                   // Need to switch the before and current element.
                   c.compare(current, before) > 0) {
                list.set(j + 1, before);
                // Get new before element.
                if (--j >= 0) {
                    before = list.get(j);
                }
            }

            list.set(j + 1, current);
        }
    }
}
