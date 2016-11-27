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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Mergesort is a divide-and-conquer algorithm that was invented by John von
 * Neumann in 1945. The divide-and-conquer algorithm break the problem into
 * several subproblems that are similar to the original problem but smaller
 * in size, solve the subproblems recursively, and then combine these solutions
 * to create a solution to the original problem.
 * <br/>
 * <br/>
 * Complexity:
 * <br/>
 * Time: average O(n * log(n))
 * <br/>
 * Space: worst O(n).
 * <br/>
 * <br/>
 * Wiki: https://en.wikipedia.org/wiki/Merge_sort
 * <br/>
 * <br/>
 * e.g.
 * <pre>
 *     5,2,4,6,1,3
 *      /       \        (split)
 *   5,2,4     6,1,3
 *   /  \      /  \      (split)
 *  5  2,4    6  1,3
 *  \  /      \  /       (merge)
 *  2,4,5    1,3,6
 *    \       /          (merge)
 *   1,2,3,4,5,6
 * </pre>
 */
public class MergeSort {

    public static <T> void sort(List<T> list,
                                Comparator<? super T> c) {
        if (list == null || list.size() < 2) return;
        // Sort the list.
        new Sorter<>(list, c).sort();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private MergeSort() {
        // DO NOTHING.
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class Sorter<T> {

        List<T> mTargetList;
        List<T> mCopyList;

        Comparator<? super T> mComparator;

        Sorter(List<T> targetList,
               Comparator<? super T> comparator) {
            if (targetList == null || targetList.size() < 2) {
                throw new IllegalArgumentException("Invalid list or size is " +
                                                   "less than 2");
            }
            mTargetList = targetList;
            mCopyList = new ArrayList<>(targetList.size());
            mCopyList.addAll(mTargetList);
            mComparator = comparator;
        }

        void sort() {
            topDownSplitMerge(0, mTargetList.size() / 2, mTargetList.size());
        }

        /**
         * The range of left sub-array is [leftStart, mid);
         * <br/>
         * The range of right sub-array is [mid, rightEnd);
         * <br/>
         * e.g.
         * <pre>
         *     5,2,4,6,1,3
         *      /       \        (split)
         *   5,2,4     6,1,3
         *   /  \      /  \      (split)
         *  5  2,4    6  1,3
         *  \  /      \  /       (merge)
         *  2,4,5    1,3,6
         *    \       /          (merge)
         *   1,2,3,4,5,6
         * </pre>
         */
        void topDownSplitMerge(int leftStart,
                               int mid,
                               int rightEnd) {
            if (rightEnd - leftStart > 2) {
                // Split again to generate the left sub-array.
                topDownSplitMerge(leftStart,
                                  leftStart + (mid - leftStart) / 2,
                                  mid);
                // The right sub-array.
                topDownSplitMerge(mid,
                                  mid + (rightEnd - mid) / 2,
                                  rightEnd);

                // Merge the sub-arrays and write the result to the target
                // array.
                int global = leftStart;
                int left = leftStart;
                int right = mid;
                while (left < mid && right < rightEnd) {
                    if (mComparator.compare(mCopyList.get(left),
                                            mCopyList.get(right)) < 0) {
                        mTargetList.set(global++, mCopyList.get(right++));
                    } else {
                        mTargetList.set(global++, mCopyList.get(left++));
                    }
                }
                // Maybe there are remaining elements in the left sub-array.
                while (left < mid) {
                    mTargetList.set(global++, mCopyList.get(left++));
                }
                // Maybe there are remaining elements in the right sub-array.
                while (right < rightEnd) {
                    mTargetList.set(global++, mCopyList.get(right++));
                }
                // Sync the result from target list to the copy list so that
                // the copy list can be used in the next merging process.
                for (int i = leftStart; i < rightEnd; ++i) {
                    mCopyList.set(i, mTargetList.get(i));
                }
            } else {
                // The stop condition of the splitting recursion.
                for (int i = 1; i < rightEnd; ++i) {
                    int j = i - 1;
                    T current = mCopyList.get(i);
                    T before = mCopyList.get(j);

                    while (j >= 0 &&
                           // Need to exchange the before and current element.
                           mComparator.compare(before, current) < 0) {
                        mCopyList.set(j + 1, before);
                        // Get new before element.
                        --j;
                        if (j >= 0) {
                            before = mCopyList.get(j);
                        }
                    }

                    mCopyList.set(j + 1, current);
                }
            }
        }

        /**
         * The range of left sub-array is [leftStart, mid);
         * <br/>
         * The range of right sub-array is [mid, rightEnd);
         * <br/>
         * e.g.
         * <pre>
         *     5,2,4,6,1,3
         *      /       \        (split)
         *   5,2,4     6,1,3
         *   /  \      /  \      (split)
         *  5  2,4    6  1,3
         *  \  /      \  /       (merge)
         *  2,4,5    1,3,6
         *    \       /          (merge)
         *   1,2,3,4,5,6
         * </pre>
         */
        void bottomUpSplitMerge(int leftStart,
                                int mid,
                                int rightEnd) {
            // TODO: Complete it.
        }
    }
}
