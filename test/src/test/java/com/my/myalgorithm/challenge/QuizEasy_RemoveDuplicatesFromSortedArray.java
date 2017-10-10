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

import android.util.Size;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * <p/>
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * <p/>
 * For example,
 * <pre>
 *     Given input array nums = [1,1,2],
 *     Your function should return length = 2, with the first two elements of
 *     nums being 1 and 2 respectively. It doesn't matter what you leave beyond
 *     the new length.
 * </pre>
 * Reference:
 * <ul>
 *     <li>LeetCode: https://leetcode.com/problems/merge-two-sorted-lists/description/</li>
 * </ul>
 */
public class QuizEasy_RemoveDuplicatesFromSortedArray {

    @Test
    public void test() throws Exception {
        Assert.assertEquals(2, removeDuplicates(new int[]{1, 1, 2}));
        Assert.assertEquals(6, removeDuplicates(new int[]{1, 2, 3, 3, 4, 5, 5, 5, 6}));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 22.65% on LeetCode.
     */
    private int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        // For example:
        // i is the slow runner; j is the fast runner
        //
        // [ 1 , 2 , 2 , 3 , 4 , 4 , 4 , 4 , 5 ]
        //   i
        //       j
        //
        // [ 1 , 2 , 2 , 3 , 4 , 4 , 4 , 4 , 5 ]
        //       i
        //           j
        //
        // [ 1 , 2 , 2 , 3 , 4 , 4 , 4 , 4 , 5 ]
        //       i
        //               j
        //
        // [ 1 , 2 ,(3), 3 , 4 , 4 , 4 , 4 , 5 ]
        //           i
        //               j
        //
        // [ 1 , 2 , 3 , 3 , 4 , 4 , 4 , 4 , 5 ]
        //           i
        //                   j
        //
        // [ 1 , 2 , 3 ,(4), 4 , 4 , 4 , 4 , 5 ]
        //               i
        //                   j
        //
        // skip duplicates...
        //
        // [ 1 , 2 , 3 , 4 , 4 , 4 , 4 , 4 , 5 ]
        //               i
        //                                   j
        //
        // [ 1 , 2 , 3 , 4 ,(5), 4 , 4 , 4 , 5 ]
        //                   i
        //                                   j

        // Since the array is already sorted, we can keep two pointers i and j,
        // where i is the slow-runner while j is the fast-runner. As long as
        // nums[i] = nums[j], we increment j to skip the duplicate.
        // When we encounter nums[j] ≠ nums[i], the duplicate run has ended so
        // we must copy its value to nums[i + 1]. i is then incremented and we
        // repeat the same process again until j reaches the end of array.
        // Time complexity: O(n), space complexity: O(1).
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }
}
