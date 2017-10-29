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
 * Rotate an array of n elements to the right by k steps.
 * <p/>
 * For example:
 * <pre>
 *     For example, given array [1,2,3,4,5,6,7] and k = 3
 *     the array is rotated to [5,6,7,1,2,3,4].
 * </pre>
 * References:
 * <ul>
 * <li>https://leetcode.com/problems/rotate-array/description/</li>
 * </ul>
 */
public class QuizEasy_RotateArray {

    @Test
    public void test() throws Exception {
        Assert.assertArrayEquals(new int[]{1, 2},
                                 rotate(new int[]{2, 1}, 1));
        Assert.assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4},
                                 rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    // Solution #1 ////////////////////////////////////////////////////////////

    /**
     * Beats 10% on LeetCode.
     */
    private int[] rotate(int[] nums, int k) {
        // Do nothing if the k is multiple of array length.
        if (k == 0 || (k = k % nums.length) == 0) return nums;

        // For example:
        // Given array [1,2,3,4,5,6,7] and k = 3
        //
        // original: [1,2,3,4,5,6,7]
        // clone:    [5,0,0,0,0,0,0]
        //
        // original: [1,2,3,4,5,6,7]
        // clone:    [5,6,0,0,0,0,0]
        //
        // original: [1,2,3,4,5,6,7]
        // clone:    [5,6,7,0,0,0,0]
        //
        // and so on...
        //
        // original: [1,2,3,4,5,6,7]
        // clone:    [5,6,7,1,2,3,4]
        //
        // then copy the values from clone to original!

        // Time complexity: O(n).
        // Space complexity: O(n).
        int[] clone = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            int j = (nums.length + i - k) % nums.length;

            clone[i] = nums[j];
        }

        // Time complexity: O(n).
        // Space complexity: O(n).
        System.arraycopy(clone, 0, nums, 0, nums.length);

        return nums;
    }
}
