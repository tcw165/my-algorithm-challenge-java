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

package com.my.myalgorithm.challenge.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * QuickSort is a divide-and-conquer algorithm that was invented by Tony Hoare
 * in 1959. Quicksort first divides a large array into two smaller sub-arrays:
 * the low elements and the high elements. Quicksort can then recursively sort
 * the sub-arrays.
 * <br/>
 * <br/>
 * Complexity:
 * <br/>
 * Time: worst O(n^2), best O(n * log(n)), average O(n * log(n))
 * <br/>
 * Space: worst O(n) auxiliary.
 * <br/>
 * <br/>
 * Wiki: https://en.wikipedia.org/wiki/Quicksort
 * <br/>
 * <br/>
 * e.g. Sort the list in the ascending order.
 * <pre>
 * Step 1: Find the pivot which divides the array into two halves.
 *
 *    [5], 2 , 4 , 6 , 1 , 3
 *     L                   H  (Pivot at low position, and compare to the high position)
 * =>  3 , 2 , 4 , 6 , 1 ,[5]
 *     L                   H  (Until the item is less than the pivot, exchange them)
 * =>  3 , 2 , 4 , 6 , 1 ,[5]
 *         L               H  (Keep advancing the opposite side of the pivot)
 * =>  3 , 2 , 4 , 6 , 1 ,[5]
 *             L           H  (Keep advancing the opposite side of the pivot)
 * =>  3 , 2 , 4 , 6 , 1 ,[5]
 *                 L       H  (Until the item is larger than the pivot)
 * =>  3 , 2 , 4 ,[5], 1 , 6
 *                 L       H  (Exchange the pivot with the opposite position)
 * =>  3 , 2 , 4 ,[5], 1 , 6
 *                 L   H      (Keep advancing the opposite side of the pivot)
 * =>  3 , 2 , 4 , 1 ,[5], 6
 *                 L   H      (Keep advancing the opposite side of the pivot)
 * =>  3 , 2 , 4 , 1 ,[5], 6
 *                    L H     (Until the low position is equal to the high position)
 *
 * Step 2: Break the array into low and high sub-arrays.
 *
 * =>  3,2,4,1,5 and 6
 *
 * Step 3: Repeat the above process until the sub-array is small enough.
 * </pre>
 */
public class QuickSort {

    public static <T> void sort(List<T> list,
                                Comparator<? super T> c) {
        if (list == null || list.size() < 2) return;
        // Sort the list.
        new Sorter<>(list, c).sort();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private QuickSort() {
        // DO NOTHING.
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class Sorter<T> {

        List<T> mTargetList;

        Comparator<? super T> mComparator;

        Sorter(List<T> targetList,
               Comparator<? super T> comparator) {
            if (targetList == null || targetList.size() < 2) {
                throw new IllegalArgumentException("Invalid list or size is " +
                                                   "less than 2");
            }
            mTargetList = targetList;
            mComparator = comparator;
        }

        void sort() {
            topDownSplit(0, mTargetList.size() - 1);
        }

        /**
         * Quick sort the {@code mTargetList} at the range [lowStart, highStart].
         */
        void topDownSplit(int lowStart,
                          int highStart) {
            if (highStart <= lowStart) return;

            int lowBound = lowStart;
            int highBound = highStart;
            // Start the comparison from the high side.
            int pivot = lowStart;
            int advancedStep = -1;
            // First, divide a large array into two smaller sub-arrays by
            // finding the pivot.
            // At the meanwhile, the process make sure all the elements in the
            // left sub-array are less than all the elements in the right sub-
            // array if it is asked to sort the list in the ascending order,
            // and vice versa.
            while (highBound > lowBound) {
                if ((advancedStep < 0 &&
                     mComparator.compare(mTargetList.get(pivot),
                                         mTargetList.get(highBound)) < 0) ||
                    (advancedStep > 0 &&
                     mComparator.compare(mTargetList.get(lowBound),
                                         mTargetList.get(pivot)) < 0)) {
                    // Exchange the pivot with the active side (depends on the
                    // sign of the advancedStep).
                    T pivotItem = mTargetList.get(pivot);
                    if (advancedStep < 0) {
                        mTargetList.set(pivot, mTargetList.get(highBound));
                        mTargetList.set(highBound, pivotItem);

                        pivot = highBound;
                    } else {
                        mTargetList.set(pivot, mTargetList.get(lowBound));
                        mTargetList.set(lowBound, pivotItem);

                        pivot = lowBound;
                    }

                    // Update the active side.
                    advancedStep *= -1;

                    continue;
                }

                if (advancedStep < 0) {
                    highBound += advancedStep;
                } else {
                    lowBound += advancedStep;
                }
            }

            // Sort the low sub-array.
            topDownSplit(lowStart, pivot);
            // Sort the high sub-array.
            topDownSplit(pivot + 1, highStart);
        }
    }
}
